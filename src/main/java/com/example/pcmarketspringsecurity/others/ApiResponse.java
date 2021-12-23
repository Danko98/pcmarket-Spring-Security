package com.example.pcmarketspringsecurity.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String massage;
    private boolean success;
    private Object object;

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }


    public ApiResponse(Object object, boolean success) {
        this.success = success;
        this.object = object;
    }
}
