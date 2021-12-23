package com.example.pcmarketspringsecurity.controller;

import com.example.pcmarketspringsecurity.Dto.ComputerDto;
import com.example.pcmarketspringsecurity.entity.*;
import com.example.pcmarketspringsecurity.others.ApiResponse;
import com.example.pcmarketspringsecurity.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/computer")
public class ComputerController {

    @Autowired
    ComputerRepository computerRepository;

    @Autowired
    CpuRepository cpuRepository;

    @Autowired
    DiagonalRepository diagonalRepository;

    @Autowired
    DiskRepository diskRepository;

    @Autowired
    RamRepository ramRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ApiResponse addComputer(@RequestBody ComputerDto computerDto){

        if (!categoryRepository.existsById(computerDto.getCategoryId())){
            return new ApiResponse("Bunday categorya topilamdi.",false);
        }

        if (!ramRepository.existsById(computerDto.getRamId())){
            return new ApiResponse("Bunday ram topilmadi.",false);
        }

        if (!diskRepository.existsById(computerDto.getDiskId())){
            return new ApiResponse("Bunday disk topilmadi.",false);
        }

        if (!diagonalRepository.existsById(computerDto.getDiagonalId())){
            return new ApiResponse("Bunday diogonal topilamdi.",false);
        }

        if (!cpuRepository.existsById(computerDto.getCpuId())){
            return new ApiResponse("Bunday prossesor topiladi.",false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(computerDto.getCategoryId());
        Optional<Cpu> optionalCpu = cpuRepository.findById(computerDto.getCpuId());
        Optional<Diagonal> optionalDiagonal = diagonalRepository.findById(computerDto.getDiagonalId());
        Optional<Disk> optionalDisk = diskRepository.findById(computerDto.getDiskId());
        Optional<Ram> optionalRam = ramRepository.findById(computerDto.getRamId());

        Computer computer = new Computer();

        computer.setName(computerDto.getName());
        computer.setCategory(optionalCategory.get());
        computer.setCpu(optionalCpu.get());
        computer.setDiagonal(optionalDiagonal.get());
        computer.setDisk(optionalDisk.get());

        computerRepository.save(computer);
        return new ApiResponse("Kompyuter saqlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ApiResponse addComputer(@PathVariable Integer id, @RequestBody ComputerDto computerDto){

        if (!computerRepository.existsById(id)){
            return new ApiResponse("Bunday Kompyuter topilmadi.",false);
        }

        if (!categoryRepository.existsById(computerDto.getCategoryId())){
            return new ApiResponse("Bunday categorya topilamdi.",false);
        }

        if (!ramRepository.existsById(computerDto.getRamId())){
            return new ApiResponse("Bunday ram topilmadi.",false);
        }

        if (!diskRepository.existsById(computerDto.getDiskId())){
            return new ApiResponse("Bunday disk topilmadi.",false);
        }

        if (!diagonalRepository.existsById(computerDto.getDiagonalId())){
            return new ApiResponse("Bunday diogonal topilamdi.",false);
        }

        if (!cpuRepository.existsById(computerDto.getCpuId())){
            return new ApiResponse("Bunday prossesor topiladi.",false);
        }

        Optional<Computer> optionalComputer = computerRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findById(computerDto.getCategoryId());
        Optional<Cpu> optionalCpu = cpuRepository.findById(computerDto.getCpuId());
        Optional<Diagonal> optionalDiagonal = diagonalRepository.findById(computerDto.getDiagonalId());
        Optional<Disk> optionalDisk = diskRepository.findById(computerDto.getDiskId());
        Optional<Ram> optionalRam = ramRepository.findById(computerDto.getRamId());

        Computer computer = optionalComputer.get();

        computer.setName(computerDto.getName());
        computer.setCategory(optionalCategory.get());
        computer.setCpu(optionalCpu.get());
        computer.setDiagonal(optionalDiagonal.get());
        computer.setDisk(optionalDisk.get());

        computerRepository.save(computer);
        return new ApiResponse("Kompyuter tahrirlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public List<Computer> getComputerList(){
        List<Computer> computerList = computerRepository.findAll();
        return computerList;
    }


    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ApiResponse getComputerById(@PathVariable Integer id){
        if (!computerRepository.existsById(id)){
            return new ApiResponse("Bunday kompyuter mavjud emas.",false);
        }

        Optional<Computer> optionalComputer = computerRepository.findById(id);
        return new ApiResponse(optionalComputer.get(),true);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteComputer(@PathVariable Integer id){
        if (!computerRepository.existsById(id)){
            return new ApiResponse("Bunday kompyuter mavjud emas.",false);
        }
        computerRepository.deleteById(id);
        return new ApiResponse("Kompyuter o'chirildi.",false);

    }
}


























