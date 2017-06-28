package ru.my.helpers_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static ru.my.helpers_operations.GlobalVariables.*;
import static ru.my.helpers_operations.StoredQuery.SaveNumber;


//Created by rkurbanov on 19.05.2017.

public class SQL {

    public static ResultSet Query (String reqSQL) {

        Connection connection;
        ResultSet resultSet = null;
        try {
            //Class.forName("org.postgresql.Driver");
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost,dblogin,dbpassword);
            //System.out.println("Соединение установлено");
            Statement statement;

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery(reqSQL);
            connection.close();
            return resultSet;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static void SaveInBD(String result, Integer status)
    {
        result = Split(result);
        GlobalVariables.Response = Split(GlobalVariables.Response);

        Query(StoredQuery.QueryToSave(result,status));
    }



    private static String Split(String str)
    {
        String[] arrstr;
        arrstr = str.split("'");
        str="";
        for(String ar: arrstr)str+=ar;

        return str;
    }

}
