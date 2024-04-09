package com.autodrive.executor;

import com.autodrive.reader.ConsoleReader;
import com.autodrive.service.CarService;
import com.autodrive.validator.InputDataValidator;

import java.util.List;

public class CarSimulationCollisionExecutor {

  public static void main(String[] args) {
    // Perform collision detection by reading input from the console, processing it using CarService, and printing the result.
    InputDataValidator inputDataValidator = new InputDataValidator();
    ConsoleReader reader = new ConsoleReader(inputDataValidator);
    List<String> collisionData = reader.readCollisionData();
    CarService carService = new CarService(inputDataValidator);
    String result = carService.getCollisionPoints(collisionData);
    System.out.println(result);
  }
}
