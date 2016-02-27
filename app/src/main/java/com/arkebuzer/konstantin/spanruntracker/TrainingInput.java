package com.arkebuzer.konstantin.spanruntracker;


/**
 * Created by Konstantin on 27.02.2016.
 */
public class TrainingInput {

    private Integer circlesCnt;
    private Integer workDistance;
    private Integer restDistance;

    public TrainingInput() {
    }

    public TrainingInput(Integer circlesCnt, Integer workDistance, Integer restDistance) {
        this.circlesCnt = circlesCnt;
        this.workDistance = workDistance;
        this.restDistance = restDistance;
    }

    @Override
    public String toString()
    {
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

}
