package com.example.CarParkingAPI.Entities;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity

public class Parking {
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty
    private Integer size;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ParkingSpace> spaces = new HashSet<ParkingSpace>();

    public Parking() {
        super();
    }

    public Parking(Integer size) {
        super();
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void addBay(ParkingSpace space) {
        boolean add = this.getSpaces().add(space);
    }

    public Set<ParkingSpace> getSpaces() {
        return spaces;
    }

    public void setBays(Set<ParkingSpace> spaces) {
        this.spaces = spaces;
    }


}


