package com.example.CarParkingAPI.Entities;

import java.util.ArrayList;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {

    private Integer size;
    private ArrayList<Integer> pedestrianExits = new ArrayList<Integer>();
    private ArrayList<Integer> disabledSpaces = new ArrayList<Integer>();

    public ParkingBuilder withSquareSize(final int size) {
        this.size = size;
        return this;
    }

    public ParkingBuilder withPedestrianExit(final int pedestrianExitIndex) {
        this.pedestrianExits.add(pedestrianExitIndex);
        return this;
    }

    public ParkingBuilder withDisabledSpace(final int disabledSpaceIndex) {
        this.disabledSpaces.add(disabledSpaceIndex);
        return this;
    }

    public Parking build() {
        Parking parking = new Parking(this.size);
        ParkingSpace space = null;

        for (int i = 0; i < (this.size * this.size); i++) {
            space = new ParkingSpace(i, pedestrianExits.contains(i), disabledSpaces.contains(i), parking);
            parking.addSpace(space);
        }

        pedestrianExits.stream().forEach(peIndex -> parking.getSpaces().stream().forEach(p -> p.setDistanceToExit(Integer.min(p.getDistanceToExit(), Math.abs(peIndex - p.getIndex())))));

        return parking;
    }
}
