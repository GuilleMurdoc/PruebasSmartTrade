package com.example.SpringProjectPrueba.db.dtos;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;


public class DTO {
    public String toJSON(){
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
