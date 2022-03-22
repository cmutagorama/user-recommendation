# User recommendation API

When you follow someone on Twitter, Facebook or Instagram, the website may recommend some close friends of this user to you. This web service implements a similar functionality based on the Twitter dataset we collected.

### Request format

`GET /v1/api/q2?userId=<ID>&type=<TYPE>&phrase=<PHRASE>&hashtag=<HASHTAG>`

### Response format

```json
[
	{
		"userId": 1,
		"userScreenName": "screen name 1",
		"userDescription": "description 1",
		"rankingScore": 1.0,
		"latestTweet": null
	},
	{
		"userId": 2,
		"userScreenName": "screen name 2",
		"userDescription": "description 2",
		"rankingScore": 1.0,
		"latestTweet": {
			"tweetId": 1234,
			"createdAt": "2000-01-01T10:00:00",
			"text": "Tweet 1",
			"userId": 3,
			"userScreenName": "screen name 3",
			"userDescription": "description 3",
			"tweetType": "reply",
			"replyToTweetId": 123456,
			"replyToUserId": 1,
			"retweetToTweetId": null,
			"retweetToUserId": null,
			"hashtags": "Hello world"
		}
	}
]
```

## Stack used

This API is built on top of the following stacks:
- Spring boot v2.6.4 as a boilerplate
- Apache maven v3.8.4 as a compiler & dependency manager
- Postgresql v9+ as the storage

## Prerequisites

Before running this API on your local environment, make sure you have following dependencies installed:
- Java 8
- Maven 3.8.4
- Postgres 9+

1. Clone this repository

```bash
git clone git@github.com:cmutagorama/user-recommendation.git
```

2. Navigate to the root directory and build the project

```bash
cd user-recommendation-api/

mvn clean install
```

3. Run the app

```bash
mvn spring-boot:run
```

## Deployment

This API is deployed on Heroku. Live on `https://user-recommendation-api.herokuapp.com`.