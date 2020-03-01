package com.company.templateapplication.entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dummies")
public class Dummy implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private Integer priority;

    public Dummy(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    protected Dummy(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            priority = null;
        } else {
            priority = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (priority == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(priority);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dummy> CREATOR = new Creator<Dummy>() {
        @Override
        public Dummy createFromParcel(Parcel in) {
            return new Dummy(in);
        }

        @Override
        public Dummy[] newArray(int size) {
            return new Dummy[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    @NonNull
    @Override
    public String toString() {
        return "{ id: " + id + "; name: " + name + "; priority: " + priority + " }";
    }
}
