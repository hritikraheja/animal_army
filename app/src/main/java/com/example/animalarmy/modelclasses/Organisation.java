package com.example.animalarmy.modelclasses;

public class Organisation implements Comparable<Organisation>{
    private String name;
    private int icon;
    private String siteUrl;
    private String donationUrl;

    public String getDonationUrl() {
        return donationUrl;
    }

    public Organisation(String name, int icon, String siteUrl, String donationUrl) {
        this.name = name;
        this.icon = icon;
        this.siteUrl = siteUrl;
        this.donationUrl = donationUrl;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public Organisation(String name, int icon, String siteUrl) {
        this.name = name;
        this.icon = icon;
        this.siteUrl = siteUrl;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    @Override
    public int compareTo(Organisation o) {
        return this.name.compareTo(o.name);
    }
}
