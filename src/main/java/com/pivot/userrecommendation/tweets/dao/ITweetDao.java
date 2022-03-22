package com.pivot.userrecommendation.tweets.dao;

import com.pivot.userrecommendation.tweets.dto.HashtagText;
import com.pivot.userrecommendation.tweets.domain.Tweet;
import com.pivot.userrecommendation.tweets.dto.UserCount;

import java.math.BigInteger;
import java.util.List;

public interface ITweetDao {
    List<UserCount> getReplyCountFromMe(BigInteger userId);
    List<UserCount> getReplyCountFromOthers(BigInteger userId);
    List<UserCount> getRetweetCountFromMe(BigInteger userId);
    List<UserCount> getRetweetCountFromOthers(BigInteger userId);
    List<HashtagText> getTweetsByUserIdAndTweetType(BigInteger userId, String tweetType);
    Tweet getUserLatestTweet(BigInteger userId, BigInteger originalUserId, String tweetType);
}
