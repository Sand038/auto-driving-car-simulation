package com.autodrive.service;

import static com.autodrive.model.Constants.SPACE;
import static com.autodrive.model.Directions.FORWARD;
import static com.autodrive.model.Directions.LEFT;
import static com.autodrive.model.Directions.RIGHT;

import com.autodrive.model.Car;
import com.autodrive.model.Field;
import com.autodrive.validator.InputDataValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarService {

  private static final String NO_COLLISION = "no collision";
  private final InputDataValidator inputDataValidator;

  /**
   * Constructor for CarService class.
   *
   * @param inputDataValidator the input data validator
   */
  public CarService(InputDataValidator inputDataValidator) {
    this.inputDataValidator = inputDataValidator;
  }

  /**
   * Retrieves the latest position of a car based on the given input data.
   *
   * @param data the input data containing field size, car initial position, and commands
   * @return the latest position of the car
   */
  public String getLatestPosition(List<String> data) {
    String fieldData = data.get(0);
    inputDataValidator.validateFieldData(fieldData);
    String[] size = fieldData.split(SPACE);
    Field field = new Field(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    String carData = data.get(1);
    inputDataValidator.validateCarData(carData, field);
    String commands = data.get(2);
    inputDataValidator.validateCommands(commands);

    // Initialize car object using input data
    String[] initialPosition = carData.split(SPACE);
    int initialX = Integer.parseInt(initialPosition[0]);
    int initialY = Integer.parseInt(initialPosition[1]);
    char initialDirection = initialPosition[2].charAt(0);
    Car car = getCar(null, initialX, initialY, commands, initialDirection);

    // Execute commands to update the latest position
    for (char instruction : car.getCommands().toCharArray()) {
      executeCommand(field, car, instruction);
    }
    return car.getPosition() + SPACE + car.getDirection();
  }

  /**
   * Retrieves collision points based on the given input data.
   *
   * @param data the input data containing field size and multiple car configurations
   * @return the collision points, if any, otherwise "no collision"
   */
  public String getCollisionPoints(List<String> data) {
    inputDataValidator.validateFieldData(data.get(0));
    String[] size = data.get(0).split(SPACE);
    Field field = new Field(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    data.remove(0);

    // Extract car data and initialize cars
    List<Car> cars = getCarData(data, field);
    int maxLength = getMaxCommandLength(cars);
    int step = 0;
    boolean isCollided = false;

    // Simulate movement and check for collisions
    for (int i = 0; i < maxLength - 1; i++) {
      // Map to store positions of cars at each step
      Map<String, String> positions = new HashMap<>();
      for (Car car : cars) {
        // Execute command for each car
        if (i < car.getCommands().length()) {
          executeCommand(field, car, car.getCommands().charAt(i));
        }
        String carName = car.getName();
        String newPosition = car.getPosition();
        // Check for collisions
        if (positions.containsValue(newPosition)) {
          isCollided = true;
        }
        positions.put(carName, newPosition);
      }
      step++;
      // Return collided car details if collision occurs
      if (isCollided) {
        return getCollidedCarDetails(cars, step);
      }
    }
    // Return "no collision" if no collision occurs
    return NO_COLLISION;
  }

  private List<Car> getCarData(List<String> data, Field field) {
    List<Car> cars = new ArrayList<>();
    for (int i = 0; i < data.size(); i = i + 3) {
      String carName = data.get(i);
      inputDataValidator.validateCarName(carName);
      String carData = data.get(i + 1);
      inputDataValidator.validateCarData(carData, field);
      String commands = data.get(i + 2);
      inputDataValidator.validateCommands(commands);

      String[] initialPosition = carData.split(SPACE);
      int initialX = Integer.parseInt(initialPosition[0]);
      int initialY = Integer.parseInt(initialPosition[1]);
      char initialDirection = initialPosition[2].charAt(0);
      Car car = getCar(carName, initialX, initialY, commands, initialDirection);
      cars.add(car);
    }
    return cars;
  }

  private int getMaxCommandLength(List<Car> cars) {
    return cars.stream()
        .mapToInt(car -> car.getCommands().length())
        .max()
        .orElse(0);
  }

  private void executeCommand(Field field, Car car, char instruction) {
    switch (instruction) {
      case LEFT:
        car.turnLeft();
        break;
      case RIGHT:
        car.turnRight();
        break;
      case FORWARD:
        car.moveForward(field.getWidth(), field.getHeight());
        break;
      default:
        break;
    }
  }

  private Car getCar(String name, int initialX, int initialY, String commands,
      char initialDirection) {
    Car car = new Car();
    car.setName(name);
    car.setPositionX(initialX);
    car.setPositionY(initialY);
    car.setCommands(commands);
    car.setDirection(initialDirection);
    return car;
  }

  private String getCollidedCarDetails(List<Car> cars, int step) {
    Map<String, List<Car>> carsByPosition = cars.stream()
        .collect(Collectors.groupingBy(Car::getPosition));

    StringBuilder result = new StringBuilder();
    carsByPosition.forEach((position, carList) -> {
      if (carList.size() > 1) {
        result.append(carList.stream().map(Car::getName).collect(Collectors.joining(SPACE)))
            .append("\n")
            .append(position)
            .append("\n")
            .append(step)
            .append("\n")
            .append("\n");
      }
    });
    return result.toString().trim();
  }
}
