package com.example.pcmarketspringsecurity.controller;

import com.example.pcmarketspringsecurity.entity.Category;
import com.example.pcmarketspringsecurity.entity.Cpu;
import com.example.pcmarketspringsecurity.others.ApiResponse;
import com.example.pcmarketspringsecurity.repository.CpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cpu")
public class CpuController {


    @Autowired
    CpuRepository cpuRepository;


    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ApiResponse addCpu(@RequestBody Cpu cpuDto){
        Cpu cpu = new Cpu();
        cpu.setName(cpuDto.getName());
        cpuRepository.save(cpu);
        return new ApiResponse("Prossessor saqlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping
    public ApiResponse editCpu(@RequestBody Cpu cpuDto, @PathVariable Integer id){
        if (cpuRepository.existsById(id)){
            return new ApiResponse("Bunday prossessor topilmadi.",false);
        }

        Optional<Cpu> optionalCpu = cpuRepository.findById(id);
        Cpu cpu = optionalCpu.get();
        cpu.setName(cpuDto.getName());
        cpuRepository.save(cpu);
        return new ApiResponse("Prossessor tahrirlandi.",true);

    }


    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public List<Cpu> getCpuList(){
        List<Cpu> cpuList = cpuRepository.findAll();
        return cpuList;
    }


    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ApiResponse getCpuById(@PathVariable Integer id){
        if (cpuRepository.existsById(id)){
            return new ApiResponse("Bunday prossessor topilmadi.",false);
        }
        Optional<Cpu> optionalCpu = cpuRepository.findById(id);
        return new ApiResponse(optionalCpu.get(),true);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteCpu(@PathVariable Integer id){
        if (cpuRepository.existsById(id)){
            return new ApiResponse("Bunday prossessor topilmadi.",false);
        }
        cpuRepository.deleteById(id);
        return new ApiResponse("Prossessor o'chirildi.",true);
    }


}
