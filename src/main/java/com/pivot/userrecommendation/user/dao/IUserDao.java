package com.pivot.userrecommendation.user.dao;

import com.pivot.userrecommendation.user.domain.User;

import java.math.BigInteger;

public interface IUserDao {
    User getUserInfo(BigInteger userId);
}
