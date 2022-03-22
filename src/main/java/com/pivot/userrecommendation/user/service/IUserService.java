package com.pivot.userrecommendation.user.service;

import com.pivot.userrecommendation.user.domain.User;

import java.math.BigInteger;

public interface IUserService {
    User getUserInfo(BigInteger userId);
}
