package com.example.finalwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalwork.Adapters.WeatherHourlyCardAdapter;
import com.example.finalwork.Adapters.WeatherWeeklyCardAdapter;
import com.example.finalwork.weatherinfo.DailyWeatherInfo;
import com.example.finalwork.weatherinfo.WeatherHourlyCard;
import com.example.finalwork.weatherinfo.WeatherWeeklyCard;
import com.example.finalwork.weatherinfo.WeeklyWea;
import com.example.finalwork.weatherinfo.WeeklyWeather;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Next7DayWeatherActivity extends AppCompatActivity {
    public static final String URL = "http://gfeljm.tianqiapi.com/api?unescape=1&version=v91&appid=61799982&appsecret=6RkLdy7H";
    private static final String TAG = Next7DayWeatherActivity.class.getSimpleName() ;
    private RecyclerView recyclerView;
    private WeatherWeeklyCardAdapter adapter;
    private List<WeatherWeeklyCard> weatherWeeklyCardList;
    private ConstraintLayout main7d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next7_day_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainNext7), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        main7d = findViewById(R.id.mainNext7);
        initRecyclerView();
        initRecyclerViewEx();
    }

    private void initRecyclerViewEx() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonContent = response.body().string();
                Log.i(TAG, "onResponse: " + jsonContent);

                Gson gson = new Gson();
                WeeklyWeather weeklyWeather = gson.fromJson(jsonContent, WeeklyWeather.class);
                Log.i(TAG, "onResponse: " + weeklyWeather.getCity());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        weatherWeeklyCardList = new ArrayList<>();
                        recyclerView = main7d.findViewById(R.id.WeeklyRecyclerView);
                        for(int i = 0; i < 7; i++){
                            WeeklyWea weeklyWea = weeklyWeather.getData().get(i);
                            String weaimg = weeklyWea.getWea_img();
                            int sid;
                            if(weaimg.equals("yun")){
                                sid = R.drawable.cloudy_3;
                            }else if(weaimg.equals("yu")){
                                sid = R.drawable.rainy;
                            }else if(weaimg.equals("qing")){
                                sid = R.drawable.sun;
                            }else if(weaimg.equals("lei")){
                                sid = R.drawable.storm;
                            }else{
                                sid = R.drawable.cloudy_sunny;
                            }
                            if(i == 0){
                                ((TextView) main7d.findViewById(R.id.weatherCondition)).setText(weeklyWea.getWea());
                                ((TextView) main7d.findViewById(R.id.temperature)).setText(weeklyWea.getTem());
                                ((TextView) main7d.findViewById(R.id.temperatureHL)).setText(weeklyWea.getTem1() + "℃ " + weeklyWea.getTem2() + "℃");
                                ((ImageView) main7d.findViewById(R.id.weatherImg)).setImageResource(sid);
                            }else{
                                weatherWeeklyCardList.add(new WeatherWeeklyCard(weeklyWea.getWeek(), sid, weeklyWea.getTem1(), weeklyWea.getTem2(), weeklyWea.getWea()));
                            }
                        }
                        adapter = new WeatherWeeklyCardAdapter(weatherWeeklyCardList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    private void initRecyclerView() {
        recyclerView = findViewById(R.id.WeeklyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        weatherWeeklyCardList = new ArrayList<>();
        weatherWeeklyCardList.add(new WeatherWeeklyCard("Sat", R.drawable.rainy, "17", "10", "cloudy"));
        weatherWeeklyCardList.add(new WeatherWeeklyCard("Sun", R.drawable.cloudy, "17", "10", "sun"));
        weatherWeeklyCardList.add(new WeatherWeeklyCard("Mon", R.drawable.cloudy_sunny, "17", "10", "cloudy"));
        weatherWeeklyCardList.add(new WeatherWeeklyCard("Tut", R.drawable.cloudy_3, "17", "10", "rain"));
        weatherWeeklyCardList.add(new WeatherWeeklyCard("Wen", R.drawable.snowy, "17", "10", "rain_cloudy"));
        weatherWeeklyCardList.add(new WeatherWeeklyCard("Tus", R.drawable.storm, "17", "10", "storm"));

        adapter = new WeatherWeeklyCardAdapter(weatherWeeklyCardList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void BackToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}