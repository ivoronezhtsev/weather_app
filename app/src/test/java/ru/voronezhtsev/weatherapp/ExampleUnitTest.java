package ru.voronezhtsev.weatherapp;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import ru.voronezhtsev.weatherapp.ru.voronezhtsev.weatherapp.net.models.Response;

/**
 * Response local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void parseForecast() throws IOException {
        URL url = Resources.getResource("response.json");
        String responseString = Resources.toString(url, Charsets.UTF_8);
        Gson gson = new Gson();
        Response response = gson.fromJson(responseString, Response.class);

    }
}