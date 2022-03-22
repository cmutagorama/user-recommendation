package com.pivot.userrecommendation.hashtag.dto;

import com.pivot.userrecommendation.tweets.dto.IKeyValue;

import java.math.BigInteger;

public class UserHashtagCount implements IKeyValue {
    private BigInteger userId;

    private String hashtagName;

    private Long count;

    public UserHashtagCount() {}

    public UserHashtagCount(BigInteger userId, String hashtagName, Long count) {
        this.userId = userId;
        this.hashtagName = hashtagName;
        this.count = count;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getHashtagName() {
        return hashtagName;
    }

    public void setHashtagName(String hashtagName) {
        this.hashtagName = hashtagName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public BigInteger key() {
        return userId;
    }

    @Override
    public Long value() {
        return count;
    }
}
