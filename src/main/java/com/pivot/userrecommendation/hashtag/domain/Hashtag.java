package com.pivot.userrecommendation.hashtag.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @Column(name = "tweet_id", nullable = false)
    private BigInteger tweetId;

    @Column(name = "user_id", nullable = false)
    private BigInteger userId;

    @Column(name = "hashtag_name", nullable = false)
    private String hashtagName;

    public Hashtag() {}

    public Hashtag(BigInteger tweetId, BigInteger userId, String hashtagName) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.hashtagName = hashtagName;
    }

    public BigInteger getTweetId() {
        return tweetId;
    }

    public void setTweetId(BigInteger tweetId) {
        this.tweetId = tweetId;
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
}
