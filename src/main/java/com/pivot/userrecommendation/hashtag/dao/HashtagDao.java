package com.pivot.userrecommendation.hashtag.dao;

import com.pivot.userrecommendation.hashtag.dto.HashtagCount;
import com.pivot.userrecommendation.hashtag.dto.UserHashtagCount;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

@Repository
public class HashtagDao implements IHashtagDao {
    private EntityManager entityManager;

    @Autowired
    public HashtagDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<HashtagCount> getHashtagsByUserId(BigInteger userId) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.hashtag.dto.HashtagCount(h.hashtagName, COUNT (h)) FROM Hashtag h WHERE h.userId = :user_id GROUP BY h.hashtagName").setParameter("user_id", userId);

        return (List<HashtagCount>) query.getResultList();
    }

    @Override
    public List<UserHashtagCount> getUsersWithSimilarHashtagsWithCurrentUser(BigInteger userId, List<String> currentUserHashtags) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.hashtag.dto.UserHashtagCount(h.userId, h.hashtagName, COUNT (h)) FROM Hashtag h WHERE h.hashtagName in ( :current_user_hashtags) and h.userId <> :user_id GROUP BY h.userId, h.hashtagName ORDER BY h.userId");
        query.setParameter("user_id", userId).setParameter("current_user_hashtags", currentUserHashtags);

        return (List<UserHashtagCount>) query.getResultList();
    }
}
