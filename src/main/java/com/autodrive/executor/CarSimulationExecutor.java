package com.autodrive.executor;

import com.autodrive.reader.ConsoleReader;
import com.autodrive.service.CarService;
import com.autodrive.validator.InputDataValidator;
import java.util.List;

public class CarSimulationExecutor {

  public static void main(String[] args) {
    // Generate the latest car position by reading input from the console, processing it using CarService, and printing the result.
    ConsoleReader reader = new ConsoleReader();
    System.out.println(getPromptMessage());
    List<String> carData = reader.readCarData();
    CarService carService = new CarService(new InputDataValidator());
    String result = carService.getLatestPosition(carData);
    System.out.println(result);
  }

  private static String getPromptMessage() {
    return  "Please enter the data in the following format:\n\n" +
            "Example:\n" +
            "10 10\n" +
            "1 2 N\n" +
            "FFRFFFRRLF\n\n" +
            "The first line indicates the width and height of the field.\n" +
            "The second line represents the current position and facing direction of the car. The direction can be N, E, S, or W.\n" +
            "The third line contains the commands to execute. Commands can be F, R, or L.\n\n" +
            "Enter Data:";
  }
}
