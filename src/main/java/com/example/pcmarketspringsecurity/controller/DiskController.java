package com.example.pcmarketspringsecurity.controller;

import com.example.pcmarketspringsecurity.entity.Disk;
import com.example.pcmarketspringsecurity.others.ApiResponse;
import com.example.pcmarketspringsecurity.repository.DiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/disk")
public class DiskController {


    @Autowired
    DiskRepository diskRepository;


    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ApiResponse addDisk(@RequestBody Disk diskDto){
        Disk disk = new Disk();
        disk.setName(diskDto.getName());
        disk.setSize(diskDto.getSize());
        diskRepository.save(disk);
        return new ApiResponse("Disk saqlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ApiResponse editDisk(@RequestBody Disk diskDto, @PathVariable Integer id){
        if (!diskRepository.existsById(id)){
            return new ApiResponse("Bunday Disk topilamdi.",false);
        }
        Optional<Disk> optionalDisk = diskRepository.findById(id);
        Disk disk = optionalDisk.get();
        disk.setSize(diskDto.getSize());
        disk.setName(diskDto.getName());
        diskRepository.save(disk);
        return new ApiResponse("Disk tahrirlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public List<Disk> getDiskList(){
        List<Disk> diskList = diskRepository.findAll();
        return diskList;
    }


    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ApiResponse getDiskById(@PathVariable Integer id){
        if (!diskRepository.existsById(id)){
            return new ApiResponse("Bunday disk topilmadi.",false);
        }
        Optional<Disk> optionalDisk = diskRepository.findById(id);
        return new ApiResponse(optionalDisk.get(),true);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteDisk(@PathVariable Integer id){
        if (!diskRepository.existsById(id)){
            return new ApiResponse("Bunday disk topilmadi.",false);
        }
        diskRepository.deleteById(id);
        return new ApiResponse("Disk o'chirildi.",true);
    }



}






























