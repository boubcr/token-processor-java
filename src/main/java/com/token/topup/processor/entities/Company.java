package com.token.topup.processor.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
    private Long id;
    private String name;
    @JsonProperty("top_up")
    private Integer topUp;
    @JsonProperty("email_status")
    private boolean emailStatus;

    public Company() {
    }

    public Company(Long id, String name, Integer topUp, boolean emailStatus) {
        this.id = id;
        this.name = name;
        this.topUp = topUp;
        this.emailStatus = emailStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTopUp() {
        return topUp;
    }

    public void setTopUp(Integer topUp) {
        this.topUp = topUp;
    }

    public boolean isEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(boolean emailStatus) {
        this.emailStatus = emailStatus;
    }
}
