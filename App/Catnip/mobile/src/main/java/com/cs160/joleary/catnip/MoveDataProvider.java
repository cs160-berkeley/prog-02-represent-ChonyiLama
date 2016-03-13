package com.cs160.joleary.catnip;

/**
 * Created by chonyi on 3/2/16.
 */
public class MoveDataProvider {
    private int move_poster_resource;
    private String move_title;
    private  String move_rating;
    private String tweets;
    private String websites;
    private String parties;

    public String getMove_rating() {
        return move_rating;

    }

    public MoveDataProvider(int move_poster_resource,String move_title,String move_rating, String websites, String parties){

//took out String tweets argument
        this.setMove_poster_resource(move_poster_resource);
        this.setMove_title(move_title);
        this.setMove_rating(move_rating);
        this.setWebsites(websites);
        this.setTweets(tweets);
        this.setParties(parties);
        System.out.println("i am in move data provider");
    }
    public String getTweets() { return tweets;}
    public void setTweets(String tweets) { this.tweets = tweets;}

    public String getParties() { return parties;}
    public void setParties(String parties) { this.parties = parties;}

    public String getWebsites() { return websites;}
    public void setWebsites(String websites) { this.websites = websites;}

    public void setMove_rating(String move_rating) {
        this.move_rating = move_rating;
    }

    public String getMove_title() {
        return move_title;
    }

    public void setMove_title(String move_title) {
        this.move_title = move_title;
    }

    public int getMove_poster_resource() {
        return move_poster_resource;
    }

    public void setMove_poster_resource(int move_poster_resource) {
        this.move_poster_resource = move_poster_resource;
    }


}
