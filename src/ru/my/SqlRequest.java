package ru.my;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by rashgild on 11.05.2018.
 */
public class SqlRequest {

    private String req;
    public SqlRequest(){
        this.req = new String();
    }

    public SqlRequest(String path){
        String s = new String();
        StringBuffer sb = new StringBuffer();

        try{
            FileReader fr = new FileReader(new File("SQL/"+path));
            BufferedReader br = new BufferedReader(fr);
            while((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.req = sb.toString();
    }

    public SqlRequest put(String var1, String var2) throws JSONException {
        this.req = this.req.replace(var1,var2);
        return this;
    }

    @Override
    public String toString() {
        return req;
    }

}
