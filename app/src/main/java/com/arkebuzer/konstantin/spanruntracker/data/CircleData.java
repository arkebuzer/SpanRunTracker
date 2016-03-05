package com.arkebuzer.konstantin.spanruntracker.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Konstantin on 05.03.2016.
 */
public class CircleData implements Parcelable {

    private Integer workTime;
    private Integer restTime;

    public CircleData() {
        workTime = -1;
        restTime = -1;
    }

    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime(Integer restTime) {
        this.restTime = restTime;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(workTime);
        dest.writeInt(restTime);
    }

    public static final Parcelable.Creator<CircleData> CREATOR
            = new Parcelable.Creator<CircleData>() {
        public CircleData createFromParcel(Parcel in) {
            return new CircleData(in);
        }

        public CircleData[] newArray(int size) {
            return new CircleData[size];
        }
    };

    private CircleData(Parcel in) {
        workTime = in.readInt();
        restTime = in.readInt();
    }
}
