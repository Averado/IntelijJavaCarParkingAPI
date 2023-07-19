package com.example.CarParkingAPI.Controllers;

import com.example.CarParkingAPI.Entities.Renter;
import com.example.CarParkingAPI.Services.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/renters")

public class RenterController {
@Autowired
    RenterService renterService;
@GetMapping
    public List<Renter> getAllRenters(){
    return this.renterService.getAllRenters();
}
@GetMapping("/{id")
    public ResponseEntity<Renter> getRenterById(@PathVariable Long id){
    try {
        return ResponseEntity.ok().body(this.renterService.findRenterById(id));
    }catch (NoSuchElementException e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Renter by ID: %s not found", id));
    }
    @PostMapping
            public ResponseEntity<Renter> addRenter(@RequestBody CreaterRenterDTO createRenterDTO){
        //reikia RenterDTO
        try {
            return ResponseEntity.status((HttpStatus.CREATED).body(this.renterService.addRenter(new Renter(createRenterDTO)));
        }catch (InvalidIdException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e,getMessage(), e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Renter> deleteRenterById(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.renterService.deleteRenterById(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Renter by ID: %s not found", id));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Renter>> replaceRenterById(@PathVariable Long id, @RequestBody CreateRenterDTO createRenterDTO) {
        try {
            return ResponseEntity.ok().body(this.renterService.replaceRenterById(id, createRenterDTO));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Renter by ID: %s not found", id));
        }
    }
}


}
