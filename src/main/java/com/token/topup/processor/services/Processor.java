package com.token.topup.processor.services;

import com.token.topup.processor.dto.OutputCompany;
import com.token.topup.processor.dto.OutputUser;
import com.token.topup.processor.entities.Company;
import com.token.topup.processor.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

public class Processor {
    public Processor() {

    }

    public void execute()  {
        List<Company> companies = FileManager.getCompaniesFromJson();
        companies.sort(Comparator.comparing(Company::getId));

        List<User> users = FileManager.getUsersFromJson();
        users.sort(Comparator.comparing(User::getLastName));

        List<OutputCompany> output = getUsersByCompany(companies, users);
        String outputString = buildTxtOutputString(output);
        FileManager.writeOnTxtFile(outputString);
    }

    private List<OutputCompany> getUsersByCompany(List<Company> companies, List<User> users) {
        Map<Long, List<User>> activeUsersPerCompany = users.stream()
                .filter(User::isActiveStatus)
                .collect(groupingBy(User::getCompanyId));

        return activeUsersPerCompany.entrySet().stream()
                .map(pair -> companies.stream()
                        .filter(p -> pair.getKey().equals(p.getId()))
                        .findAny()
                        .map(company -> new OutputCompany(company, pair.getValue()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }

    private String buildTxtOutputString(List<OutputCompany> users) {
        StringBuilder sb = new StringBuilder();
        for (OutputCompany company : users) {
            sb.append(buildLine("Company Id", company.getCompanyId(), 1));
            sb.append(buildLine("Company Name", company.getName(), 1));
            sb.append(buildLine( "Users Emailed", null, 1));
            if (Objects.nonNull(company.getEmailed()) && !company.getEmailed().isEmpty()) {
                sb.append(buildUserLines(company.getEmailed()));
            }
            sb.append(buildLine( "Users Not Emailed", null, 1));
            if (Objects.nonNull(company.getNotEmails()) && !company.getNotEmails().isEmpty()) {
                sb.append(buildUserLines(company.getNotEmails()));
            }

            String totalAmountLine = "Total amount of top ups for ".concat(company.getName());
            sb.append(buildLine(totalAmountLine, company.getTokens(), 1));
            sb.append("\n");
        }
        return sb.toString();
    }

    private String buildUserLines(List<OutputUser> users) {
        return String.join("", users.stream()
                .map(this::buildUserLine)
                .toList()
        );
    }

    private String buildUserLine(OutputUser user) {
        StringBuilder sb = new StringBuilder();
        String userName = user.getLastName().concat(", ")
                .concat(user.getFirstName()).concat(", ")
                .concat(user.getEmail());
        sb.append(buildLine( userName, null, 2));

        String previousToken = "Previous Token Balance, ".concat(user.getPreviousToken().toString());
        sb.append(buildLine(previousToken, null, 3));

        String newToken = "New Token Balance, ".concat(user.getNewToken().toString());
        sb.append(buildLine(newToken, null, 3));
        return sb.toString();
    }

    private <T> String buildLine(String label, T line, int level) {
        StringBuilder lineBuilder = new StringBuilder();
        while (level-- > 0) {
            lineBuilder.append("\t");
        }

        lineBuilder.append(label);

        if (Objects.nonNull(line)) {
            lineBuilder.append(": ");
            lineBuilder.append(line);
        }

        lineBuilder.append("\n");
        return lineBuilder.toString();
    }
}