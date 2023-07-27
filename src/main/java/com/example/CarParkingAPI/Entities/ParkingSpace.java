package com.example.CarParkingAPI.Entities;

import com.example.CarParkingAPI.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
@Entity

public class ParkingSpace {

    @Id
    @GeneratedValue
    private Long spaceId;

    @ApiModelProperty
    private Integer index;

    @ApiModelProperty
    private boolean disabled = false;

    @ApiModelProperty
    private boolean pedestrianExit = false;

    @ApiModelProperty
    private Integer distanceToExit = Integer.MAX_VALUE;

    private char parkedCar;

    @ManyToOne(targetEntity = Parking.class)
    @JoinColumn(name = "id")
    @JsonIgnore
    private Parking parking;

    public ParkingSpace() {
        super();
    }

    public ParkingSpace(Integer index) {
        super();
        this.index = index;
    }

    public ParkingSpace(Integer index, boolean pedestrianExit, boolean disabled, Parking parking) {
        super();
        this.index = index;
        this.pedestrianExit = pedestrianExit;
        this.disabled = disabled;
        this.parking = parking;
        initializeParkedCar();
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isPedestrianExit() {
        return pedestrianExit;
    }

    public void setPedestrianExit(boolean pedestrianExit) {
        this.pedestrianExit = pedestrianExit;
    }

    public char getParkedCar() {
        return parkedCar;
    }

    public void setParkedCar(char parkedCar) {
        this.parkedCar = parkedCar;
    }

    public Integer getDistanceToExit() {
        return distanceToExit;
    }

    public void setDistanceToExit(Integer distanceToExit) {
        this.distanceToExit = distanceToExit;
    }

    public boolean isAvailable(char carType) {
        if (carType == Constants.CAR_TYPE_DISABLED) {
            return !this.isPedestrianExit() && this.parkedCar == Constants.DISABLED_EMPTY;
        } else {
            return !this.isPedestrianExit() && this.parkedCar == Constants.NON_DISABLED_EMPTY;
        }
    }

    public boolean isAvailable() {
        return !this.isPedestrianExit() && (this.parkedCar == Constants.DISABLED_EMPTY || this.parkedCar == Constants.NON_DISABLED_EMPTY);
    }

    public void initializeParkedCar() {
        this.parkedCar = this.pedestrianExit ? Constants.PEDESTRIAN_EXIT : this.disabled ? Constants.DISABLED_EMPTY : Constants.NON_DISABLED_EMPTY;
    }




}
