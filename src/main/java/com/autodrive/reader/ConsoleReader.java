package com.autodrive.reader;

import com.autodrive.exception.InputValidationFailedException;
import com.autodrive.model.Field;
import com.autodrive.validator.InputDataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.autodrive.model.Constants.SPACE;

public class ConsoleReader {

  private final InputDataValidator inputDataValidator;

  public ConsoleReader(InputDataValidator inputDataValidator) {
    this.inputDataValidator = inputDataValidator;
  }

  /**
   * Reads collision data from the console until the user decides to stop adding more cars.
   *
   * @return The collision data read from the console as a list of strings.
   */
  public List<String> readCollisionData() {
    Scanner scanner = new Scanner(System.in);
    List<String> data = new ArrayList<>();
    String fieldData = readFieldData(scanner);
    boolean test = true;
    data.add(fieldData);
    int count = 1;
    while (test) {
      System.out.println("------------------- Enter details for Car #" + count + " -------------------");
      data.add(readCarName(scanner));
      data.add(readCarPosition(scanner, fieldData));
      data.add(readCarCommands(scanner));
      System.out.println("Do you want to add more cars? (y/n)");
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("n")) {
        break;
      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("Invalid input. More cars will be added.");
      }
      count++;
    }
    return data;
  }

  /**
   * Reads data for a car from the console.
   *
   * @return The car data read from the console as a list of strings.
   */
  public List<String> readCarData() {
    Scanner scanner = new Scanner(System.in);
    List<String> data = new ArrayList<>();
    String fieldData = readFieldData(scanner);
    data.add(fieldData);
    data.add(readCarPosition(scanner, fieldData));
    data.add(readCarCommands(scanner));
    scanner.close();
    return data;
  }

  private String readFieldData(Scanner scanner) {
    String input;
    do {
      System.out.println("Please enter the width and height of the field (ex:10 10): ");
      try {
        input = scanner.nextLine();
        inputDataValidator.validateData(input);
        inputDataValidator.validateFieldData(input);
      } catch (InputValidationFailedException e) {
        System.out.println("Please renter data, Validation Error: " + e.getMessage());
        input = null; // Set input to null to prompt the user to re-enter
      }
    } while (input == null);
    return input;
  }

  private String readCarName(Scanner scanner) {
    String input;
    do {
      System.out.println("Please enter car name (ex:A): ");
      try {
        input = scanner.nextLine();
        inputDataValidator.validateData(input);
        inputDataValidator.validateCarName(input);
      } catch (InputValidationFailedException e) {
        System.out.println("Please renter data, Validation Error: " + e.getMessage());
        input = null; // Set input to null to prompt the user to re-enter
      }
    } while (input == null);
    return input;
  }

  private String readCarPosition(Scanner scanner, String fieldData) {
    String input;
    String[] size = fieldData.split(SPACE);
    Field field = new Field(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    do {
      System.out.println("Please enter the current position and facing direction of the car (ex:1 2 N): ");
      try {
        input = scanner.nextLine();
        inputDataValidator.validateData(input);
        inputDataValidator.validateCarData(input, field);
      } catch (InputValidationFailedException e) {
        System.out.println("Please renter data, Validation Error: " + e.getMessage());
        input = null; // Set input to null to prompt the user to re-enter
      }
    } while (input == null);
    return input;
  }

  private String readCarCommands(Scanner scanner) {
    String input;
    do {
      System.out.println("Please enter the commands for the car (ex:FFRFFFRRLF): ");
      try {
        input = scanner.nextLine();
        inputDataValidator.validateData(input);
        inputDataValidator.validateCommands(input);
      } catch (InputValidationFailedException e) {
        System.out.println("Please renter data, Validation Error: " + e.getMessage());
        input = null;
      }
    } while (input == null);
    return input;
  }
}
