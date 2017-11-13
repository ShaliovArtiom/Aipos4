package com.shaliov.server.mysql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс, предназначенный для соединения с таблицей MYSQL
 * @author ShaliovArtiom, TruntsVitalij
 */
public class DBWorker {
    /**
     * поле, предназначенное для создания единственного экземпляра класса
     */
    private static DBWorker instance = null;
    /**
     * поле, содержащее пароль к базе данных
     */
    private final static String PASSWORD = "1234asdqwe";
    /**
     * поле, содержащее имя базы данных
     */
    private final static String USERNAME = "root";
    /**
     * поле, содержащее путь к базе данных
     */
    private final static String URL = "jdbc:mysql://localhost:3306/aipos";
    /**
     * поле, предназначенное для соединения с базой данных
     */
    private Connection connection;

    /**
     * конструктор по умолчанию
     */
    private DBWorker() {
    }

    /**
     * Метод установления соединения с базой данных
     */
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод закрытия соединения
     */
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверки единственности базы данных
     * @return возвращение значения instance
     */
    public static DBWorker getInstance() {
        if(instance == null){
            instance = new DBWorker();
        }
        return instance;
    }

    /**
     * Метод получения соединения с базой данных
     * @return возвращает значение connection
     */
    public Connection getConnection() {
        return connection;
    }
}

