package com.iamkurtgoz.currencydemo.currency;

public class CurrencyModel {

    private int imageResource = -1;
    private String name, value;

    public CurrencyModel(int imageResource, String name, String value){
        this.imageResource = imageResource;
        this.name = name;
        this.value = value;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
