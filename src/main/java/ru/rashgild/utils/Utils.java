package ru.rashgild.utils;

import static ru.rashgild.api.ApiUtils.get;

public class Utils {

    public static String parseSnils(String snils){
        String str[];
        str = snils.split("-");
        snils = str[0] + str[1] + str[2];
        str = snils.split(" ");
        snils = str[0] + str[1];
        return snils;
    }
}
