/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sheba.urlchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

/**
 *
 * @author kabir
 */
public class ServiceRequest {

    public boolean isValidService(Integer serviceId) {

        try {
            String url = "https://api.sheba.xyz/v3/services/" + serviceId + "?lat=23.7980791&lng=90.4048319";
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.connect();
            InputStream ip = con.getInputStream();

            BufferedReader br1 = new BufferedReader(new InputStreamReader(ip));
            StringBuilder response = new StringBuilder();
            String responseSingle = null;
            while ((responseSingle = br1.readLine()) != null) {
                response.append(responseSingle);
            }
            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createJsonParser(response.toString());
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String tok = parser.getCurrentName();

                if (tok != null && tok.equals("code")) {
                    parser.nextToken();
                    Integer code = parser.getIntValue();
                    if (code == 200) {
                        return true;
                    }
                    break;
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean isValidServiceCategory(Integer categoryId) {

        try {
            String url = "https://api.sheba.xyz/v3/categories/" + categoryId + "services?lat=23.7980791&lng=90.4048319";
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.connect();
            InputStream ip = con.getInputStream();

            BufferedReader br1 = new BufferedReader(new InputStreamReader(ip));
            StringBuilder response = new StringBuilder();
            String responseSingle = null;
            while ((responseSingle = br1.readLine()) != null) {
                response.append(responseSingle);
            }
            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createJsonParser(response.toString());
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String tok = parser.getCurrentName();

                if (tok != null && tok.equals("code")) {
                    parser.nextToken();
                    Integer code = parser.getIntValue();
                    if (code == 200) {
                        return true;
                    }
                    break;
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.isValidService(1885);
    }
}
