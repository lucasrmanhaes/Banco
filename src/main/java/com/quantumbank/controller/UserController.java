package com.quantumbank.controller;

import com.quantumbank.dto.UserRegisterDto;
import com.quantumbank.dto.UserViewDto;
import com.quantumbank.model.UserModel;
import com.quantumbank.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(description = "Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return userService.createUser(userRegisterDto);
    }
    @Operation(description = "Update user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping()
    public ResponseEntity<Object> updateUser(@RequestBody UserModel userModel) {
        return userService.updateUser(userModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "email")
    public ResponseEntity<Object> findUserByEmail(@RequestParam("email") String email){
        return userService.findUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "cpf")
    public ResponseEntity<Object> findUserByCpf(@RequestParam("cpf") String cpf){
        return userService.findUserByCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(params = "email")
    public void removeUserByEmail(@RequestParam("email") String email){
        userService.deleteUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(params = "cpf")
    public void removeUserByCpf(@RequestParam("cpf") String cpf){
        userService.deleteUserByCpf(cpf);
    }

}
