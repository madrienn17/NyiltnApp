package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.LocationBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository extends JpaRepository<LocationBE, Long> {
}
