package com.techpro.project.service;

import com.techpro.project.entity.People;
import com.techpro.project.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicePeopleImpl implements Servicepeople {
  private PeopleRepository peopleRepository;

  @Autowired
  public ServicePeopleImpl (PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  /**
 * Saves a people object.
 * This method saves the provided people object using the PeopleRepository's save() method.
 * @param people The people object to be saved.
 * @return The saved People object.
 */
  public People savePeople(People people) {
    return peopleRepository.save(people);
  }

  /**
 * Retrieves a list of all people.
 * It retrieves all people using the PeopleRepository's findAll() method.
 * @return A List of People objects representing all the people.
 */
  public List<People> findAll() {
    return peopleRepository.findAll();
  }

  /**
 * Finds a person by their ID.
 * This method retrieves the person with the specified ID using the PeopleRepository's findById() method.
 * @param id The ID of the person to be retrieved.
 * @return An Optional containing the People object if found, or an empty Optional if not found.
 */
  public Optional<People> findById(Long id) {
    return peopleRepository.findById(id);
  }

  /**
 * Updates a people object.
 * This method updates the provided people object using the PeopleRepository's save() method.
 * If the person already exists (based on its ID), it will be updated; otherwise, a new person will be created.
 * @param people The people object to be updated.
 * @return The updated People object.
 */
  public People updatePeople(People people) {
    //JPA understands if id exists so it upddates
    peopleRepository.save(people);
    return people;
  }

  /**
 * Deletes a person by their ID.
 * This method deletes the person with the specified ID using the PeopleRepository's deleteById() method.
 * @param id The ID of the person to be deleted.
 */
  public void deleteById(Long id) {
    peopleRepository.deleteById(id);
  }
}
