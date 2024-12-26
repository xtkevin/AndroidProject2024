package com.example.finalwork.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalwork.R;
import com.example.finalwork.weatherinfo.WeatherHourlyCard;

import java.util.List;

public class WeatherHourlyCardAdapter extends RecyclerView.Adapter<WeatherHourlyCardAdapter.WeatherViewHolder> {

    private List<WeatherHourlyCard> weatherHourlyCardList;

    public WeatherHourlyCardAdapter(List<WeatherHourlyCard> weatherHourlyCardList) {
        this.weatherHourlyCardList = weatherHourlyCardList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_card, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherHourlyCard weatherHourlyCard = weatherHourlyCardList.get(position);

        // 设置天气描述
        holder.weatherDescriptionTextView.setText(weatherHourlyCard.getHourlytime());

        // 设置天气图标
        holder.weatherIconImageView.setImageResource(weatherHourlyCard.getWeatherIconResId());

        // 设置气温
        holder.temperatureTextView.setText(weatherHourlyCard.getTemperature());
    }

    @Override
    public int getItemCount() {
        return weatherHourlyCardList.size();
    }

    // ViewHolder 用于缓存每个卡片视图的视图组件
    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIconImageView;
        TextView weatherDescriptionTextView;
        TextView temperatureTextView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            weatherIconImageView = itemView.findViewById(R.id.weather_icon);
            weatherDescriptionTextView = itemView.findViewById(R.id.hourlytime);
            temperatureTextView = itemView.findViewById(R.id.temperature);
        }
    }
}
