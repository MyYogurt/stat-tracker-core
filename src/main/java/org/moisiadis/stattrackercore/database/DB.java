package org.moisiadis.stattrackercore.database;

import java.io.*;
import java.sql.*;
import java.util.Optional;

public class DB {
    private final String dbUrl;
    protected final Connection connection;
    private String schemaPath = "src/main/resources/schema.sql";

    public DB(String path) throws SQLException {
        dbUrl = "jdbc:sqlite:" + path;
        connection = DriverManager.getConnection(dbUrl);
    }

    public String getSchemaPath() {
        return schemaPath;
    }

    public void setSchemaPath(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    public boolean delete() {
        File file = new File(dbUrl);
        return file.delete();
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void initialize() throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(schemaPath));

        while (reader.ready()) {
            builder.append(reader.readLine());
        }

        String[] queries = builder.toString().split(";");

        for (String query : queries) {
            try {
                runQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        reader.close();
    }

    public Optional<ResultSet> runQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        return Optional.ofNullable(statement.getResultSet());
    }
}
