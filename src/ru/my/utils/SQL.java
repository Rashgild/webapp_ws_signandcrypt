package ru.my.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static ru.my.utils.GlobalVariables.dbdriver;
import static ru.my.utils.GlobalVariables.dbhost;
import static ru.my.utils.GlobalVariables.dblogin;
import static ru.my.utils.GlobalVariables.dbpassword;

public class SQL {

    public static ResultSet select(String reqSQL) {
        Connection connection;
        ResultSet resultSet = null;
        try {
            //Class.forName("org.postgresql.Driver");
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost, dblogin, dbpassword);
            //System.out.println("Соединение установлено");
            Statement statement;

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery(reqSQL);
            if (!dbdriver.equals("com.intersys.jdbc.CacheDriver")) {
                connection.close();
            }
            return resultSet;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static int sqlUpdIns(String sql) {
        Connection connection;
        int res = 0;
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost, dblogin, dbpassword);
            Statement statement;
            statement = connection.createStatement();
            res = statement.executeUpdate(sql);
            connection.close();
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static String insertReturning(String sql) {
        Connection connection;
        ResultSet rs;
        String id = "";
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost, dblogin, dbpassword);
            Statement statement = null;
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            connection.close();

            while (rs.next()) {
                id = rs.getString("id");
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
