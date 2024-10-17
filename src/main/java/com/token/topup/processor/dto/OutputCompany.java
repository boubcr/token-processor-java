package com.token.topup.processor.dto;

import com.token.topup.processor.entities.Company;
import com.token.topup.processor.entities.User;

import java.util.ArrayList;
import java.util.List;

public class OutputCompany {
    private Long companyId;
    private String name;
    private Integer tokens;
    private List<OutputUser> emailed;
    private List<OutputUser> notEmails;

    public OutputCompany(){}

    public OutputCompany(Company company, List<User> users) {
        this.companyId = company.getId();
        this.name = company.getName();
        this.tokens = company.getTopUp() * users.size();

        this.emailed = new ArrayList<>();
        this.notEmails = new ArrayList<>();

        users.forEach(user -> {
            OutputUser outputUser = new OutputUser(user, company.getTopUp());
            if (company.isEmailStatus() && user.isEmailStatus()) {
                this.emailed.add(outputUser);
            } else {
                this.notEmails.add(outputUser);
            }
        });
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public List<OutputUser> getEmailed() {
        return emailed;
    }

    public List<OutputUser> getNotEmails() {
        return notEmails;
    }

    public Integer getTokens() {
        return tokens;
    }
}
