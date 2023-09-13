package com.HngTaskTwo.HngTaskTwo.controller;

import com.HngTaskTwo.HngTaskTwo.dto.UpdateRequestDto;
import com.HngTaskTwo.HngTaskTwo.dto.UserRequestDto;
import com.HngTaskTwo.HngTaskTwo.entity.User;
import com.HngTaskTwo.HngTaskTwo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }
    @PutMapping("/{user_id}")
    public User UpdateUser(@PathVariable Long user_id, @RequestBody UserRequestDto request) {
        return userService.updateUser(user_id,request);
    }
    @DeleteMapping("/{user_id}")
    public String  deleteUser(@PathVariable Long user_id) {
        return userService.deleteUser(user_id);
    }
    @GetMapping("/{user_id}")
    public User getUser(@PathVariable Long user_id) {
        return userService.getUser(user_id);
    }
}
