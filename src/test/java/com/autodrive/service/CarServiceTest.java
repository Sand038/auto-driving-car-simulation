package com.autodrive.service;

import com.autodrive.exception.InputValidationFailedException;
import com.autodrive.validator.InputDataValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarServiceTest {

  private CarService carService;

  @BeforeEach
  void setUp() {
    carService = new CarService(new InputDataValidator());
  }

  @Test
  void getLatestPosition_whenInputDataIsEmpty() {
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(Collections.emptyList()));

    String expectedMessage = "Input data is empty!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenInvalidFieldDataCount() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10 11", "1 2 N", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid field data count!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenNegativeFieldData() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 -10", "1 2 N", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid field size!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenFieldDataIsNotANumber() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 ss", "1 2 N", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid field data format!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenInvalidCarDataCount() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "1 2 N S", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid car data count!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenInvalidCarPositions() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "-1 2 N", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid car positions!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenCarPositionsNotANumber() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "SS 2 N", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid car data format!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenCarDirectionIsInvalid() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "1 2 T", "FFRFFFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid direction!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenCarCommandsAreInvalid() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "1 2 N", "FFRFTFRRLF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid car commands!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getLatestPosition_whenInputDataIsValid() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "1 2 N", "FFRFFFRRLF")
    );
    String result = carService.getLatestPosition(inputData);
    Assertions.assertEquals("4 3 S", result);
  }

  @Test
  void getCollisionPoints_whenInputDataIsEmpty() {
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(Collections.emptyList()));

    String expectedMessage = "Input data is empty!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenInputDataHasInvalidRowCount() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid input data row count!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenInvalidFieldDataCount() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10 11", "", "A", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid field data count!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenNegativeFieldData() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 -10", "", "A", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid field size!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenFieldDataIsNotANumber() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 ss", "", "A", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getLatestPosition(inputData));

    String expectedMessage = "Invalid field data format!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenInvalidCarDataCount() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 N S", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid car data count!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenInvalidCarPositions() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "-1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid car positions!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenCarPositionsNotANumber() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "SS 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid car data format!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenCarDirectionIsInvalid() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 T", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid direction!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenCarCommandsAreInvalid() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 N", "FFRTFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid car commands!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenCarNameIsInvalid() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    Exception exception = Assertions.assertThrows(InputValidationFailedException.class,
        () -> carService.getCollisionPoints(inputData));

    String expectedMessage = "Invalid Car Name!";
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getCollisionPoints_whenInputDataIsValidAndCollisionHappens() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF")
    );
    String result = carService.getCollisionPoints(inputData);
    Assertions.assertEquals("A B\n5 4\n7", result);
  }

  @Test
  void getCollisionPoints_whenInputDataIsValidAndNoCollisionsHappen() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 N", "FFRRFF", "", "B", "3 4 W", "FRFLFF")
    );
    String result = carService.getCollisionPoints(inputData);
    Assertions.assertEquals("no collision", result);
  }

  @Test
  void getCollisionPoints_whenThreeCarsAndCollisionHappens() {
    List<String> inputData = new ArrayList<>(
        Arrays.asList("10 10", "", "A", "1 2 N", "FFRFFFFRRL", "", "B", "7 8 W", "FFLFFFFFFF", "",
            "C", "8 1 W", "FFFRFFFFFFF")
    );
    String result = carService.getCollisionPoints(inputData);
    Assertions.assertEquals("A B C\n5 4\n7", result);
  }
}