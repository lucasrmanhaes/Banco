package com.quantumbank.service;

import com.quantumbank.dto.UserRegisterDto;
import com.quantumbank.dto.UserViewDto;
import com.quantumbank.exception.UserNotFoundException;
import com.quantumbank.model.UserModel;
import com.quantumbank.repository.UserRepository;
import com.quantumbank.utils.KeepNonNullAttributes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> createUser(UserRegisterDto userRegisterDto) {
        Optional<UserModel> userModelOptionalUsingEmail = userRepository.findByEmail(userRegisterDto.getEmail());
        Optional<UserModel> userModelOptionalUsingCpf = userRepository.findByCpf(userRegisterDto.getCpf());
        if(userModelOptionalUsingEmail.isEmpty() && userModelOptionalUsingCpf.isEmpty()) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(userRegisterDto, userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new UserViewDto(userRepository.save(userModel)));
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
    }

    public ResponseEntity<Object> updateUser(UserModel userModel) {
        Optional<UserModel> userModelOptionalUsingEmail = userRepository.findByEmail(userModel.getEmail());
        if(userModelOptionalUsingEmail.isPresent()) {
            UserModel user = userModelOptionalUsingEmail.get();
            KeepNonNullAttributes.copyNonNullProperties(userModel, user);
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewDto(userRepository.save(user)));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    public ResponseEntity<Object> findUserByEmail(String email){
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);
        if(userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewDto(userModelOptional.get()));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    public ResponseEntity<Object> findUserByCpf(String cpf){
        Optional<UserModel> userModelOptional = userRepository.findByCpf(cpf);
        if(userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserViewDto(userModelOptional.get()));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    public void deleteUserByEmail(String email){
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);
        if(userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            userModel.setEnabled(false);
            userRepository.save(userModel);
        }
        else{
            throw new UserNotFoundException("User not found");
        }
    }

    public void deleteUserByCpf(String cpf){
        Optional<UserModel> userModelOptional = userRepository.findByCpf(cpf);
        if(userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            userModel.setEnabled(false);
            userRepository.save(userModel);
        }
        else{
            throw new UserNotFoundException("User not found");
        }
    }

}
