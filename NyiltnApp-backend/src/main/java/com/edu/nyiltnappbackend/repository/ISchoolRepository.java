package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.SchoolBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISchoolRepository extends JpaRepository<SchoolBE, Long> {

    Optional<SchoolBE> getBySchoolName(String name);
}
