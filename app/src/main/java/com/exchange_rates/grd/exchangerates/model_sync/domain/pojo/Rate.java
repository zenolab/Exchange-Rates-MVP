package com.exchange_rates.grd.exchangerates.model_sync.domain.pojo;

import com.google.gson.annotations.SerializedName;

//public class Rate implements Serializable
public class Rate
{

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("ask")
    private String ask;

    @SerializedName("bid")
    private String bid;

    @SerializedName("lasttime")
    private String lasttime;

    @SerializedName("change")
    private String change;

    @SerializedName("digits")
    private String digits;

    @SerializedName("change24h")
    private String change24h;

    @SerializedName("img")
    private String img;;


    //----------------Accessors-----------------------
    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public String getAsk ()
    {
        return ask;
    }

    public void setAsk (String ask)
    {
        this.ask = ask;
    }

    public String getBid ()
    {
        return bid;
    }

    public void setBid (String bid)
    {
        this.bid = bid;
    }

    public String getLasttime ()
    {
        return lasttime;
    }

    public void setLasttime (String lasttime)
    {
        this.lasttime = lasttime;
    }

    public String getChange ()
    {
        return change;
    }

    public void setChange (String change)
    {
        this.change = change;
    }

    public String getDigits ()
    {
        return digits;
    }

    public void setDigits (String digits)
    {
        this.digits = digits;
    }



    public String getChange24h ()
    {
        return change24h;
    }

    public void setChange24h (String change24h)
    {
        this.change24h = change24h;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    //--------------------------------------
    @Override
    public String toString() {
        return "Rate{" +
                "symbol='" + symbol + '\'' +
                ", ask='" + ask + '\'' +
                ", bid='" + bid + '\'' +
                ", lasttime='" + lasttime + '\'' +
                ", change='" + change + '\'' +
                ", digits='" + digits + '\'' +
                ", change24h='" + change24h + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
    //--------------------------------------
}