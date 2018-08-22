package com.niran.reference.api.controller;

import com.niran.reference.api.domain.Person;
import com.niran.reference.api.domain.PersonRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    /**
    Get list of people
     */

    //produces - The producible media types of the mapped request, narrowing the primary mapping.
    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = "application/json")
    //Describes an operation or typically a HTTP method against a specific path.
    @ApiOperation(value = "Fetch all people")
    //Describes a possible response of an operation.
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Person.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")})
    public List<Person> people() {
        List<Person> people = (List<Person>) personRepository.findAll();

        return people;
    }


    /**
     Get one person
     */

    @RequestMapping(path = "/{id}",
            method = RequestMethod.GET)
    @ApiOperation(value = "Fetch a person")
    public Person person(@PathVariable Long id) {
        return personRepository.findOne(id);
    }


    /**
     Add one new person
     */
    @RequestMapping(path = "/add",
            method = RequestMethod.POST,
            //is used to specify which MIME media types of representations a resource can accept, or consume, from the client.
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a person")
    public Person add(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);

        return savedPerson;
    }


    /**
     Update selected person
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.PUT)
    @ApiOperation(value = "Update a person")
    public Person update(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);

        return savedPerson;
    }


    /**
     Daelete selected person
     */
    @RequestMapping(path = "/{id}",
            method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a person")
    public void delete(@PathVariable Long id) {
        personRepository.delete(id);
    }

}
