package com.computer.network.mapper;

import com.computer.network.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    int addUser(User user);

    User getUserByName(String name);
}
