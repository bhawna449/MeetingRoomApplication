package com.techgig.meetingroombooking.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techgig.meetingroombooking.entity.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, UUID> {

}
