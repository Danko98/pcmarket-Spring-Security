package com.example.pcmarketspringsecurity.repository;

import com.example.pcmarketspringsecurity.entity.Computer;
import com.example.pcmarketspringsecurity.entity.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RamRepository extends JpaRepository<Ram, Integer> {


}
