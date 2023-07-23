package com.epam.mjc.io;

import java.io.File;


public class FileReader {

    public Profile getDataFromFile(File file) {
        //return new Profile();
        try {
            String fileData = readFileToString(file);
            Map<String, String> profileData = parseFileData(fileData);
            return createProfileFromData(profileData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readFileToString(File file) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }
        return fileContent.toString();
    }

    private Map<String, String> parseFileData(String fileData) {
        Map<String, String> profileData = new HashMap<>();
        String[] lines = fileData.split("\n");
        for (String line : lines) {
            int separatorIndex = line.indexOf(":");
            if (separatorIndex != -1) {
                String key = line.substring(0, separatorIndex).trim();
                String value = line.substring(separatorIndex + 1).trim();
                profileData.put(key, value);
            }
        }
        return profileData;
    }

    private Profile createProfileFromData(Map<String, String> profileData) {
        String name = profileData.get("Name");
        Integer age = Integer.parseInt(profileData.get("Age"));
        String email = profileData.get("Email");
        Long phone = Long.parseLong(profileData.get("Phone"));
        return new Profile(name, age, email, phone);
    }
}
