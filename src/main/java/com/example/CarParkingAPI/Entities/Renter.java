package com.example.CarParkingAPI.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor

public class Renter {

    private Integer size;
    private ArrayList<Integer> pedestrianExits = new ArrayList<Integer>();
    private ArrayList<Integer> disabledBays = new ArrayList<Integer>();

    public Renter withSquareSize(final int size) {
        this.size = size;
        return this;
    }

    public Renter withPedestrianExit(final int pedestrianExitIndex) {
        this.pedestrianExits.add(pedestrianExitIndex);
        return this;
    }

    public Renter withDisabledBay(final int disabledBayIndex) {
        this.disabledBays.add(disabledBayIndex);
        return this;
    }

    public Parking build() {
        Parking parking = new Parking(this.size);
        ParkingSpace space = null;

        for (int i = 0; i < (this.size * this.size); i++) {
            space = new ParkingSpace(i, pedestrianExits.contains(i), disabledBays.contains(i), parking);
            parking.addBay(space);
        }

        pedestrianExits.stream().forEach(peIndex -> parking.getSpaces().stream().forEach(p -> p.setDistanceToExit(Integer.min(p.getDistanceToExit(), Math.abs(peIndex - p.getIndex())))));

        return parking;
    }
}

