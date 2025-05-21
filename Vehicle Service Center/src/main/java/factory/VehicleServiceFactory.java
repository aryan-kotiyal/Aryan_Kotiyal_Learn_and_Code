package factory;

import model.BikeService;
import model.CarService;
import model.TruckService;
import model.VehicleService;

public class VehicleServiceFactory {
    public static VehicleService getVehicleService(String vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        switch (vehicleType.toLowerCase()) {
            case "car":
                return new CarService();
            case "bike":
                return new BikeService();
            case "truck":
                return new TruckService();
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        }
    }
}
