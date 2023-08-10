package com.example.CarParkingAPI.Services;

import com.example.CarParkingAPI.Data.ParkingData;
import com.example.CarParkingAPI.Entities.*;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.CarParkingAPI.Entities.ParkingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Component
@Repository
@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    /**
     * Creates a new Parking with the provided configuration
     *
     * @param parkingData
     * @return a new Parking
     */
    public Parking createParking(ParkingData parkingData) {

        ParkingBuilder parkingBuilder = new ParkingBuilder().withSquareSize(parkingData.getSize());

        for (Integer i : parkingData.getPedestrianExits()) {
            parkingBuilder.withPedestrianExit(i);
        }

        for (Integer i : parkingData.getDisabledSpaces()) {
            parkingBuilder.withDisabledSpace(i);
        }

        Parking parking = parkingBuilder.build();

        parkingRepository.save(parking);
        return parking;
    }

    /**
     * Gets the parking with the provided id from DB
     *
     * @param id
     * @return Parking
     */
    public Optional<Parking> getParkingById(Long id) {
        return parkingRepository.findById(id);
    }

    /**
     * Get number of availbale bays of the specified parking
     *
     * @param parking
     * @return
     */
    public long getAvailableSpaces(Parking parking) {
        return parking.getSpaces().stream().filter(pb -> pb.isAvailable()).count();
    }

    /**
     * Get first available bay of the parking for a car of carType
     *
     * @param parkingId
     * @param carType
     * @return
     */
    private Integer getFirstAvailableSpace(Long parkingId, char carType) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);
        if (parkingOpt.isPresent()) {
            Parking parking = parkingOpt.get();
            Optional<ParkingSpace> parkingSpaceOpt = parking.getSpaces().stream().filter(pb -> pb.isAvailable(carType)).sorted(Comparator.comparing(ParkingSpace::getDistanceToExit).thenComparing(ParkingSpace::getIndex)).findFirst();
            if (parkingSpaceOpt.isPresent()) {
                return parkingSpaceOpt.get().getIndex();
            }
        }

        return -1;
    }

    /**
     * Park a car of the given type
     *
     * @param parking
     * @param carType
     * @return
     */
    public Integer parkCar(Parking parking, char carType) {
        Integer indexToPark = getFirstAvailableSpace(parking.getId(), carType);
        if (indexToPark > 0) {
            Optional<ParkingSpace> parkingSpaceOpt = parking.getSpaces().stream().filter(pb -> pb.getIndex().equals(indexToPark)).findFirst();
            if (parkingSpaceOpt.isPresent()) {
                ParkingSpace parkingSpace = parkingSpaceOpt.get();
                parkingSpace.setParkedCar(carType);
                parkingSpaceRepository.saveAndFlush(parkingSpace);
            }
        }
        return indexToPark;
    }

    /**
     * Unpark a car from the given index
     *
     * @param parking
     * @param index
     * @return
     */
    public boolean unparkCar(Parking parking, Integer index) {
        Optional<ParkingSpace> parkingSpaceOpt = parking.getSpaces().stream().filter(pb -> pb.getIndex().equals(index)).findFirst();
        if (parkingSpaceOpt.isPresent()) {
            ParkingSpace parkingSpace = parkingSpaceOpt.get();
            if (parkingSpace.isAvailable() || parkingSpace.isPedestrianExit()) {
                return false;
            }

            parkingSpace.initializeParkedCar();
            parkingSpaceRepository.saveAndFlush(parkingSpace);
            return true;
        }
        return false;
    }

    /**
     * Generates a String representation of the parking
     *
     * @param parking
     * @return
     */
    public String printParking(Parking parking) {

        StringBuffer strBuffParking = new StringBuffer();
        int totalSize = parking.getSize() * parking.getSize();
        boolean needReverse = false;
        for (int i = 0; i < totalSize; i = i + parking.getSize()) {
            final Integer minIndex = i;
            final Integer maxIndex = i + parking.getSize() - 1;
            StringBuffer strBuffLane = new StringBuffer();
            parking.getSpaces().stream().filter(pb -> (pb.getIndex() >= minIndex && pb.getIndex() <= maxIndex))
                    .sorted(Comparator.comparing(ParkingSpace::getIndex))
                    .forEachOrdered(pb -> strBuffLane.append(printSpace(parking.getSize(), pb)));

            if (needReverse) {
                strBuffLane.reverse();
                needReverse = false;
            } else {
                needReverse = true;
            }

            if (i + parking.getSize() < totalSize) {
                strBuffLane.append("\n");
            }

            strBuffParking.append(strBuffLane);
        }

        return strBuffParking.toString();

    }

    private char printSpace(Integer parkingSize, ParkingSpace parkingSpace) {
        return parkingSpace.getParkedCar();
    }
}

