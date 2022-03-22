package com.pivot.userrecommendation.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "user_account")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private BigInteger userId;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "description")
    private String description;

    public User() {}

    public User(BigInteger userId, String screenName, String description) {
        this.userId = userId;
        this.screenName = screenName;
        this.description = description;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}