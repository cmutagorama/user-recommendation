package com.pivot.userrecommendation.tweets.service;

import com.pivot.userrecommendation.core.service.IBaseService;
import com.pivot.userrecommendation.tweets.dto.HashtagText;
import com.pivot.userrecommendation.tweets.dto.UserCount;
import com.pivot.userrecommendation.tweets.dto.ContactedUserDto;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ITweetService extends IBaseService {
    List<UserCount> findReplyCountFromMe(BigInteger userId);
    List<UserCount> findReplyCountFromOthers(BigInteger userId);
    List<UserCount> findRetweetCountFromMe(BigInteger userId);
    List<UserCount> findRetweetCountFromOthers(BigInteger userId);
    List<HashtagText> findTweetsByUserIdAndTweetType(BigInteger userId, String tweetType);
    Map<BigInteger, Double> calculateInteractionScore(BigInteger userId);
    List<ContactedUserDto> calculateRankingScore(BigInteger userId, String type, String phrase, String hashtag);
}
