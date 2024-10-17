package com.token.topup.processor.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.token.topup.processor.entities.Company;
import com.token.topup.processor.entities.User;
import com.token.topup.processor.execptions.ReadFileException;
import com.token.topup.processor.execptions.WriteFileException;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class FileManager {
    private static final String COMPANIES_JSON_FILE = "files/companies.json";
    private static final String USERS_JSON_FILE = "files/users.json";
    private static final String OUTPUT_TXT_FILE = "files/output.txt";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private FileManager(){
    }

    public static void writeOnTxtFile(String data) throws WriteFileException {
        try {
            FileWriter fileWriter = new FileWriter(getFile(OUTPUT_TXT_FILE));
            try (BufferedWriter writer = new BufferedWriter(fileWriter)) {
                writer.write(data);
            }
        } catch (IOException ex) {
            throw new WriteFileException("Error writing on txt file: " + ex.getMessage());
        }
    }

    public static List<Company> getCompaniesFromJson() throws ReadFileException {
        try {
            List<Company> companies = objectMapper.readValue(getFile(COMPANIES_JSON_FILE), new TypeReference<List<Company>>() {});
            companies.sort(Comparator.comparing(Company::getId));
            return companies;
        } catch (IOException ex) {
            throw new ReadFileException("Error getting company json file: " + ex.getMessage());
        }
    }

    public static List<User> getUsersFromJson() throws ReadFileException {
        try {
            List<User> users = objectMapper.readValue(getFile(USERS_JSON_FILE), new TypeReference<List<User>>() {});
            users.sort(Comparator.comparing(User::getLastName));
            return users;
        } catch (IOException ex) {
            throw new ReadFileException("Error getting users json file: " + ex.getMessage());
        }
    }

    private static File getFile(String path) throws ReadFileException {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            return resource.getFile();
        } catch(IOException ex) {
            throw new ReadFileException("File ".concat(path).concat(" is not found"));
        }
    }
}
