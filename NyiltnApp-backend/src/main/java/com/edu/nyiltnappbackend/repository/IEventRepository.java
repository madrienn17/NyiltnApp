package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.EventBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEventRepository extends JpaRepository<EventBE, Long> {
    List<EventBE> findByEventMeta_Id(Long id);
    Optional<EventBE> findEventBEById(Long id);
    List<EventBE> findAllByOrderByMaxAttendeeNrDesc();
}
