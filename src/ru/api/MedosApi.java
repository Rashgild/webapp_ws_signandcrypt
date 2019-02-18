package ru.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;

import static ru.api.ApiUtils.creteGetRequest;

@Path("/medos")
public class MedosApi {

    @GET
    @Path("/getSign")
    @Produces("text/html")
    public String getSign() throws JSONException {
        return creteGetRequest("http://localhost:999", "api/jsongen/test", "text/html");
    }

    @GET
    @Path("/gohtml")
    @Produces("text/html")
    public String test(@Context HttpServletRequest req, @Context HttpServletResponse resp) throws JSONException {
        String ht = creteGetRequest("http://localhost:999", "api/jsongen/html", "text/html");
        return ht;
    }
}
