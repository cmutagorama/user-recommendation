package com.pivot.userrecommendation.tweets.dto;

import com.pivot.userrecommendation.tweets.domain.Tweet;

import java.math.BigInteger;

public class ContactedUserDto implements Comparable<ContactedUserDto> {
    private BigInteger userId;
    private String userScreenName;
    private String userDescription;
    private Double rankingScore;
    private Tweet latestTweet;

    public ContactedUserDto() {}

    public ContactedUserDto(BigInteger userId, String userScreenName, String userDescription, Double rankingScore, Tweet latestTweet) {
        this.userId = userId;
        this.userScreenName = userScreenName;
        this.userDescription = userDescription;
        this.rankingScore = rankingScore;
        this.latestTweet = latestTweet;
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

    public Double getRankingScore() {
        return rankingScore;
    }

    public void setRankingScore(Double rankingScore) {
        this.rankingScore = rankingScore;
    }

    public Tweet getLatestTweet() {
        return latestTweet;
    }

    public void setLatestTweet(Tweet latestTweet) {
        this.latestTweet = latestTweet;
    }

    @Override
    public int compareTo(ContactedUserDto contactedUserDto) {
        return (int)(contactedUserDto.getRankingScore() - this.rankingScore);
    }
}
