package com.techpro.project.controllers;

import com.techpro.project.entity.People;
import com.techpro.project.repository.PeopleRepository;
import com.techpro.project.service.ServicePeopleImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("people")
@CrossOrigin(origins = "http://localhost:3000")
public class PeopleController {

  @Autowired
  private final ServicePeopleImpl servicePeople;

  @GetMapping(value = "/find_people", produces = "application/json")
  public List<People> findAll() {
    return servicePeople.findAll();
  }

  @PostMapping(value = "/create_people", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> test_controller3(@RequestBody People people) {
    System.out.println(people);
    servicePeople.savePeople(people);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/get_people/{id}", produces = "application/json")
  public Optional<People> findById(@PathVariable Long id) {
      System.out.println("id");
    return servicePeople.findById(id);
  }

  @PutMapping(value = "/update_people", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> updatePeople(@RequestBody People updatedPeople) {
    System.out.println("test");
    servicePeople.updatePeople(updatedPeople);

    return ResponseEntity.ok().build();
  }

  @DeleteMapping(value = "/delete_people/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    servicePeople.deleteById(id);
    return ResponseEntity.ok().build();
  }


}
