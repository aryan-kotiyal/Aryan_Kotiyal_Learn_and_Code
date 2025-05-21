package org.app;

import factory.VehicleServiceFactory;
import model.VehicleService;

public class ServiceCenter {
    public static void main(String[] args) {
        VehicleService service1 = VehicleServiceFactory.getVehicleService("car");
        service1.performService();

        VehicleService service2 = VehicleServiceFactory.getVehicleService("bike");
        service2.performService();

        VehicleService service3 = VehicleServiceFactory.getVehicleService("truck");
        service3.performService();
    }
}