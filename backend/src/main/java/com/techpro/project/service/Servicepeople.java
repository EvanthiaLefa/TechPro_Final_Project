package com.techpro.project.service;

import com.techpro.project.entity.People;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface Servicepeople {
    // Save operation
    People savePeople(People people);
    List<People> findAll();
    Optional<People> findById(Long id);
    People updatePeople(People people);
    Optional<People> deleteById(Long id);
}

