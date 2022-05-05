package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.repository.ISchoolRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchoolService {
    @Resource
    private ISchoolRepository schoolRepository;
}
