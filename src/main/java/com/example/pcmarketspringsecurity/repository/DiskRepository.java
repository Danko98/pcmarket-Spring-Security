package com.example.pcmarketspringsecurity.repository;

import com.example.pcmarketspringsecurity.entity.Computer;
import com.example.pcmarketspringsecurity.entity.Disk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiskRepository extends JpaRepository<Disk, Integer> {


}
