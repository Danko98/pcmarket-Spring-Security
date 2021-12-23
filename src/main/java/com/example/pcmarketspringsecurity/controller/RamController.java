package com.example.pcmarketspringsecurity.controller;

import com.example.pcmarketspringsecurity.entity.Ram;
import com.example.pcmarketspringsecurity.others.ApiResponse;
import com.example.pcmarketspringsecurity.repository.RamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/ram")
public class RamController {

    @Autowired
    RamRepository ramRepository;


    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ApiResponse addRam(@RequestBody Ram ramDto){
        Ram ram = new Ram();
        ram.setSize(ramDto.getSize());
        ramRepository.save(ram);
        return new ApiResponse("Ram saqlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ApiResponse editRam(@RequestBody Ram ramDto, @PathVariable Integer id){
        if (!ramRepository.existsById(id)){
            return new ApiResponse("Bunday Ram topilmadi.",false);
        }
        Optional<Ram> optionalRam = ramRepository.findById(id);
        return new ApiResponse(optionalRam.get(),true);
    }


    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public List<Ram> getRamList(){
        List<Ram> ramList = ramRepository.findAll();
        return ramList;
    }


    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ApiResponse getRamById(@PathVariable Integer id){
        if (!ramRepository.existsById(id)){
            return new ApiResponse("Bunday Ram topilmadi.",false);
        }
        Optional<Ram> optionalRam = ramRepository.findById(id);
        return new ApiResponse(optionalRam.get(),true);

    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteRam(@PathVariable Integer id){
        if (!ramRepository.existsById(id)){
            return new ApiResponse("Bunday Ram topilmadi.",false);
        }
        ramRepository.deleteById(id);
        return new ApiResponse("Ram o'chirildi.",true);
    }








}
