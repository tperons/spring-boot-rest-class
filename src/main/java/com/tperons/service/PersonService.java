package com.tperons.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tperons.data.dto.v1.PersonDTOV1;
import com.tperons.data.dto.v2.PersonDTOV2;
import com.tperons.entity.Person;
import com.tperons.exception.ResourceNotFoundException;
import com.tperons.mapper.ObjectMapper;
import com.tperons.mapper.custom.PersonMapper;
import com.tperons.repository.PersonRepository;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper converter;


    public List<PersonDTOV1> findAll() {
        logger.info("Finding all People!");
        return ObjectMapper.parseListObjects(repository.findAll(), PersonDTOV1.class);
    }

    public PersonDTOV1 findById(Long id) {
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return ObjectMapper.parseObject(entity, PersonDTOV1.class);
    }

    public PersonDTOV1 create(PersonDTOV1 obj) {
        logger.info("Creating one Person!");
        var entity = ObjectMapper.parseObject(obj, Person.class);
        return ObjectMapper.parseObject(repository.save(entity), PersonDTOV1.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 obj) {
        logger.info("Creating one Person V2!");
        var entity = ObjectMapper.parseObject(obj, Person.class);
        // return ObjectMapper.parseObject(repository.save(entity), PersonDTOV1.class);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTOV1 update(Long id, PersonDTOV1 obj) {
        logger.info("Updating one Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(obj.getFirstName());
        entity.setLastName(obj.getLastName());
        entity.setAddress(obj.getAddress());
        entity.setGender(obj.getGender());
        return ObjectMapper.parseObject(repository.save(entity), PersonDTOV1.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);

    }

}
