package com.quantumbank.dto;

import com.quantumbank.model.UserModel;
import lombok.Data;

import java.util.UUID;

@Data
public class UserViewDto {
    private UUID id;
    private String name;
    private String cpf;
    private String email;

    public UserViewDto(UserModel userModel) {
        this.id = userModel.getId();
        this.name = userModel.getName();
        this.cpf = userModel.getCpf();
        this.email = userModel.getEmail();
    }

}
