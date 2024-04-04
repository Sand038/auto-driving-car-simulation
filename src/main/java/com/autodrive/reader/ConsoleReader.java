package com.autodrive.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader {

  /**
   * Reads collision data from the console until two consecutive empty lines are encountered.
   *
   * @return The collision data read from the console as a list of strings.
   */
  public List<String> readCollisionData() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> data = new ArrayList<>();
    // Counter to track empty lines
    int emptyLinesCount = 0;
    // Read data until two consecutive empty lines are encountered
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.trim().isEmpty()) {
        emptyLinesCount++;
        if (emptyLinesCount >= 2) {
          // If two empty lines are encountered, stop reading
          data.remove(data.size() - 1); // Remove the last empty line
          break;
        }
      } else {
        emptyLinesCount = 0; // Reset empty lines counter
      }
      data.add(line);
    }
    scanner.close();
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
    // Read 3 lines of input for a car
    for (int i = 0; i < 3; i++) {
      data.add(scanner.nextLine());
    }
    scanner.close();
    return data;
  }
}
