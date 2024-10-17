package com.token.topup.processor.dto;

import com.token.topup.processor.entities.User;

public class OutputUser {
    private String firstName;
    private String lastName;
    private String email;
    private Integer previousToken;
    private Integer newToken;

    public OutputUser() {}

    public OutputUser(User user, Integer companyTokens) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.previousToken = user.getTokens();
        this.newToken = user.getTokens() + companyTokens;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPreviousToken() {
        return previousToken;
    }

    public Integer getNewToken() {
        return newToken;
    }

    public String getEmail() {
        return email;
    }
}
