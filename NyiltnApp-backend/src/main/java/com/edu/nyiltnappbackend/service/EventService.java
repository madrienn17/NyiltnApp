package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.repository.IEventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EventService {
    @Resource
    private IEventRepository eventRepository;
}
