package ru.api;

import com.google.gson.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/** Created by rkurbanov on 24.04.2018. */

public class ApiUtils {

    public static String creteGetRequest(String endpoint,String path, String mediaType){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpoint);
        target = target.path(path);

        Response response = target.request(mediaType)
                .header("Access-Control-Allow-Headers","X-Requested-With, content-type")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .get();

        //System.out.println(response);
        return  response.readEntity(String.class);
    }

    public static String creteGetRequest(String endpoint, String path, Map<String, String> params){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpoint);
        target = target.path(path);
        for (Map.Entry entry : params.entrySet()) {
            target = target.queryParam(entry.getKey().toString(),entry.getValue().toString());
        }
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        //System.out.println(response);
        return  response.readEntity(String.class);
    }


    public static String cretePostRequest(String endpoint, String path, String json){

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(endpoint);
        target = target.path(path);
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(json));
        //System.out.println(response);
        return  response.readEntity(String.class);
    }

    public static String get(JsonObject obj, String name){
        if(obj.has(name)){
            if(obj!=null && obj.get(name)!=null && !obj.get(name).getAsString().equals("")){
                return obj.get(name).getAsString();
            }
        }
        return null;
    }
}
