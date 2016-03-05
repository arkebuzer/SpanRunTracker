package com.arkebuzer.konstantin.spanruntracker.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Konstantin on 05.03.2016.
 */
public class TrainingData implements Parcelable {

    private ArrayList<CircleData> circles;
    private Integer circlesCnt;
    private Integer workDistance;
    private Integer restDistance;

    public TrainingData(Integer circlesCnt, Integer workDistance, Integer restDistance) {
        this.circlesCnt = circlesCnt;
        this.workDistance = workDistance;
        this.restDistance = restDistance;
        circles = new ArrayList<CircleData>();
        for (Integer i = 0; i < 10; i++) {
            circles.add(new CircleData());
        }
    }

    public void setCircleWorkTime(Integer workTime, Integer circleNum) {
        circles.get(circleNum).setWorkTime(workTime);
    }

    public void setCircleRestTime(Integer restTime, Integer circleNum) {
        circles.get(circleNum).setRestTime(restTime);
    }

    @Override
    public String toString() {
        return this.circlesCnt + " X " + this.workDistance + " / " + this.restDistance;
    }

    public Integer getCirclesCnt() {
        return circlesCnt;
    }

    public void setCirclesCnt(Integer circlesCnt) {
        this.circlesCnt = circlesCnt;
    }

    public Integer getWorkDistance() {
        return workDistance;
    }

    public void setWorkDistance(Integer workDistance) {
        this.workDistance = workDistance;
    }

    public Integer getRestDistance() {
        return restDistance;
    }

    public void setRestDistance(Integer restDistance) {
        this.restDistance = restDistance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(circlesCnt);
        dest.writeInt(workDistance);
        dest.writeInt(restDistance);
        dest.writeTypedList(circles);
    }

    public static final Parcelable.Creator<TrainingData> CREATOR
            = new Parcelable.Creator<TrainingData>() {
        public TrainingData createFromParcel(Parcel in) {
            return new TrainingData(in);
        }

        public TrainingData[] newArray(int size) {
            return new TrainingData[size];
        }
    };

    private TrainingData(Parcel in) {
        circlesCnt = in.readInt();
        workDistance = in.readInt();
        restDistance = in.readInt();
        in.readTypedList(circles, CircleData.CREATOR);
    }
}
