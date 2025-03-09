package com.example.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        da();




    }

    public void da() {
        new Thread(() -> {
            try {

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                        .url("https://eol.org/api/search/1.0.json?q=Shark&exact=true/results")
                        .get()
                        .build();
                Response response = client.newCall(request).execute();
                final String result = response.body().string();


                runOnUiThread(() -> {
                            TextView text = findViewById(R.id.s);
                            try {
                                JSONObject json = new JSONObject(result);
                                JSONArray results = json.getJSONArray("results");

                                // Создаем список акул
                                List<Shark> sharks = new ArrayList<>();
                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject item = results.getJSONObject(i);
                                    String title = item.getString("title");
                                    sharks.add(new Shark(title));
                                }

                                // Выводим название первой акулы
                                if (!sharks.isEmpty()) {
                                    text.setText(sharks.get(0).getTitle());
                                } else {
                                    text.setText("No sharks found");
                                }

                            } catch (JSONException e) {
                                text.setText("Error: " + e.getMessage());
                            }
                        });
////                OkHttpClient client = new OkHttpClient().newBuilder().build();
////                Request request = new Request.Builder()
////                        .url("https://eol.org/api/search/1.0.json?q=Shark&exact=true")
////                        .get()
////                        .build();
////                Response response = client.newCall(request).execute();
////                final String result = response.body().string();
////                runOnUiThread(() -> {
////                    TextView text = findViewById(R.id.s);
////                    try {
////                        JSONObject json = new JSONObject(result);
////                        JSONArray results = json.getJSONArray("results");
////
////                        // Создаем список акул
////                        List<Shark> sharks = new ArrayList<>();
////                        for(int i = 0; i < results.length(); i++) {
////                            JSONObject item = results.getJSONObject(i);
////                            String title = item.getString("title");
////                            sharks.add(new Shark(title));
////                        }
////
////                        // Выводим название первой акулы
////                        if(!sharks.isEmpty()) {
////                            text.setText(sharks.get(0).getTitle());
////                        } else {
////                            text.setText("No sharks found");
////                        }
////
////                    } catch (JSONException e) {
////                        text.setText("Error: " + e.getMessage());
////                    }
//                });
            } catch (Exception e) {

            }
        }).start();
    }

}