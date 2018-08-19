package hotel.support;

import java.sql.*;
import java.io.*;
import java.util.*;

public class ConexaoBD {

    private static ConexaoBD instance = null;
    private Connection conn = null;

    public ConexaoBD() {        
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("db.properties"));
            String dbdriver = prop.getProperty("db.driver");
            String dburl = prop.getProperty("db.url");
            String dbuser = prop.getProperty("db.user");
            String dbsenha = "postgres";

            // Carrega Driver do Banco de Dados
            Class.forName(dbdriver);

            if (dbuser.length() != 0) // conexão COM usuário e senha
            {
                conn = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } 
            else // conexão SEM usuário e senha
            {
                conn = DriverManager.getConnection(dburl);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static ConexaoBD getInstance() {
        if (instance == null) {
            instance = new ConexaoBD();
        }
        
        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            throw new RuntimeException("connection == null");
        }
        
        return conn;
    }

    public void shutDown() {
        try {
            conn.close();
            conn = null;
            instance = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

