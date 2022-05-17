package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.model.dto.SelectItemDTO;
import com.edu.nyiltnappbackend.repository.ISchoolRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SchoolService {
    @Resource
    private ISchoolRepository schoolRepository;

    public ArrayList<SelectItemDTO> findAllOrderedByNameDescending() {
        ArrayList<SelectItemDTO> selectItemDTOS = new ArrayList<>();
        schoolRepository.findAllOrderedByNameDescending().forEach(x -> selectItemDTOS.add(new SelectItemDTO(x, x)));
        return selectItemDTOS;
    }
}
