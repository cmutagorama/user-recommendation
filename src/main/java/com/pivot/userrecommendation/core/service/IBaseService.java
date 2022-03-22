package com.pivot.userrecommendation.core.service;

import com.pivot.userrecommendation.tweets.dto.IKeyValue;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IBaseService {
    public <T extends IKeyValue> Map<BigInteger, Long> convertListToMap(List<T> list);
}
