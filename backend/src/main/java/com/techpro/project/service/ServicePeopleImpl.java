package com.techpro.project.service;

import com.techpro.project.entity.People;
import com.techpro.project.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicePeopleImpl {

    @Autowired
    private PeopleRepository peopleRepository;

    public People savePeople(People people) {
        return peopleRepository.save(people);
    }

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<People> findById(Long id) {
        return peopleRepository.findById(id);
    }

    public void updatePeople(People people) {
        //JPA understands if id exists so it upddates
        peopleRepository.save(people);
    }

    public void deleteById(Long id) {
        peopleRepository.deleteById(id);
    }
}
