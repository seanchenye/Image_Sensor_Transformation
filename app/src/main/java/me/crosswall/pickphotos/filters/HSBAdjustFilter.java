package me.crosswall.pickphotos.filters;


import android.graphics.Color;

public class HSBAdjustFilter implements PointFilter {

    public float hFactor, sFactor, bFactor;
    public boolean canFilterIndexColorModel;
    private float[] hsb = new float[3];

    public HSBAdjustFilter() {
        this(0, 0, 0);
    }

    public HSBAdjustFilter(float r, float g, float b) {
        hFactor = r;
        sFactor = g;
        bFactor = b;
        canFilterIndexColorModel = true;
    }

    public void setHFactor( float hFactor ) {
        this.hFactor = hFactor;
    }

    public float getHFactor() {
        return hFactor;
    }

    public void setSFactor( float sFactor ) {
        this.sFactor = sFactor;
    }

    public float getSFactor() {
        return sFactor;
    }

    public void setBFactor( float bFactor ) {
        this.bFactor = bFactor;
    }

    public float getBFactor() {
        return bFactor;
    }

    public int filterRGB(int x, int y, int rgb) {
        int a = rgb & 0xff000000;
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        //Color.HSVToColor(r, g, b, hsb);

        hsb[0] += hFactor;
        while (hsb[0] < 0)
            hsb[0] += Math.PI*2;
        hsb[1] += sFactor;
        if (hsb[1] < 0)
            hsb[1] = 0;
        else if (hsb[1] > 1.0)
            hsb[1] = 1.0f;
        hsb[2] += bFactor;
        if (hsb[2] < 0)
            hsb[2] = 0;
        else if (hsb[2] > 1.0)
            hsb[2] = 1.0f;
        rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        return a | (rgb & 0xffffff);
    }

    public String toString() {
        return "Colors/Adjust HSB...";
    }
}
