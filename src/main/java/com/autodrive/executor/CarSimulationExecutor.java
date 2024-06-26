package com.autodrive.executor;

import com.autodrive.reader.ConsoleReader;
import com.autodrive.service.CarService;
import com.autodrive.validator.InputDataValidator;
import java.util.List;

public class CarSimulationExecutor {

  public static void main(String[] args) {
    // Generate the latest car position by reading input from the console, processing it using CarService, and printing the result.
    InputDataValidator inputDataValidator = new InputDataValidator();
    ConsoleReader reader = new ConsoleReader(inputDataValidator);
    List<String> carData = reader.readCarData();
    CarService carService = new CarService(inputDataValidator);
    String result = carService.getLatestPosition(carData);
    System.out.println(result);
  }
}
