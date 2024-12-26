package com.example.finalwork.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalwork.R;
import com.example.finalwork.weatherinfo.WeatherHourlyCard;
import com.example.finalwork.weatherinfo.WeatherWeeklyCard;

import java.util.List;

public class WeatherWeeklyCardAdapter extends RecyclerView.Adapter<WeatherWeeklyCardAdapter.WeatherViewHolder> {

    private List<WeatherWeeklyCard> weatherWeeklyCardList;

    public WeatherWeeklyCardAdapter(List<WeatherWeeklyCard> weatherWeeklyCardList) {
        this.weatherWeeklyCardList = weatherWeeklyCardList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherWeeklyCard weatherWeeklyCard = weatherWeeklyCardList.get(position);

        holder.weeklyTimeTextView.setText(weatherWeeklyCard.getWeeklyTime());
        holder.weatherDescriptionTextView.setText(weatherWeeklyCard.getWeeklyWeaDes());
        holder.weatherIconImageView.setImageResource(weatherWeeklyCard.getWeeklyIconResId());
        holder.temperatureTextViewH.setText(weatherWeeklyCard.getWeeklyTemH());
        holder.temperatureTextViewL.setText(weatherWeeklyCard.getWeeklyTemL());
    }

    @Override
    public int getItemCount() {
        return weatherWeeklyCardList.size();
    }

    // ViewHolder 用于缓存每个卡片视图的视图组件
    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIconImageView;
        TextView weeklyTimeTextView;
        TextView weatherDescriptionTextView;
        TextView temperatureTextViewH;
        TextView temperatureTextViewL;
        public WeatherViewHolder(View itemView) {
            super(itemView);
            weeklyTimeTextView = itemView.findViewById(R.id.WeeklyTime);
            weatherIconImageView = itemView.findViewById(R.id.WeeklyWeaIcon);
            weatherDescriptionTextView = itemView.findViewById(R.id.WeeklyWea);
            temperatureTextViewH = itemView.findViewById(R.id.WeeklyTemH);
            temperatureTextViewL = itemView.findViewById(R.id.WeeklyTemL);
        }
    }
}
