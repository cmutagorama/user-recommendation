package com.pivot.userrecommendation.tweets.dto;

import java.math.BigInteger;

public class HashtagText {
    private BigInteger userId;

    private BigInteger replyToUserId;

    private BigInteger retweetToUserId;

    private String text;

    private String hashtag;

    public HashtagText() {}

    public HashtagText(BigInteger userId, BigInteger replyToUserId, BigInteger retweetToUserId, String text, String hashtag) {
        this.userId = userId;
        this.replyToUserId = replyToUserId;
        this.retweetToUserId = retweetToUserId;
        this.text = text;
        this.hashtag = hashtag;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getReplyToUserId() {
        return replyToUserId;
    }

    public void setReplyToUserId(BigInteger replyToUserId) {
        this.replyToUserId = replyToUserId;
    }

    public BigInteger getRetweetToUserId() {
        return retweetToUserId;
    }

    public void setRetweetToUserId(BigInteger retweetToUserId) {
        this.retweetToUserId = retweetToUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
