package com.example.pcmarketspringsecurity.controller;

import com.example.pcmarketspringsecurity.entity.Category;
import com.example.pcmarketspringsecurity.others.ApiResponse;
import com.example.pcmarketspringsecurity.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;


    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ApiResponse addCategory(@RequestBody Category categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Kategorya saqlandi",true);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ApiResponse editCategory(@RequestBody Category categoryDto, @PathVariable Integer id){
        if (!categoryRepository.existsById(id)){
            return new ApiResponse("Bunday kategorya topilmadi.",true);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Kategorya tahrirlandi.",true);
    }


    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public List<Category> getCategoryList(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }


    @PreAuthorize(value = "hasAuthority('GET_ONE_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ApiResponse getCategoryById(@PathVariable Integer id){
        if (!categoryRepository.existsById(id)){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return new ApiResponse(optionalCategory.get(),true);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ApiResponse deleteCategory(@PathVariable Integer id){
        if (!categoryRepository.existsById(id)){
            return new ApiResponse("Bunday kategorya topilmadi.",false);
        }
        categoryRepository.deleteById(id);
        return new ApiResponse("Kategorya o'chirildi.",true);

    }

}






























