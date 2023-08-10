package com.example.CarParkingAPI.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    int countByParkingIdAndPedestrianExitFalseAndParkedCarNull(Long id);

    ParkingSpace findByParkingIdAndIndex(Long id, Integer index);
}