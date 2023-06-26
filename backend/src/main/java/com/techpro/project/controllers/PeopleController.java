package com.techpro.project.controllers;

import com.techpro.project.entity.People;
import com.techpro.project.service.ServicePeopleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("people")
@CrossOrigin(origins = "http://localhost:3000")
public class PeopleController {

  private final ServicePeopleImpl servicePeople;

  @Autowired
  public PeopleController (ServicePeopleImpl servicePeople) {
    this.servicePeople = servicePeople;
  }

  /**
   * Retrieves a list of all people.
   * <p>
   * This method accepts a GET request at "/find_people" and produces a JSON response.
   * It calls the ServicePeople's findAll() method to retrieve all people.
   *
   * @return A List of People objects representing all the people.
   */

  @GetMapping(value = "/find_people", produces = "application/json")
  public List<People> findAll() {
    return servicePeople.findAll();
  }

  /**
   * Creates a new person.
   * <p>
   * This method accepts a POST request at "/create_people" with a JSON payload representing the person to be created.
   *
   * @param people The People object representing the person to be created.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @PostMapping(value = "/create_people", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> createPeople(@RequestBody People people) {
    servicePeople.savePeople(people);
    return ResponseEntity.ok().build();
  }

  /**
   * Retrieves a person by their ID.
   * <p>
   * This method accepts a GET request at "/get_people/{id}" with the person ID as a path variable.
   *
   * @param id The ID of the person to be retrieved.
   * @return An Optional containing the People object if found, or an empty Optional if not found.
   */

  @GetMapping(value = "/get_people/{id}", produces = "application/json")
  public Optional<People> findById(@PathVariable Long id) {
    return servicePeople.findById(id);
  }

  /**
   * Updates a person.
   * <p>
   * This method accepts a PUT request at "/update_people" with a JSON payload representing the updated person.
   *
   * @param updatedPeople The People object representing the updated person.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @PutMapping(value = "/update_people", consumes = "application/json", produces = "application/json")
  public ResponseEntity<?> updatePeople(@RequestBody People updatedPeople) {
    servicePeople.updatePeople(updatedPeople);
    return ResponseEntity.ok().build();
  }

  /**
   * Deletes a person by their ID.
   * <p>
   * This method accepts a DELETE request at "/delete_people/{id}" with the person ID as a path variable.
   *
   * @param id The ID of the person to be deleted.
   * @return A ResponseEntity indicating the success of the operation.
   */

  @DeleteMapping(value = "/delete_people/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    servicePeople.deleteById(id);
    return ResponseEntity.ok().build();
  }


}
