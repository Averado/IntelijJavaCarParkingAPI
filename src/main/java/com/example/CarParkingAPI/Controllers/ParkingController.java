package com.example.CarParkingAPI.Controllers;

import com.example.CarParkingAPI.Data.ParkingData;
import com.example.CarParkingAPI.Entities.Parking;
import com.example.CarParkingAPI.Entities.ParkingSpace;
import com.example.CarParkingAPI.Services.ParkingService;

import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Component
@Path("/CarParkingAPI")
public class ParkingController {

    private final ParkingService service;
    private Long id;
    private char carType;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a parking instance")
    public Response createParking(ParkingData parkingData) {
        Parking parking = service.createParking(parkingData);
        return Response.ok(parking).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailable(@PathParam("id") Long id) {
        Optional<Parking> parkingOpt = service.getParkingById(id);
        if (parkingOpt.isPresent()) {
            Parking parking = parkingOpt.get();
            long free = service.getAvailableSpaces(parking);
            return Response.ok(free).build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/park/{carType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response parkCar(@PathParam("id") Long id, @PathParam("carType") char carType) {
        Optional<Parking> parkingOpt = service.getParkingById(id);
        if (parkingOpt.isPresent()) {
            Parking parking = parkingOpt.get();
            Integer index = service.parkCar(parking, carType);
            return Response.ok(index).build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/unpark/{index}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response unparkCar(@PathParam("id") Long id, @PathParam("index") Integer index) {
        Optional<Parking> parkingOpt = service.getParkingById(id);
        if (parkingOpt.isPresent()) {
            Parking parking = parkingOpt.get();
            boolean unparked = service.unparkCar(parking, index);
            return Response.ok(unparked).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/print")
    @Produces(MediaType.APPLICATION_JSON)
    public Response print(@PathParam("id") Long id) {
        Optional<Parking> parkingOpt = service.getParkingById(id);
        if (parkingOpt.isPresent()) {
            Parking parking = parkingOpt.get();
            String parkingMap = service.printParking(parking);
            return Response.ok(parkingMap).build();
        }
        return Response.noContent().build();
    }
}
