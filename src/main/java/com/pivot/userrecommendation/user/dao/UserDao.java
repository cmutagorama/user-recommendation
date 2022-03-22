package com.pivot.userrecommendation.user.dao;

import com.pivot.userrecommendation.user.domain.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
public class UserDao implements IUserDao {
    private EntityManager entityManager;

    @Autowired
    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserInfo(BigInteger userId) {
        Query query = (Query) entityManager.createQuery("SELECT new com.pivot.userrecommendation.user.domain.User(u.userId, u.screenName, u.description) FROM User u WHERE u.userId = :user_id").setParameter("user_id", userId);

        return (User) query.getSingleResult();
    }
}
