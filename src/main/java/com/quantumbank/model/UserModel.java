package com.quantumbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

    @Data
    @Entity(name = "TB_FIN_USER")
    public class UserModel {
        @Id
        @GeneratedValue(generator = "UUID")
        private UUID id;
        private String name;
        private String cpf;
        private String email;
        private String password;
        private boolean enabled = true;
    }

