package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);

}
