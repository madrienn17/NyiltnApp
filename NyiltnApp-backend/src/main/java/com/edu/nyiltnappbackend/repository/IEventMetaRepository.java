package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.EventMetaBE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventMetaRepository extends JpaRepository<EventMetaBE, Long> {
}
