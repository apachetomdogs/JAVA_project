package com.ja.finalproject.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.UserDto;

@Mapper
public interface UserSqlMapper {

    public void createUser(UserDto userDto);
    // insert, update, delete = void로 해도 된다. 
    // select는 꼭 그거에 적합한 return 타입을 써줘야 한다!!!!!!
    public UserDto findByUserIdAndPassword(UserDto userDto);
    public UserDto findById(int id);
}
