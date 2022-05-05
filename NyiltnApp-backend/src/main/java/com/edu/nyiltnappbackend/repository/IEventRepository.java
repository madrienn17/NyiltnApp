package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.EventBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<EventBE, Long> {

}
