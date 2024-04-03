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

  public void validateData(List<String> data) {
    if (data == null || data.isEmpty()) {
      throw new InputValidationFailedException("Input data is empty!");
    }
  }

  public void validateDataRowCount(int rowCount) {
    if (rowCount < 4) {
      throw new InputValidationFailedException("Invalid input data row count!");
    }
  }

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

  public void validateCommands(String commands) {
    String regex = "^[LRF]+$";
    if (!Pattern.matches(regex, commands)) {
      throw new InputValidationFailedException("Invalid car commands!");
    }
  }

  public void validateCarName(String carName) {
    if (carName == null || carName.isEmpty()) {
      throw new InputValidationFailedException("Invalid Car Name!");
    }
  }
}
