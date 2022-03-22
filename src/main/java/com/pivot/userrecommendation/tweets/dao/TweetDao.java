package com.pivot.userrecommendation.tweets.dao;

import com.pivot.userrecommendation.tweets.dto.HashtagText;
import com.pivot.userrecommendation.tweets.domain.Tweet;
import com.pivot.userrecommendation.tweets.dto.UserCount;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

@Repository
public class TweetDao implements ITweetDao {
    private EntityManager entityManager;

    @Autowired
    public TweetDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserCount> getReplyCountFromMe(BigInteger userId) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.dto.UserCount(t.replyToUserId, COUNT (t)) FROM Tweet t WHERE t.userId =:user_id AND t.tweetType = 'reply' GROUP BY t.replyToUserId").setParameter("user_id", userId);

        return (List<UserCount>) query.getResultList();
    }

    @Override
    public List<UserCount> getReplyCountFromOthers(BigInteger userId) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.dto.UserCount(t.userId, COUNT (t)) FROM Tweet t WHERE t.replyToUserId = :user_id AND t.tweetType = 'reply' GROUP BY t.userId").setParameter("user_id", userId);

        return (List<UserCount>) query.getResultList();
    }

    @Override
    public List<UserCount> getRetweetCountFromMe(BigInteger userId) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.dto.UserCount(t.retweetToUserId, COUNT (t)) FROM Tweet t WHERE t.userId = :user_id AND t.tweetType = 'retweet' GROUP BY t.retweetToUserId").setParameter("user_id", userId);

        return (List<UserCount>) query.getResultList();
    }

    @Override
    public List<UserCount> getRetweetCountFromOthers(BigInteger userId) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.dto.UserCount(t.userId, COUNT (t)) FROM Tweet t WHERE t.retweetToUserId = :user_id AND t.tweetType = 'retweet' GROUP BY t.userId").setParameter("user_id", userId);

        return (List<UserCount>) query.getResultList();
    }

    @Override
    public List<HashtagText> getTweetsByUserIdAndTweetType(BigInteger userId, String tweetType) {
        Query query;
        if (tweetType.equalsIgnoreCase("both")) {
            query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.dto.HashtagText(t.userId, t.replyToUserId, t.retweetToUserId, t.text, t.hashtags) from Tweet t where (t.userId = :user_id or t.replyToUserId = :user_id or t.retweetToUserId = :user_id) and t.tweetType = 'reply' or t.tweetType = 'retweet'").setParameter("user_id", userId);
        } else {
            query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.dto.HashtagText(t.userId, t.replyToUserId, t.retweetToUserId, t.text, t.hashtags) from Tweet t where (t.userId = :user_id or t.replyToUserId = :user_id or t.retweetToUserId = :user_id) and t.tweetType = :tweet_type").setParameter("user_id", userId).setParameter("tweet_type", tweetType);
        }

        return (List<HashtagText>) query.getResultList();
    }

    @Override
    public Tweet getUserLatestTweet(BigInteger userId, BigInteger originalUserId, String tweetType) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.tweets.domain.Tweet(t.tweetId, t.createdAt, t.text, t.userId, t.userScreenName, t.userDescription, t.tweetType, t.replyToTweetId, t.replyToUserId, t.retweetToTweetId, t.retweetToUserId, t.hashtags) FROM Tweet t where (t.userId = :user_id and (t.replyToUserId = :original_user_id or t.retweetToUserId = :original_user_id)) or (t.userId = :original_user_id and (t.replyToUserId = :user_id or t.retweetToUserId = :user_id)) and t.tweetType = :tweetType order by t.createdAt desc").setParameter("user_id", userId).setParameter("original_user_id", originalUserId) .setParameter("tweetType", tweetType);

        return (Tweet) query.getResultList().stream().findFirst().orElse(null);
    }
}
