package com.computer.network.serviceImpl;

import com.computer.network.mapper.UserMapper;
import com.computer.network.service.UserService;
import com.computer.network.pojo.BaseResponse;
import com.computer.network.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";

    @Autowired
    UserMapper userMapper;

    @Override
    public BaseResponse addUser(User user) {
        try {
            userMapper.addUser(user);
            return BaseResponse.buildSuccess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return BaseResponse.buildFailure(ACCOUNT_EXIST);
        }
    }

    @Override
    public BaseResponse login(User user) {
        User foundUser = userMapper.getUserByName(user.getName());
        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword()))
            return BaseResponse.buildFailure(ACCOUNT_INFO_ERROR);
        return BaseResponse.buildSuccess(foundUser);
    }

    @Override
    public BaseResponse information(User user){
        return BaseResponse.buildSuccess(userMapper.getUserByName(user.getName()));
    }
}
