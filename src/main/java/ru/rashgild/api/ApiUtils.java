package ru.rashgild.api;

import com.google.gson.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ApiUtils {

    static String cretePostRequest(String endpoint, String path, String json) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpoint);
        target = target.path(path);
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json));

        return response.readEntity(String.class);
    }

    public static String get(JsonObject obj, String name) {
        if (obj.has(name)) {
            if (obj != null && obj.get(name) != null && !obj.get(name).getAsString().equals("")) {
                return obj.get(name).getAsString();
            } else {
                return "";
            }
        }
        return "";
    }

    public static Boolean isNotNullOrEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    public static String getDate(JsonObject obj, String name) {
        String value = get(obj, name);
        if (value != null && !value.isEmpty()) {
            return value;//toNewXMLGregorianCalendar(obj.get(name).getAsString());
        }
        return null;
    }

    public static Boolean getBoolean(JsonObject obj, String name) {
        String value = get(obj, name);
        if (value != null && !value.isEmpty()) {
            return Boolean.parseBoolean(obj.get(name).getAsString());
        }
        return null;
    }

    private static XMLGregorianCalendar toNewXMLGregorianCalendar(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(newDate);

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
