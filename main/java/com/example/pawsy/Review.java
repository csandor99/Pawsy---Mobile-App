package com.example.pawsy;

public class Review {
    private String usernamePetSitter, usernameOwner, review,uniqueID;
    private Float rating;
    private Boolean recommend;

    public Review(){

    }

    public Review(String uniqueID, String usernamePetSitter, String usernameOwner, String review, Float rating, Boolean recommend) {
        this.uniqueID = uniqueID;
        this.usernamePetSitter = usernamePetSitter;
        this.usernameOwner = usernameOwner;
        this.review = review;
        this.rating = rating;
        this.recommend = recommend;
    }

    public String getUsernamePetSitter() {
        return usernamePetSitter;
    }

    public void setUsernamePetSitter(String usernamePetSitter) {
        this.usernamePetSitter = usernamePetSitter;
    }

    public String getUsernameOwner() {
        return usernameOwner;
    }

    public void setUsernameOwner(String usernameOwner) {
        this.usernameOwner = usernameOwner;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    @Override
    public String toString() {
        if(recommend){
            return "Reviewer: " + usernameOwner + '\n'
                    + "Review: " + review + '\n'
                    + "Rating: " + rating + '\n'
                    + "Would recommend: Yes" + '\n'
                    ;
        }
        else{
            return "Reviewer: " + usernameOwner + '\n'
                    + "Review: " + review + '\n'
                    + "Rating: " + rating + '\n'
                    + "Would recommend: No" + '\n'
                    ;
        }

    }
}
