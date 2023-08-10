package com.example.CarParkingAPI.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.example.CarParkingAPI.Entities.ParkingRepository;


@Service
public interface ParkingRepository extends JpaRepository<Parking, Long> {

}