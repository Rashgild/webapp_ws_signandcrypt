package ru.api;

import org.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static ru.api.ApiUtils.creteGetRequest;

/**
 * Created by rkurbanov on 24.04.2018.
 */

@Path("/medos")
public class MedosApi {

    @GET
    @Path("/createSign")
    @Produces("application/json;charset=UTF-8")
    public void createSign(@QueryParam("disabilityId") String disabilityId) throws JSONException {

        // выбрать данные из базы().
        // создать json()
        //отправить JSON с периодом.
    }


    @GET
    @Path("/getSign")
    @Produces("text/html")
    public String getSign() throws JSONException {

        return creteGetRequest("http://localhost:999","api/jsongen/test","text/html");
    }

    @GET
    @Path("/gohtml")
    @Produces("text/html")
    public String test(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws JSONException {

        String ht = creteGetRequest("http://localhost:999","api/jsongen/html","text/html");

        //select period
        //выбираем нужный период
        //номер элн
        //передаем в подписание
        //

        /*Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:999");
        target = target.path("api/jsongen/html");

        Response response = target.request("text/html")
                .header("Access-Control-Allow-Headers","X-Requested-With, content-type")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .get();

        System.out.println(response);
        return  response.readEntity(String.class);*/

       return ht;
    }

}
