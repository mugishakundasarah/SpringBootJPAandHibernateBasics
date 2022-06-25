package com.bgroup.mis.repository;

import com.bgroup.mis.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Integer>{
}
