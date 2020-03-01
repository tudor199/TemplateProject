package com.company.templateAPI.service;

import com.company.templateAPI.entity.Dummy;
import com.company.templateAPI.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyService {
    @Autowired
    DummyRepository dummyRepository;

    public List<Dummy> getAll() {
        return dummyRepository.findAll();
    }

    public void insert(Dummy dummy) {
        dummyRepository.save(dummy);
    }
}
