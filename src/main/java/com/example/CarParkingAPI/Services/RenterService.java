package com.example.CarParkingAPI.Services;

import com.example.CarParkingAPI.Entities.Renter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RenterService {
    private List<Renter> rentersList = new ArrayList<>();

    public List<Renter> getAllRenters(){
        if (rentersList.isEmpty()){
           //createRenterList
        }
        return rentersList;
    }
    public Renter addRenter(Renter renter){
        this.rentersList.add(renter);
        return renter;
    }
    public Renter deleteRenterById(Long id){
        Renter renterToDelete = findRenterById(id);
        rentersList.remove(renterToDelete);
        return renterToDelete;
    }
    public List<Renter> replaceRenterById(Long id, CreateRenterDTO, CreateRenterDTO){
        //reikia RenterDTO
        Renter renterToReplace = findRenterById(id);
        int indexOfRenter = this.rentersList.indexOf(renterToReplace);
        this.rentersList.set(indexOfRenter, new Renter(renterToReplace, createRenterDTO));
        //reikia RenterDTO
        return this.rentersList;
    }

    public Renter findRenterById(Long id){
        Renter renterToGet =
                this.rentersList.stream()
                        .filter(rent -> rent.getId().equals(id))
                        .findFirst().orElseThrow();
        return renterToGet;

    }

}
