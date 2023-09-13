package com.HngTaskTwo.HngTaskTwo.service;

import com.HngTaskTwo.HngTaskTwo.dto.UpdateRequestDto;
import com.HngTaskTwo.HngTaskTwo.dto.UserRequestDto;
import com.HngTaskTwo.HngTaskTwo.entity.User;

public interface UserService {
    User createUser (UserRequestDto request);
    User updateUser(Long userId, UserRequestDto request);
    String deleteUser(Long id);
    User getUser(Long id);

}
