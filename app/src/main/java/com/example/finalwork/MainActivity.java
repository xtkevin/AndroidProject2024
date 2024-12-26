package com.example.finalwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalwork.weatherinfo.DailyWeatherInfo;
import com.example.finalwork.weatherinfo.WeatherHourlyCard;
import com.example.finalwork.Adapters.WeatherHourlyCardAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private WeatherHourlyCardAdapter adapter;
    private List<WeatherHourlyCard> weatherHourlyCardList;
    private TextView cityNameTextView;
    private TextView timeNowTextView;
    private ImageView weatherImg;
    private TextView weatherConditionTextView;
    private TextView temperatureTextView;
    private TextView temperatureHL;
    private LinearLayout activity_main;
    public static final String URL = "http://gfeljm.tianqiapi.com/api?unescape=1&version=v63&appid=61799982&appsecret=6RkLdy7H";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_main);
        activity_main = findViewById(R.id.main);
        initRecyclerView();
        getWeatherTodayInfo();
    }


    private void getWeatherTodayInfo(){
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
                DailyWeatherInfo dailyWeatherInfo = gson.fromJson(jsonContent, DailyWeatherInfo.class);
                Log.i(TAG, "onResponse: " + dailyWeatherInfo.getCity() + " " + dailyWeatherInfo.getWea());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((TextView) activity_main.findViewById(R.id.cityName)).setText(dailyWeatherInfo.getCity());
                        ((TextView) activity_main.findViewById(R.id.weatherCondition)).setText(dailyWeatherInfo.getWea());
                        ((TextView) activity_main.findViewById(R.id.temperature)).setText(dailyWeatherInfo.getTem());
                        ((TextView) activity_main.findViewById(R.id.temperatureHL)).setText(dailyWeatherInfo.getTem1() + "℃ " + dailyWeatherInfo.getTem2() + "℃");
                        ((TextView) activity_main.findViewById(R.id.RainPos)).setText(dailyWeatherInfo.getRain_pcpn());
                        ((TextView) activity_main.findViewById(R.id.windSpeed)).setText(dailyWeatherInfo.getWin_meter());
                        ((TextView) activity_main.findViewById(R.id.Humidity)).setText(dailyWeatherInfo.getHumidity());
                        ((ImageView) activity_main.findViewById(R.id.weatherImg)).setImageResource(R.drawable.cloudy_sunny);
                        weatherHourlyCardList = new ArrayList<>();
                        for(int i = 0; i < 6; i++){
                            String timenow = dailyWeatherInfo.getHours().get(i).getHours();
                            String Wea = dailyWeatherInfo.getHours().get(i).getWea();
                            String tem = dailyWeatherInfo.getHours().get(i).getTem() + "℃";
                            String weaimg = dailyWeatherInfo.getHours().get(i).getWea_img();
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
                            weatherHourlyCardList.add(new WeatherHourlyCard(timenow, sid, tem));
                        }
                        recyclerView = activity_main.findViewById(R.id.recyclerViewHourlyWeather);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        Log.d(TAG, "Item count: " + weatherHourlyCardList.size());

                        // 创建适配器并设置给 RecyclerView
                        adapter = new WeatherHourlyCardAdapter(weatherHourlyCardList);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });


    }

    private void initRecyclerView() {


        // 模拟一些天气数据
        weatherHourlyCardList = new ArrayList<>();
        weatherHourlyCardList.add(new WeatherHourlyCard("7am", R.drawable.sun, "28°C"));
        weatherHourlyCardList.add(new WeatherHourlyCard("8am", R.drawable.cloudy, "22°C"));
        weatherHourlyCardList.add(new WeatherHourlyCard("9am", R.drawable.rainy, "18°C"));
        weatherHourlyCardList.add(new WeatherHourlyCard("10am", R.drawable.sun, "28°C"));
        weatherHourlyCardList.add(new WeatherHourlyCard("11am", R.drawable.cloudy, "22°C"));
        weatherHourlyCardList.add(new WeatherHourlyCard("12am", R.drawable.rainy, "18°C"));
        weatherHourlyCardList.add(new WeatherHourlyCard("1pm", R.drawable.sun, "28°C"));

        recyclerView = activity_main.findViewById(R.id.recyclerViewHourlyWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Log.d("RecyclerView", "Item count: " + weatherHourlyCardList.size());

        // 创建适配器并设置给 RecyclerView
        adapter = new WeatherHourlyCardAdapter(weatherHourlyCardList);
        recyclerView.setAdapter(adapter);

    }


    public void QueryWeatherNext7Days(View view) {
        //Toast.makeText(this, "This is a Toast message", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Next7DayWeatherActivity.class);

        startActivity(intent);
    }

}