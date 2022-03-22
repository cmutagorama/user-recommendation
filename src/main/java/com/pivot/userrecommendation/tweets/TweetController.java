package com.pivot.userrecommendation.tweets;

import com.pivot.userrecommendation.tweets.dto.UserCount;
import com.pivot.userrecommendation.tweets.dto.ContactedUserDto;
import com.pivot.userrecommendation.tweets.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path="v1/api")
public class TweetController {
    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/q2")
    private ResponseEntity<List<ContactedUserDto>> findRankingScore(@RequestParam BigInteger userId, @RequestParam String type, @RequestParam String phrase, @RequestParam String hashtag) {
        return new ResponseEntity<List<ContactedUserDto>>(tweetService.calculateRankingScore(userId, type, phrase, hashtag), HttpStatus.OK);
    }
}
