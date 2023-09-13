package com.HngTaskTwo.HngTaskTwo.service;

import com.HngTaskTwo.HngTaskTwo.dto.UpdateRequestDto;
import com.HngTaskTwo.HngTaskTwo.dto.UserRequestDto;
import com.HngTaskTwo.HngTaskTwo.entity.User;
import com.HngTaskTwo.HngTaskTwo.repository.UserRepository;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequestDto request) {
        if (request.getEmail() == null) {
            return User.builder().email(null).name(null).id(null).build();
        }
//        if()//check if the email already exists in database
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();
        User savedUser = userRepository.save(user);
        return User.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .build();
    }

    @Override
    public User updateUser(UpdateRequestDto request) {
   User user = userRepository.findById(request.getId())
           .orElseThrow(()->new NoSuchElementException("User not found"));
   if(request.getEmail() != null){
       user.setEmail(request.getEmail());
   }
   if(request.getName() != null){
            user.setName(request.getName());
        }
   User savedUser = userRepository.save(user);
        return User.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .build();

    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));
        userRepository.delete(user);
        return "User has been successfully deleted";
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
