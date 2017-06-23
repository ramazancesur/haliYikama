package hali.pro.com.haliyikama.Helper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import hali.pro.com.haliyikama.authenticationentities.JwtAuthenticationRequest;
import hali.pro.com.haliyikama.authenticationentities.JwtAuthenticationResponse;
import hali.pro.com.haliyikama.authenticationentities.JwtUser;

/**
 * Created by ramazancesur on 23/06/2017.
 */

public class RAuthentication {
    private static JwtAuthenticationResponse jwtAuthenticationResponse;

    @SuppressWarnings("static-access")
    public static JwtAuthenticationResponse getAuthTokenCookie(JwtUser user) throws JsonGenerationException, JsonMappingException, IOException {
        if (jwtAuthenticationResponse == null) {
            String uri = Settings.getServerUrl() + "/auth";
            try {


                Client client = Client.create();

                WebResource webResource = client.resource(uri);

                ObjectMapper mapper = new ObjectMapper();
                JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest(user.getUsername(), user.getPassword());


                ClientResponse response = webResource.type("application/json")
                        .post(ClientResponse.class, mapper.writeValueAsString(jwtAuthenticationRequest));

                if (response.getStatus() == 201) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                String jsonResponse = response.getEntity(String.class);

                Gson gson = new Gson();
                jwtAuthenticationResponse = gson.fromJson(jsonResponse, JwtAuthenticationResponse.class);

                System.out.println(jsonResponse);
                System.out.println("Output from Server .... \n");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return jwtAuthenticationResponse;

        } else {
            return jwtAuthenticationResponse;
        }
    }

}