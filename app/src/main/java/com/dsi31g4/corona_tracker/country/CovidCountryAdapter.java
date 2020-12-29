package com.dsi31g4.corona_tracker.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dsi31g4.corona_tracker.R;

import java.util.ArrayList;
import java.util.List;

public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder> implements Filterable {

    private List<CovidCountry> covidCountries;
    private List<CovidCountry> covidCountriesFull;

    private Context context;

    public CovidCountryAdapter(List<CovidCountry> covidCountries, Context context) {
        this.covidCountries = covidCountries;
        this.context = context;
        covidCountriesFull = new ArrayList<>(covidCountries);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_country, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CovidCountry covidCountry = covidCountries.get(position);

        holder.tvDetailCountryName.setText(covidCountry.getmCovidCountry());
        holder.tvDetailTotalCases.setText(covidCountry.getmCritical());
        holder.tvDetailTotalDeaths.setText(covidCountry.getmDeaths());
        holder.tvDetailTotalRecovered.setText(covidCountry.getmRecovered());
        holder.tvDetailTotalActive.setText(covidCountry.getmActive());
        holder.tvDetailTotalCritical.setText(covidCountry.getmCritical());



    }

    @Override
    public int getItemCount() {
        return covidCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetailCountryName, tvDetailTotalCases,tvDetailTotalDeaths,
                tvDetailTotalRecovered, tvDetailTotalActive, tvDetailTotalCritical;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvTotalCases = itemView.findViewById(R.id.tvTotalCases);
//            tvCountryName = itemView.findViewById(R.id.tvCountryName);
//            imgCountryFlag = itemView.findViewById(R.id.imgCountryFlag);
            tvDetailCountryName = itemView.findViewById(R.id.tvDetailCountryName);
            tvDetailTotalCases = itemView.findViewById(R.id.tvDetailTotalCases);
            tvDetailTotalDeaths = itemView.findViewById(R.id.tvDetailTotalDeaths);
            tvDetailTotalRecovered = itemView.findViewById(R.id.tvDetailTotalRecovered);
            tvDetailTotalActive = itemView.findViewById(R.id.tvDetailTotalActive);
            tvDetailTotalCritical = itemView.findViewById(R.id.tvDetailTotalCritical);
        }
    }

    @Override
    public Filter getFilter() {
        return covidCountriesFilter;
    }

    private Filter covidCountriesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CovidCountry> filteredCovidCountry = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredCovidCountry.addAll(covidCountriesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CovidCountry itemCovidCountry : covidCountriesFull) {
                    if (itemCovidCountry.getmCovidCountry().toLowerCase().contains(filterPattern)) {
                        filteredCovidCountry.add(itemCovidCountry);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredCovidCountry;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            covidCountries.clear();
            covidCountries.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
