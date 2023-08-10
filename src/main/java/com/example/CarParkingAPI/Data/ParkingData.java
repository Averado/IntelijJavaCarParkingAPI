package com.example.CarParkingAPI.Data;

import java.util.ArrayList;

public class ParkingData {

    private Integer size;
    private ArrayList<Integer> pedestrianExits = new ArrayList<Integer>();
    private ArrayList<Integer> disabledSpaces = new ArrayList<Integer>();

    public ParkingData() {
        super();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ArrayList<Integer> getPedestrianExits() {
        return pedestrianExits;
    }

    public void setPedestrianExits(ArrayList<Integer> pedestrianExits) {
        this.pedestrianExits = pedestrianExits;
    }

    public ArrayList<Integer> getDisabledSpaces() {
        return getDisabledSpaces();
    }

    public void setDisabledSpaces(ArrayList<Integer> disabledSpaces) {
        this.disabledSpaces = disabledSpaces;
    }


}
