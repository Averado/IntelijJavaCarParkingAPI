package com.example.CarParkingAPI.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParkingSpace {
    private int number;
    private List<Renter> licencePlate;
}
