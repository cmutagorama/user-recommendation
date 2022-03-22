package com.pivot.userrecommendation.user.service;

import com.pivot.userrecommendation.user.dao.IUserDao;
import com.pivot.userrecommendation.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserService implements IUserService {
    IUserDao userDao;

    @Autowired
    public UserService(@Qualifier("userDao") IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserInfo(BigInteger userId) {
        return userDao.getUserInfo(userId);
    }
}
