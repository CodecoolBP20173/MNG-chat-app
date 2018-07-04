package com.mng.chat.repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SQLUtils {

    private Connection connection;

    public SQLUtils(String path, String username, String password) {
        try {
            connection = DriverManager.getConnection(path, username, password);
        } catch (SQLException e) {
            System.out.println("Could not establish database connection.");
            e.printStackTrace();
        }
    }

    public void executeUpdate(String queryLine, String... params) {
        try (PreparedStatement statement = connection.prepareStatement(queryLine)){
            int counter = 1;
            for (String param : params) {
                statement.setString(counter++, param);
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database Error!");
            e.printStackTrace();
        }
    }

    public <T> T fetchOne(String queryLine, ModelAssembler<T> assembler, String... params) {
        try (PreparedStatement statement = connection.prepareStatement(queryLine)) {

            int counter = 1;
            for (String param : params) {
                statement.setString(counter++, param);
            }

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return assembler.assemble(rs);
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Database Error!");
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> fetchAll(String queryLine, ModelAssembler<T> assembler, String... params) {
        try (PreparedStatement statement = connection.prepareStatement(queryLine)) {

            int counter = 1;
            for (String param : params) {
                statement.setString(counter++, param);
            }

            ResultSet rs = statement.executeQuery();
            List<T> results = new LinkedList<>();
            while (rs.next()) {
                T newObject = assembler.assemble(rs);
                results.add(newObject);
            }
            return results;

        } catch (SQLException e) {
            System.out.println("Database Error!");
            e.printStackTrace();
        }
        return null;
    }
}
