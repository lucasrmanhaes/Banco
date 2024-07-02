package com.quantumbank.controller;

import com.quantumbank.dto.UserRegisterDto;
import com.quantumbank.model.UserModel;
import com.quantumbank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserModel createUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return userService.createUser(userRegisterDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping()
    public UserModel updateUser(@RequestBody UserModel userModel) {
        return userService.updateUser(userModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "email")
    public UserModel findUserByEmail(@RequestParam("email") String email){
        return userService.findUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "cpf")
    public UserModel findUserByCpf(@RequestParam("cpf") String cpf){
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
