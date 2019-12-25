package ru.voronezhtsev.weatherapp.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ru.voronezhtsev.weatherapp.R;
import ru.voronezhtsev.weatherapp.models.data.network.Forecast;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private static final long MILLISECOND = 1000L;
    private static final SimpleDateFormat DISPLAY_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());


    private final List<Forecast> mForecast;

    public ForecastAdapter(List<Forecast> forecast) {
        mForecast = forecast;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.forecast_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(mForecast.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecast.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mDatetime;
        private final TextView mTemp;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDatetime = itemView.findViewById(R.id.datetime_text_view);
            mTemp = itemView.findViewById(R.id.forecast_temp_text_view);
        }

        void bind(Forecast forecast) {
            mTemp.setText(TempUtils.trimZeroes(TempUtils.kelvinToCelcius(forecast.getMain().getTemp())));
            mDatetime.setText(DISPLAY_FORMAT.format(forecast.getDt() * MILLISECOND));
        }
    }
}
