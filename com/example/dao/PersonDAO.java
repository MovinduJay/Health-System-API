package com.example.dao;

import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PersonDAO {
    private final List<Person> persons = new ArrayList<>();

    // Add a new person to the list
    public void addPerson(Person person) {
        persons.add(person);
    }

    // Retrieve a person by their ID
    public Person getPerson(int id) throws NotFoundException {
        for (Person person : persons) {
            if (person.getID() == id) {
                return person;
            }
        }
        throw new NotFoundException("Person with Id " + id + " not found");
    }

    // Update a person's information
    public void updatePerson(int id, Person updatedPerson) throws NotFoundException {
        Person person = getPerson(id);
        // Update person attributes
        person.setName(updatedPerson.getName());
        person.setContactInformation(updatedPerson.getContactInformation());
        person.setAddress(updatedPerson.getAddress());
    }

    // Delete a person from the list
    public void deletePerson(int id) throws NotFoundException {
        Person person = getPerson(id);
        persons.remove(person);
    }

    // Retrieve all persons in the list
    public List<Person> getAllPersons() {
        return persons;
    }
}
