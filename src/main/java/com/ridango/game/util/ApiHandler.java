package com.ridango.game.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridango.game.model.api.Cocktail;
import com.ridango.game.model.api.Drinks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class ApiHandler {

    public static Cocktail randomCocktail()
    {
        try {


            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/random.php");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String urlResponse = reader.readLine();
            con.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            Drinks drinks = mapper.readValue(urlResponse, Drinks.class);

            return drinks.getDrinks().get(0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
