package com.pivot.userrecommendation.core.service;

import com.pivot.userrecommendation.tweets.dto.IKeyValue;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService implements IBaseService {
    @Override
    public <T extends IKeyValue> Map<BigInteger, Long> convertListToMap(List<T> list) {
        Map<BigInteger, Long> map = new HashMap<>();
        for (IKeyValue i: list) {
            map.put(i.key(), i.value());
        }

        return map;
    }
}
