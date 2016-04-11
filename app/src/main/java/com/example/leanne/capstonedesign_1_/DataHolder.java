package com.example.leanne.capstonedesign_1_;

/**
 * Created by Chloe on 4/10/2016.
 */
public class DataHolder {

    public DataHolder() {
        //skipSplash = false;
        skipLure = false;
    }

    //private boolean skipSplash;
    private boolean skipLure;

    //public void setSkipSplash(boolean skipSplash){ this.skipSplash = skipSplash; }
    //public boolean getSkipSplash(){ return skipSplash; }
    public void setSkipLure(boolean skipLure) {
        this.skipLure = skipLure;
    }

    public boolean getSkipLure() {
        return skipLure;
    }

    // ***
    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }
}
