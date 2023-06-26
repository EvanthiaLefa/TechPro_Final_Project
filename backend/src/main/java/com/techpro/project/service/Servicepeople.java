package com.techpro.project.service;

import com.techpro.project.entity.People;

import java.util.List;
import java.util.Optional;

public interface Servicepeople {
    People savePeople(People people);
    List<People> findAll();
    Optional<People> findById(Long id);
    People updatePeople(People people);
    void deleteById(Long id);
}

