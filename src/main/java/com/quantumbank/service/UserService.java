package com.quantumbank.service;

import com.quantumbank.dto.UserRegisterDto;
import com.quantumbank.exception.UserNotFoundException;
import com.quantumbank.model.UserModel;
import com.quantumbank.repository.UserRepository;
import com.quantumbank.utils.KeepNonNullAttributes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(UserRegisterDto userRegisterDto) {
        Optional<UserModel> userModelOptional = userRepository.findByEmail(userRegisterDto.getEmail());
        if(userModelOptional.isEmpty()) {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(userRegisterDto, userModel);
            return userRepository.save(userModel);
        }
        else{
            throw new UserNotFoundException("Email is already registered");
        }
    }

    public UserModel updateUser(UserModel userModel) {
        Optional<UserModel> userModelOptional = userRepository.findByEmail(userModel.getEmail());
        if(userModelOptional.isPresent()) {
            UserModel user = userModelOptional.get();
            KeepNonNullAttributes.copyNonNullProperties(userModel, user);
            return userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    public UserModel findUserByEmail(String email){
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);
        if(userModelOptional.isPresent()) {
            return userModelOptional.get();
        }
        else{
            throw new UserNotFoundException("User not found");
        }
    }

    public UserModel findUserByCpf(String cpf){
        Optional<UserModel> userModelOptional = userRepository.findByCpf(cpf);
        if(userModelOptional.isPresent()) {
            return userModelOptional.get();
        }
        else{
            throw new UserNotFoundException("User not found");
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
