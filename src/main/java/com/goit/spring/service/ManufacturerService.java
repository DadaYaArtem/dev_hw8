package com.goit.spring.service;

import com.goit.spring.model.Manufacturer;
import com.goit.spring.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    @Autowired private ManufacturerRepository repo;

    public List<Manufacturer> listAll(){
        return (List<Manufacturer>) repo.findAll();
    }

    public void save(Manufacturer manufacturer) {
        repo.save(manufacturer);
    }

    public Manufacturer get (Long id) throws Exception {
        Optional<Manufacturer> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new Exception("Manufacturer not found!");
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}