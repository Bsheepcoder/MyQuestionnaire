package com.computer.network.service;

import com.computer.network.pojo.BaseResponse;
import com.computer.network.pojo.User;


public interface UserService {

    BaseResponse addUser(User user);

    BaseResponse login(User user);

}
