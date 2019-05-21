package br.edu.ifpb.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConFactory {

    public Connection gerConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/atividadedac";
        String usuario = "postgres";
        String senha = "postgres";

        return DriverManager.getConnection(url, usuario, senha);
    }

}
