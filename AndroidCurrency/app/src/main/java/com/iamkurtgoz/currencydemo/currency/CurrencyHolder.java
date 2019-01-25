package com.iamkurtgoz.currencydemo.currency;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iamkurtgoz.currencydemo.R;

public class CurrencyHolder extends RecyclerView.ViewHolder {

    public ImageView imgHeader;
    public TextView textTitle, textValue;


    public CurrencyHolder(@NonNull View itemView) {
        super(itemView);

        imgHeader = (ImageView) itemView.findViewById(R.id.list_item_currency_row_imgHeader);
        textTitle = (TextView) itemView.findViewById(R.id.list_item_currency_row_textTitle);
        textValue = (TextView) itemView.findViewById(R.id.list_item_currency_row_textValue);

    }
}
