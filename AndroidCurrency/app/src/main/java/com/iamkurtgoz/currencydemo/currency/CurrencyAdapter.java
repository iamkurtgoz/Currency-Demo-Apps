package com.iamkurtgoz.currencydemo.currency;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iamkurtgoz.currencydemo.R;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyHolder> {

    private ArrayList<CurrencyModel> currencyModel;

    public CurrencyAdapter(ArrayList<CurrencyModel> currencyModel){
        this.currencyModel = currencyModel;
    }

    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_currency_row, viewGroup, false);
        return new CurrencyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int i) {
        CurrencyModel model = (CurrencyModel) currencyModel.get(i);

        holder.imgHeader.setImageResource(model.getImageResource());
        holder.textTitle.setText(model.getName());
        holder.textValue.setText(model.getValue());
    }

    @Override
    public int getItemCount() {
        return currencyModel.size();
    }
}
