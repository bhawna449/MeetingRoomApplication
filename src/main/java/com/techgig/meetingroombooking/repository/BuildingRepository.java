package com.techgig.meetingroombooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techgig.meetingroombooking.entity.Building;

@Repository
public interface BuildingRepository extends CrudRepository<Building, String> {
}
