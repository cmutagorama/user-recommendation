package com.pivot.userrecommendation.tweets.service;

import com.pivot.userrecommendation.core.service.BaseService;
import com.pivot.userrecommendation.hashtag.service.IHashtagService;
import com.pivot.userrecommendation.tweets.dao.ITweetDao;
import com.pivot.userrecommendation.tweets.dto.HashtagText;
import com.pivot.userrecommendation.tweets.domain.Tweet;
import com.pivot.userrecommendation.tweets.dto.UserCount;
import com.pivot.userrecommendation.tweets.dto.ContactedUserDto;
import com.pivot.userrecommendation.user.domain.User;
import com.pivot.userrecommendation.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service
public class TweetService extends BaseService implements ITweetService {
    ITweetDao tweetDao;

    @Autowired
    public TweetService(@Qualifier("tweetDao") ITweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    @Autowired
    public IHashtagService hashtagService;

    @Autowired
    public IUserService userService;

    @Override
    public List<UserCount> findReplyCountFromMe(BigInteger userId) {
        return tweetDao.getReplyCountFromMe(userId);
    }

    @Override
    public List<UserCount> findReplyCountFromOthers(BigInteger userId) {
        return tweetDao.getReplyCountFromOthers(userId);
    }

    @Override
    public List<UserCount> findRetweetCountFromMe(BigInteger userId) {
        return tweetDao.getRetweetCountFromMe(userId);
    }

    @Override
    public List<UserCount> findRetweetCountFromOthers(BigInteger userId) {
        return tweetDao.getRetweetCountFromOthers(userId);
    }

    @Override
    public List<HashtagText> findTweetsByUserIdAndTweetType(BigInteger userId, String tweetType) {
        return  tweetDao.getTweetsByUserIdAndTweetType(userId, tweetType);
    }

    @Override
    public List<ContactedUserDto> calculateRankingScore(BigInteger userId, String type, String phrase, String hashtag) {
        Map<BigInteger, Double> interactionScore = this.calculateInteractionScore(userId);
        Map<BigInteger, Double> hashtagScore = this.hashtagService.calculateHashtagScore(userId);
        Map<BigInteger, Double> keywordScore = this.calculateKeywordScore(userId, type, phrase, hashtag);

        Map<BigInteger, Double> rankingScoreMap = multiplyMaps(interactionScore, hashtagScore, keywordScore);

        List<ContactedUserDto> contactedUserDtoList = new ArrayList<>();
        for (Map.Entry<BigInteger, Double> user: rankingScoreMap.entrySet()) {
            ContactedUserDto contactedUser = getUserInfo(user.getKey(), userId, type, user.getValue());
            contactedUserDtoList.add(contactedUser);
        }
        Collections.sort(contactedUserDtoList);
        return contactedUserDtoList;
    }

    private ContactedUserDto getUserInfo(BigInteger userId, BigInteger originalUserId, String type, Double rankingScore) {
        Tweet latestTweet = tweetDao.getUserLatestTweet(userId, originalUserId, type);
        User userDetails = userService.getUserInfo(userId);

        ContactedUserDto userInfo = new ContactedUserDto();
        userInfo.setUserId(userDetails.getUserId());
        userInfo.setUserScreenName(userDetails.getScreenName());
        userInfo.setUserDescription(userDetails.getDescription());
        userInfo.setLatestTweet(latestTweet);
        userInfo.setRankingScore(rankingScore);

        return userInfo;
    }

    private Map<BigInteger, Double> multiplyMaps(Map<BigInteger, Double> map1, Map<BigInteger, Double> map2, Map<BigInteger, Double> map3) {
        Set<BigInteger> keySet = new HashSet<>();
        keySet.addAll(map1.keySet());
        keySet.addAll(map2.keySet());
        keySet.addAll(map3.keySet());

        Map<BigInteger, Double> map = new HashMap<BigInteger, Double>();
        Double val1, val2, val3;
        for (BigInteger key : keySet) {
            val1 = map1.get(key);
            val1 = (val1 == null ? 1 : val1);
            val2 = map2.get(key);
            val2 = (val2 == null ? 1 : val2);
            val3 = map3.get(key);
            val3 = (val3 == null ? 1 : val3);
            map.put(key, convertToThreeDecimals(val1 * val2 * val3));
        }

        return map;
    }

    public Map<BigInteger, Double> calculateInteractionScore(BigInteger userId) {
        Map<BigInteger, Long> repliesFromMe = convertListToMap(this.findReplyCountFromMe(userId));
        Map<BigInteger, Long> repliesFromOthers = convertListToMap(this.findReplyCountFromOthers(userId));

        Map<BigInteger, Long> retweetsFromMe = convertListToMap(this.findRetweetCountFromMe(userId));
        Map<BigInteger, Long> retweetsFromOthers = convertListToMap(this.findRetweetCountFromOthers(userId));

        Map<BigInteger, Long> replyCounts = sumMaps(repliesFromMe, repliesFromOthers);
        Map<BigInteger, Long> retweetCounts = sumMaps(retweetsFromMe, retweetsFromOthers);

        return computeIS(replyCounts, retweetCounts);
    }

    public Map<BigInteger, Double> computeIS(Map<BigInteger, Long> map1, Map<BigInteger, Long> map2) {
        Set<BigInteger> keySet = new HashSet<>();
        keySet.addAll(map1.keySet());
        keySet.addAll(map2.keySet());

        Map<BigInteger, Double> map3 = new HashMap<BigInteger, Double>();
        Long val1, val2;
        for (BigInteger key : keySet) {
            val1 = map1.get(key);
            val1 = (val1 == null ? 0 : val1);
            val2 = map2.get(key);
            val2 = (val2 == null ? 0 : val2);
            map3.put(key, Math.log(1 + 2 * val1 + val2));
        }

        return map3;
    }

    private Double convertToThreeDecimals(Double digit) {
        return (double) (Math.round(digit * 1000.0) / 1000.0);
    }

    private Map<BigInteger, Long> sumMaps(Map<BigInteger, Long> map1, Map<BigInteger, Long> map2) {
        Set<BigInteger> keySet = new HashSet<>();
        keySet.addAll(map1.keySet());
        keySet.addAll(map2.keySet());

        Map<BigInteger, Long> map3 = new HashMap<BigInteger, Long>();
        Long val1, val2;
        for (BigInteger key : keySet) {
            val1 = map1.get(key);
            val1 = (val1 == null ? 0 : val1);
            val2 = map2.get(key);
            val2 = (val2 == null ? 0 : val2);
            map3.put(key, val1 + val2);
        }

        return map3;
    }

    public Map<BigInteger, Double> calculateKeywordScore(BigInteger userId, String tweeType, String phrase, String hashtag) {
        Map<BigInteger, Integer> totalMatchesMap = new HashMap<>();
        Integer phraseCount = 0;

        List<HashtagText> usersText = findTweetsByUserIdAndTweetType(userId, tweeType);
        for (HashtagText userText: usersText) {
            BigInteger key;

            // set userId/replyToUserId/retweetToUserId as key based on the tweet type
            if (tweeType.equals("reply")) {
                key = getUserOnReply(userText, userId);
            } else if (tweeType.equals("retweet")) {
                key = getUserOnRetweet(userText, userId);
            } else {
                key = userText.getReplyToUserId() == null ? getUserOnRetweet(userText, userId) : getUserOnReply(userText, userId);
            }

            /* Get phrase match */
            if (userText.getText().contains(phrase)) {
                Integer count = countOverlaps(userText.getText(), phrase);
                incrementCountToExistingKey(totalMatchesMap, key, count);
            }

            // Get hashtag match
            List<String> hashtags = Arrays.asList(userText.getHashtag().split(","));
            for (String tag: hashtags) {
                if (tag.equalsIgnoreCase(hashtag)) {
                    incrementCountToExistingKey(totalMatchesMap, key, 1);
                }
            }
        }

        Map<BigInteger, Double> finalMap = new HashMap<>();
        for(Map.Entry<BigInteger, Integer> entry: totalMatchesMap.entrySet()) {
            finalMap.put(entry.getKey(), computeKS(entry.getValue()));
        }

        return finalMap;
    }

    private Double computeKS(Integer digit) {
        return 1 + Math.log(digit + 1);
    }

    public static int countOverlaps(String source, String lookFor) {
        int count = 0;
        int i = -1;

        while (i != 0) {
            i = source.indexOf(lookFor, i) + 1;
            if (i != 0) count++;
        }
        return count;
    }

    private void incrementCountToExistingKey(Map<BigInteger, Integer> map, BigInteger key, Integer increment) {
        if (map.containsKey(key)) {
            Integer currentCount = map.get(key);
            map.put(key, currentCount + increment);
        } else {
            map.put(key, increment);
        }
    }

    private BigInteger getUserOnReply(HashtagText hashtagText, BigInteger userId) {
        return !Objects.equals(hashtagText.getUserId(), userId) ? hashtagText.getUserId() : hashtagText.getReplyToUserId();
    }

    private BigInteger getUserOnRetweet(HashtagText hashtagText, BigInteger userId) {
        return !Objects.equals(hashtagText.getUserId(), userId) ? hashtagText.getUserId() : hashtagText.getRetweetToUserId();
    }
}
