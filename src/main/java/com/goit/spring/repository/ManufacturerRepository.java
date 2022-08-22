package com.goit.spring.repository;


import com.goit.spring.model.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
}
