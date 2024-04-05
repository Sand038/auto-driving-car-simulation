package com.autodrive.executor;

import com.autodrive.reader.ConsoleReader;
import com.autodrive.service.CarService;
import com.autodrive.validator.InputDataValidator;
import java.util.List;

public class CarSimulationCollisionExecutor {

  public static void main(String[] args) {
    // Perform collision detection by reading input from the console, processing it using CarService, and printing the result.
    ConsoleReader reader = new ConsoleReader();
    System.out.println(getPromptMessage());
    List<String> collisionData = reader.readCollisionData();
    CarService carService = new CarService(new InputDataValidator());
    String result = carService.getCollisionPoints(collisionData);
    System.out.println(result);
  }

  private static String getPromptMessage() {
    return  "Please enter the data in the following format:\n\n" +
            "Example:\n" +
            "10 10\n\n" +
            "A\n" +
            "1 2 N\n" +
            "FFRFFFFRRL\n\n" +
            "B\n" +
            "7 8 W\n" +
            "FFLFFFFFFF\n\n" +
            "The first section indicates the width and height of the field.\n" +
            "Each section of the following sections will indicate details for each car as follows:\n" +
            "- First line represents the name of the car.\n" +
            "- Second line represents the current position and facing direction of the car. The direction can be N, E, S, or W.\n" +
            "- The third line contains the commands to execute. Commands can be F, R, or L.\n" +
            "The execution begins once you input empty details as the last car section and press Enter. (This means, after entering the last valid car details, pressing Enter three times will start executing the program.)\n" +
            "Enter Data:";
  }
}
