package com.example.CarParkingAPI.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CreateRenterDTO {
    private String name;
    private String surName;
    private String personalCode;
    private String livingPlaceAddress;
    private String vehicleNo;
    private String phoneNo;
}
