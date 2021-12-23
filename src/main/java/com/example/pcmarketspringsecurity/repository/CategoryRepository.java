package com.example.pcmarketspringsecurity.repository;

import com.example.pcmarketspringsecurity.entity.Category;
import com.example.pcmarketspringsecurity.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
