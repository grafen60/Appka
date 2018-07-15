package com.malousek.appka.Data.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan Malousek on 13.07.2018.
 * Base class for storing data about meteorite
 */

@Entity(tableName = "Meteorites")
public class Meteorite implements Parcelable {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String recclass;

    @ColumnInfo
    private float mass;

    @ColumnInfo
    private String year;

    @ColumnInfo
    @SerializedName("reclat")
    private double lat;

    @ColumnInfo
    @SerializedName("reclong")
    private double lng;

    public Meteorite(int id, String name, String recclass, float mass, String year, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.recclass = recclass;
        this.mass = mass;
        this.year = year;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRecclass() {
        return recclass;
    }

    public float getMass() {
        return mass;
    }

    public String getYear() {
        return year;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    protected Meteorite(Parcel in) {
        id = in.readInt();
        name = in.readString();
        recclass = in.readString();
        mass = in.readFloat();
        year = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(recclass);
        dest.writeFloat(mass);
        dest.writeString(year);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Meteorite> CREATOR = new Parcelable.Creator<Meteorite>() {
        @Override
        public Meteorite createFromParcel(Parcel in) {
            return new Meteorite(in);
        }

        @Override
        public Meteorite[] newArray(int size) {
            return new Meteorite[size];
        }
    };
}