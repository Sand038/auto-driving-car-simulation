# auto-driving-car-simulation

## Instructions to run

1. Navigate to the project directory and run the following command to build the project (Maven should be pre-installed):

    ```
    mvn clean install
    ```

2. Run the following commands to execute parts 1 and 2 of the task:

   - For part 1:

        ```
        java -cp target\auto-driving-car-simulation-1.0-SNAPSHOT.jar com.autodrive.executor.CarSimulationExecutor
        ```

    - For part 2:

        ```
        java -cp target\auto-driving-car-simulation-1.0-SNAPSHOT.jar com.autodrive.executor.CarSimulationCollisionExecutor
        ```

3. Please follow the provided instructions and adhere to the example format when entering data. If any section contains invalid data, you will be prompted to re-enter that specific section.

4. To run tests, execute the following command:

    ```
    mvn test
    ```
5. Please locate the attached screenshots at the following path:

    ```
    Screenshots
    ```


## Auto Driving Car Simulation Design and Assumptions

### 1. Introduction:

The Auto Driving Car Simulation is designed to simulate the movement of cars on a field based on input commands. This document outlines the design of the system and the assumptions made during development.

### 2. Design:

#### 2.1 Classes:

- Car: Represents a car in the simulation. Contains properties such as name, position, direction, and commands. Implements methods for moving the car, turning left or right, and getting the current position.

- Field: Represents the simulation field with width and height.

- Navigation: Provides basic navigation functionalities for turning left, turning right, and getting the direction of the car.

- InputDataValidator: Validates input data for field size, car configuration, and commands.

- ConsoleReader: Reads input data from the console for car configuration and collision data.

- CarService: Orchestrates the simulation process, including validating input data, simulating car movements, detecting collisions, and providing the latest position.

#### 2.2 Dependencies:

- JUnit Jupiter: Used for unit testing.

### 3. Assumptions:

- Logging Absence: Logging is intentionally excluded to avoid cluttering the console output and ensure clear presentation of simulation results.

- Input Data Format: Input data must adhere to a specific format, failing which validation exceptions are raised to ensure only well structured input is processed.

- Two-Dimensional Grid Field: The simulation assumes a two-dimensional grid-based field. If the input doesn't match the expected format, a validation exception is thrown.

- Termination Conditions (Part 2): The simulation terminates upon collision between cars or completion of all car movements.

- Input Data (Part 2): Input data should be in the specified format, and the program expects it to collect details for multiple cars. Execution begins once you don't want to continue entering more car details.
