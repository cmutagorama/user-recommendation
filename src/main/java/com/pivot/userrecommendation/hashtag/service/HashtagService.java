package com.pivot.userrecommendation.hashtag.service;

import com.pivot.userrecommendation.hashtag.dao.IHashtagDao;
import com.pivot.userrecommendation.hashtag.dto.HashtagCount;
import com.pivot.userrecommendation.hashtag.dto.UserHashtagCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HashtagService  implements IHashtagService {
    IHashtagDao hashtagDao;

    @Autowired
    HashtagService(@Qualifier("hashtagDao") IHashtagDao hashtagDao) {
        this.hashtagDao = hashtagDao;
    }

    @Override
    public List<HashtagCount> findHashtagsByUserId(BigInteger userId) {
        return hashtagDao.getHashtagsByUserId(userId);
    }

    @Override
    public List<UserHashtagCount> findUsersWithSimilarHashtagsWithCurrentUser(BigInteger userId, List<String> hashtags) {
        return hashtagDao.getUsersWithSimilarHashtagsWithCurrentUser(userId, hashtags);
    }

    @Override
    public Map<BigInteger, Double> calculateHashtagScore(BigInteger userId) {
        List<HashtagCount> currentUserHashtags = this.findHashtagsByUserId(userId);
        List<String> currentUserHashtagsList = (List<String>) currentUserHashtags.stream().map(HashtagCount::getHashtagName).collect(Collectors.toList());
        List<UserHashtagCount> allUsersHashtagCount = this.findUsersWithSimilarHashtagsWithCurrentUser(userId, currentUserHashtagsList);

        Map<BigInteger, Long> sameTagCountMap = new HashMap<>();

        allUsersHashtagCount.forEach(allUHCount -> {
            HashtagCount elt = currentUserHashtags.stream().filter(uh -> allUHCount.getHashtagName().equals(uh.getHashtagName())).collect(Collectors.toList()).get(0);
            if (elt != null) {
                Long sum = allUHCount.getCount() + elt.getCount();
                allUHCount.setCount(sum);
            }

            if (sameTagCountMap.containsKey(allUHCount.getUserId())) {
                Long finalSum = sameTagCountMap.get(allUHCount.getUserId()) + allUHCount.getCount();
                sameTagCountMap.put(allUHCount.getUserId(), finalSum);
            } else {
                sameTagCountMap.put(allUHCount.getUserId(), allUHCount.getCount());
            }
        });

        Map<BigInteger, Double> finalMap = new HashMap<>();
        for(Map.Entry<BigInteger, Long> entry: sameTagCountMap.entrySet()) {
            finalMap.put(entry.getKey(), computeHS(entry.getValue()));
        }

        return finalMap;
    }

    private Double computeHS(Long digit) {
        Double tagCount;
        if (digit > 10) {
            tagCount = 1 + Math.log(1 + digit - 10);
        } else {
            tagCount = Double.valueOf(1);
        }

        return tagCount;
    }
}