package com.example.CarParkingAPI.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class Renter {
    private Long id;
    private String name;
    private String surName;
    private String personalCode;
    private String livingPlaceAddress;
    private String vehicleNo;
    private String phoneNo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
