package com.example.pcmarketspringsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Cpu cpu;

    @ManyToOne
    private Diagonal diagonal;

    @ManyToOne
    private Ram ram;

    @ManyToOne
    private Disk disk;





}
