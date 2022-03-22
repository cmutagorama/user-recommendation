package com.pivot.userrecommendation.hashtag.service;

import com.pivot.userrecommendation.hashtag.dto.HashtagCount;
import com.pivot.userrecommendation.hashtag.dto.UserHashtagCount;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IHashtagService {
    List<HashtagCount> findHashtagsByUserId(BigInteger userId);
    List<UserHashtagCount> findUsersWithSimilarHashtagsWithCurrentUser(BigInteger userId, List<String> hashtags);
    Map<BigInteger, Double> calculateHashtagScore(BigInteger userId);
}
