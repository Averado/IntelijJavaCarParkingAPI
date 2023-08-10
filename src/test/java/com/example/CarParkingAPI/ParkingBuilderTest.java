package com.example.CarParkingAPI;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.CarParkingAPI.Services.ParkingService;
import com.example.CarParkingAPI.Entities.Parking;
import com.example.CarParkingAPI.Entities.ParkingBuilder;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingBuilderTest {

    @Autowired
    private ParkingService service;

    @Test
    public void testBuildBasicParking() {
        final Parking parking = new ParkingBuilder().withSquareSize(4).build();
        assertEquals(16, service.getAvailableSpaces(parking));
    }

    @Test
    public void testBuildParkingWithPedestrianExit() {
        final Parking parking = new ParkingBuilder().withSquareSize(3).withPedestrianExit(5).build();
        assertEquals(8, service.getAvailableSpaces(parking));
    }

    @Test
    public void testBuildParkingWithDisabledSlot() {
        final Parking parking = new ParkingBuilder().withSquareSize(2).withDisabledSpace(2).build();
        assertEquals(4, service.getAvailableSpaces(parking));
    }

    @Test
    public void testBuildParkingWithPedestrianExitsAndDisabledSlots() {
        final Parking parking = new ParkingBuilder().withSquareSize(10).withPedestrianExit(8).withPedestrianExit(42).withPedestrianExit(85).withDisabledSpace(2)
                .withDisabledSpace(47).withDisabledSpace(72).build();
        assertEquals(97, service.getAvailableSpaces(parking));
    }
}
