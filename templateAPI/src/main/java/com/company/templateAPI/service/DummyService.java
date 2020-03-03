package com.company.templateAPI.service;

import com.company.templateAPI.entity.Dummy;
import com.company.templateAPI.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DummyService {
    @Autowired
    DummyRepository dummyRepository;

    public List<Dummy> getAll() {
        return dummyRepository.findAll();
    }
    public List<Dummy> getAll(Pageable pageable) {
        return dummyRepository.findAll(pageable).getContent();
    }

    public void insert(Dummy dummy) {
        dummyRepository.save(dummy);
    }

    public void delete(Integer id) {
        dummyRepository.deleteById(id);
    }

    public Optional<Dummy> getDummy(Integer id) {
        return dummyRepository.findById(id);
    }

    public void update(Dummy dummy) {
        dummyRepository.save(dummy);
    }
}
