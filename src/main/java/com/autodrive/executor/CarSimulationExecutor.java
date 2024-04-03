package com.autodrive.executor;

import com.autodrive.reader.ConsoleReader;
import com.autodrive.service.CarService;
import com.autodrive.validator.InputDataValidator;
import java.util.List;

public class CarSimulationExecutor {

  public static void main(String[] args) {
    ConsoleReader reader = new ConsoleReader();
    List<String> carData = reader.readCarData();
    CarService carService = new CarService(new InputDataValidator());
    String result = carService.getLatestPosition(carData);
    System.out.println(result);
  }
}
