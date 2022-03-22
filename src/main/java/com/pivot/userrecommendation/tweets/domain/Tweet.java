package com.pivot.userrecommendation.tweets.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "tweet")
public class Tweet {
    @Id
    @Column(name = "tweet_id", nullable = false)
    private BigInteger tweetId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "user_id", nullable = false)
    private BigInteger userId;

    @Column(name = "user_screen_name")
    private String userScreenName;

    @Column(name = "user_description")
    private String userDescription;

    @Column(name = "tweet_type")
    private String tweetType;

    @Column(name = "reply_to_tweet_id")
    private BigInteger replyToTweetId;

    @Column(name = "reply_to_user_id")
    private BigInteger replyToUserId;

    @Column(name = "retweet_to_tweet_id")
    private BigInteger retweetToTweetId;

    @Column(name = "retweet_to_user_id")
    private BigInteger retweetToUserId;

    @Column(name = "hashtags")
    private String hashtags;

    public Tweet() {}

    public Tweet(
            BigInteger tweetId, LocalDateTime createdAt, String text,
            BigInteger userId, String userScreenName,
            String userDescription, String tweetType,
            BigInteger replyToTweetId, BigInteger replyToUserId,
            BigInteger retweetToTweetId, BigInteger retweetToUserId,
            String hashtags
    ) {
        this.tweetId = tweetId;
        this.createdAt = createdAt;
        this.text = text;
        this.userId = userId;
        this.userScreenName = userScreenName;
        this.userDescription = userDescription;
        this.tweetType = tweetType;
        this.replyToTweetId = replyToTweetId;
        this.replyToUserId = replyToUserId;
        this.retweetToTweetId = retweetToTweetId;
        this.retweetToUserId = retweetToUserId;
        this.hashtags = hashtags;
    }

    public BigInteger getTweetId() {
        return tweetId;
    }

    public void setTweetId(BigInteger tweetId) {
        this.tweetId = tweetId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getTweetType() {
        return tweetType;
    }

    public void setTweetType(String tweetType) {
        this.tweetType = tweetType;
    }

    public BigInteger getReplyToTweetId() {
        return replyToTweetId;
    }

    public void setReplyToTweetId(BigInteger replyToTweetId) {
        this.replyToTweetId = replyToTweetId;
    }

    public BigInteger getReplyToUserId() {
        return replyToUserId;
    }

    public void setReplyToUserId(BigInteger replyToUserId) {
        this.replyToUserId = replyToUserId;
    }

    public BigInteger getRetweetToTweetId() {
        return retweetToTweetId;
    }

    public void setRetweetToTweetId(BigInteger retweetToTweetId) {
        this.retweetToTweetId = retweetToTweetId;
    }

    public BigInteger getRetweetToUserId() {
        return retweetToUserId;
    }

    public void setRetweetToUserId(BigInteger retweetToUserId) {
        this.retweetToUserId = retweetToUserId;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }
}