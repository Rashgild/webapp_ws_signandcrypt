package ru.api;

import com.google.gson.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
            }
        }
        return null;
    }
}
