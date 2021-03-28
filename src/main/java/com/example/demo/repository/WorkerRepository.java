package com.example.demo.repository;

import com.example.demo.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {

boolean existsByPhoneNumber(String phoneNumber);

boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

}
