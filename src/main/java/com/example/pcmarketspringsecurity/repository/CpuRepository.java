package com.example.pcmarketspringsecurity.repository;

import com.example.pcmarketspringsecurity.entity.Computer;
import com.example.pcmarketspringsecurity.entity.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuRepository extends JpaRepository<Cpu, Integer> {


}
