package ru.fintech.qa.homework.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class ServiceTests {

    @BeforeAll
    public static void beforeAll() {
        BeforeUtils.createData();
    }

    @Test
    public void Test() throws SQLException {
        Connection connection = new DBClient().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM public.animal");
            resultSet.next();
            System.out.println(resultSet.getInt("count(*)"));
            Assertions.assertEquals(10, resultSet.getInt("count(*)"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void Test2() throws SQLException {
        Connection connection = new DBClient().getConnection();
        for (int count = 1; count < 11; count++) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO animal VALUES (" + count + ", 'Картошка', 3, 3, 2, 3)");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        connection.close();
    }

    @Test
    public void Test3() throws SQLException {
        Connection connection = new DBClient().getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO public.workman VALUES (7, NULL, 25, 2)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void Test4() throws SQLException {
        Connection connection = new DBClient().getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO public.places VALUES (6, 4, 77, 'Загон 6')");
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM public.places");
            resultSet.next();
            System.out.println(resultSet.getInt("count(*)"));
            Assertions.assertEquals(6, resultSet.getInt("count(*)"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void Test5() throws SQLException {
        Connection connection = new DBClient().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM public.zoo WHERE \"name\" in ('Центральный', 'Северный', 'Западный')");
            resultSet.next();
            System.out.println(resultSet.getInt("count(*)"));
            Assertions.assertEquals(3, resultSet.getInt("count(*)"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }
}
