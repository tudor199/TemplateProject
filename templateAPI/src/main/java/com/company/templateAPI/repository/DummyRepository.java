package com.company.templateAPI.repository;

import com.company.templateAPI.entity.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyRepository extends JpaRepository<Dummy, Integer>, JpaSpecificationExecutor<Dummy> {
}
