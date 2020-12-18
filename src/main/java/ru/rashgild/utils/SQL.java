package main.java.ru.rashgild.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static main.java.ru.rashgild.utils.GlobalVariables.dbdriver;
import static main.java.ru.rashgild.utils.GlobalVariables.dbhost;
import static main.java.ru.rashgild.utils.GlobalVariables.dblogin;
import static main.java.ru.rashgild.utils.GlobalVariables.dbpassword;

public class SQL {

    public static ResultSet select(String reqSQL) {
        Connection connection;
        ResultSet resultSet;
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost, dblogin, dbpassword);
            Statement statement;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery(reqSQL);
            return resultSet;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int sqlUpdIns(String sql) {
        Connection connection;
        int result = 0;
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost, dblogin, dbpassword);
            Statement statement;
            statement = connection.createStatement();
            result = statement.executeUpdate(sql);
            connection.close();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String insertReturning(String sql) {
        Connection connection;
        ResultSet resultSet;
        String id = "";
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost, dblogin, dbpassword);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            connection.close();

            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public static void saveInBaseDate(String result, Integer status) {
        result = split(result);
        if (GlobalVariables.Response != null && !GlobalVariables.Response.equals("")) {
            GlobalVariables.Response = split(GlobalVariables.Response);
        }
        sqlUpdIns(StoredQuery.QueryToSave(result, status));
    }

    private static String split(String str) {
        String[] arrstr;
        arrstr = str.split("'");
        str = "";
        for (String ar : arrstr) str += ar;
        return str;
    }
}
