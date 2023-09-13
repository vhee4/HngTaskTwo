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
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public User createUser(UserRequestDto request) {
    String onlyStringRegex = "(^[a-zA-Z]+$)";
    String onlyEmailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    boolean isExist = userRepository.existsByEmail(request.getEmail());

        if (!isInputValid(request.getEmail(), onlyEmailRegex)) {
            return User.builder()
                    .email("Email must be a string and must contain @ symbol")
                    .build();
//            throw new IllegalArgumentException("Email must be a string.");
        }
        if (!isInputValid(request.getName(), onlyStringRegex)) {
            return User.builder()
                    .name("Name must be a string")
                    .build();
        }
        if(isExist){
            return User.builder()
                    .email("Email already exists")
                    .build();
        }

//        if()//check if the email already exists in database
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();
        User savedUser = userRepository.save(user);
        return User.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .build();
    }

    @Override
    public User updateUser(Long userId, UserRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getName() != null) {
            user.setName(request.getName());
        }
   User savedUser = userRepository.save(user);
        return User.builder()
                .userId(savedUser.getUserId())
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
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    private boolean isInputValid(String input, String regex) {
        return Pattern.compile(regex)
                .matcher(input)
                .matches();
    }
}
