package com.pivot.userrecommendation.tweets.dto;

import java.math.BigInteger;

public class UserCount implements IKeyValue {
    private BigInteger id;

    private Long count;

    public UserCount() {}

    public UserCount(BigInteger id, Long count) {
        this.id = id;
        this.count = count;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public BigInteger key() {
        return id;
    }

    @Override
    public Long value() {
        return count;
    }
}
