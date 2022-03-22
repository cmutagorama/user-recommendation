package com.pivot.userrecommendation.hashtag.dto;

public class HashtagCount implements IHKeyValue {
    private String hashtagName;

    private Long count;

    public HashtagCount() {}

    public HashtagCount(String hashtagName, Long count) {
        this.hashtagName = hashtagName;
        this.count = count;
    }

    public String getHashtagName() {
        return hashtagName;
    }

    public void setHashtagName(String hashtagName) {
        this.hashtagName = hashtagName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String key() {
        return hashtagName;
    }

    @Override
    public Long value() {
        return count;
    }
}
