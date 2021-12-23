package com.example.pcmarketspringsecurity.controller;

import com.example.pcmarketspringsecurity.entity.Diagonal;
import com.example.pcmarketspringsecurity.others.ApiResponse;
import com.example.pcmarketspringsecurity.repository.DiagonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/diagonal")
public class DiagonalController {

    @Autowired
    DiagonalRepository diagonalRepository;



    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ApiResponse addDiagonal(@RequestBody Diagonal diagonalDto){
        Diagonal diagonal = new Diagonal();
        diagonal.setSize(diagonalDto.getSize());
        diagonalRepository.save(diagonal);
        return new ApiResponse("Diagonal saqlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ApiResponse editDiagonal(@PathVariable Integer id, @RequestBody Diagonal diagonalDto){
        if (!diagonalRepository.existsById(id)){
            return new ApiResponse("Bunday diaganal topilmadi.",false);
        }
        Optional<Diagonal> optionalDiagonal = diagonalRepository.findById(id);

        Diagonal diagonal = optionalDiagonal.get();
        diagonal.setSize(diagonalDto.getSize());
        diagonalRepository.save(diagonal);
        return new ApiResponse("Diagonal saqlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public List<Diagonal> getDiagonalList(){
        List<Diagonal> diagonalList = diagonalRepository.findAll();
        return diagonalList;
    }


    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ApiResponse getDiagonalById(@PathVariable Integer id){
        if (!diagonalRepository.existsById(id)){
            return new ApiResponse("Bunday diaganal topilmadi.",false);
        }
        Optional<Diagonal> optionalDiagonal = diagonalRepository.findById(id);
        return new ApiResponse(optionalDiagonal.get(),true);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteDiagonale(@PathVariable Integer id){
        if (!diagonalRepository.existsById(id)){
            return new ApiResponse("Bunday diaganal topilmadi.",false);
        }
        diagonalRepository.deleteById(id);
        return new ApiResponse("Diagonal o'chirildi.",true);
    }




}
