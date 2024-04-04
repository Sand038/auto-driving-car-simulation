package com.autodrive.validator;

import static com.autodrive.model.Constants.SPACE;
import static com.autodrive.model.Directions.EAST;
import static com.autodrive.model.Directions.NORTH;
import static com.autodrive.model.Directions.SOUTH;
import static com.autodrive.model.Directions.WEST;

import com.autodrive.exception.InputValidationFailedException;
import com.autodrive.model.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputDataValidator {

  /**
   * Validates if input data is empty.
   *
   * @param data the input data to validate
   * @throws InputValidationFailedException if the input data is empty
   */
  public void validateData(List<String> data) {
    if (data == null || data.isEmpty()) {
      throw new InputValidationFailedException("Input data is empty!");
    }
  }

  /**
   * Validates the row count of input data.
   *
   * @param rowCount the row count of input data
   * @throws InputValidationFailedException if the row count is less than 4
   */
  public void validateDataRowCount(int rowCount) {
    if (rowCount < 4) {
      throw new InputValidationFailedException("Invalid input data row count!");
    }
  }

  /**
   * Validates the field data for size and format.
   *
   * @param fieldData the field data to validate
   * @throws InputValidationFailedException if the field data is invalid
   */
  public void validateFieldData(String fieldData) {
    String[] fieldDataList = fieldData.split(SPACE);
    if (fieldDataList.length != 2) {
      throw new InputValidationFailedException("Invalid field data count!");
    }
    try {
      int fieldX = Integer.parseInt(fieldDataList[0]);
      int fieldY = Integer.parseInt(fieldDataList[1]);
      if (fieldX <= 0 || fieldY <= 0) {
        throw new InputValidationFailedException("Invalid field size!");
      }
    } catch (NumberFormatException e) {
      throw new InputValidationFailedException("Invalid field data format!");
    }
  }

  /**
   * Validates the car data for position, direction, and format.
   *
   * @param carData the car data to validate
   * @param field the field object representing the simulation field
   * @throws InputValidationFailedException if the car data is invalid
   */
  public void validateCarData(String carData, Field field) {
    String[] initialPosition = carData.split(SPACE);
    if (initialPosition.length != 3) {
      throw new InputValidationFailedException("Invalid car data count!");
    }
    try {
      int initialX = Integer.parseInt(initialPosition[0]);
      int initialY = Integer.parseInt(initialPosition[1]);
      if (initialX < 0 || initialX > field.getWidth() || initialY < 0
          || initialY > field.getHeight()) {
        throw new InputValidationFailedException("Invalid car positions!");
      }
    } catch (NumberFormatException e) {
      throw new InputValidationFailedException("Invalid car data format!");
    }
    String initialDirection = initialPosition[2];
    if (initialDirection.isEmpty() || !Arrays.asList(NORTH, EAST, SOUTH, WEST)
        .contains(initialDirection.charAt(0))) {
      throw new InputValidationFailedException("Invalid direction!");
    }
  }

  /**
   * Validates the car commands for format and content.
   *
   * @param commands the car commands to validate
   * @throws InputValidationFailedException if the commands are invalid
   */
  public void validateCommands(String commands) {
    String regex = "^[LRF]+$";
    if (!Pattern.matches(regex, commands)) {
      throw new InputValidationFailedException("Invalid car commands!");
    }
  }

  /**
   * Validates the car name.
   *
   * @param carName the car name to validate
   * @throws InputValidationFailedException if the car name is invalid
   */
  public void validateCarName(String carName) {
    if (carName == null || carName.isEmpty()) {
      throw new InputValidationFailedException("Invalid Car Name!");
    }
  }
}
