package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.EventBE;
import com.edu.nyiltnappbackend.model.dto.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IEventRepository extends JpaRepository<EventBE, Long> {
    Set<EventBE> findByEventMeta_Id(Long id);
}
