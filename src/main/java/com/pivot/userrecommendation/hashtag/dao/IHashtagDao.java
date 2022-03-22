package com.pivot.userrecommendation.hashtag.dao;

import com.pivot.userrecommendation.hashtag.dto.HashtagCount;
import com.pivot.userrecommendation.hashtag.dto.UserHashtagCount;

import java.math.BigInteger;
import java.util.List;

public interface IHashtagDao {
    List<HashtagCount> getHashtagsByUserId(BigInteger userId);
    List<UserHashtagCount> getUsersWithSimilarHashtagsWithCurrentUser(BigInteger userId, List<String> currentUserHashtags);
}
