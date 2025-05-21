package model;

public class BikeService implements VehicleService{
    @Override
    public void performService() {
        System.out.println("Performing Bike Service: Chain lubrication, Brake tightening");
    }
}
