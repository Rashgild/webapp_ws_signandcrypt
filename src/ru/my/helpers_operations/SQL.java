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
            if(!dbdriver.equals("com.intersys.jdbc.CacheDriver")){
            connection.close();
            }
            return resultSet;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public static int SQL_UpdIns (String sql)
    {
        Connection connection = null;
        int res=0;
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(dbhost,dblogin,dbpassword);
            Statement statement = null;
            statement = connection.createStatement();
            res=  statement.executeUpdate(sql);
            connection.close();
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static void SaveInBD(String result, Integer status)
    {
        result = Split(result);
        if(GlobalVariables.Response!=null&& !GlobalVariables.Response.equals("")) {
            GlobalVariables.Response = Split(GlobalVariables.Response);
        }
        SQL_UpdIns(StoredQuery.QueryToSave(result,status));
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
