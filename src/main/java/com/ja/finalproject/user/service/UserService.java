package com.ja.finalproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

@Service
public class UserService {

    @Autowired
    private UserSqlMapper userSqlMapper;

    public void register(UserDto userDto) {
        // ...
        userSqlMapper.createUser(userDto);

    }

    public UserDto getUserByUserIdAndPassword(UserDto userDto) {
        UserDto result = userSqlMapper.findByUserIdAndPassword(userDto);
        return result;
    }

}
