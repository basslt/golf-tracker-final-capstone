package com.techelevator.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVParser {

    public static void main(String[] args) {
        String csvFile = "withLocation.csv";

        String url = "jdbc:postgresql://localhost:5432/golf-tracker-db";
        String username = "postgres";
        String password = "postgres1";

        String tableName = "course";
        String insertTemplate = "INSERT INTO %s (course_id, name, address, city, state, zipcode, latitude, longitude) VALUES (%s);";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String courseId = values[0];
                String name = values[1].replace("'", "''");
                String address = values[2];
                String city = values[3];
                String state = values[4];
                String zipcode = values[5];
                String latitude = values[6];
                String longitude = values[7];

                String insertStatement = String.format(insertTemplate, tableName, getValuesString(courseId, name, address, city, state, zipcode, latitude, longitude));

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertStatement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getValuesString(String... values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append("'").append(value).append("',");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
