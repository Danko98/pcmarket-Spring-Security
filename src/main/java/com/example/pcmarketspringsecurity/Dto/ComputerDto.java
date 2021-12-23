package com.example.pcmarketspringsecurity.Dto;

import lombok.Data;

@Data
public class ComputerDto {
    private String name;
    private Integer categoryId;
    private Integer cpuId;
    private Integer diagonalId;
    private Integer ramId;
    private Integer diskId;

}
