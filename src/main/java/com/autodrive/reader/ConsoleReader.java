package com.autodrive.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader {

  public List<String> readCollisionData() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> data = new ArrayList<>();
    int emptyLinesCount = 0;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.trim().isEmpty()) {
        emptyLinesCount++;
        if (emptyLinesCount >= 2) {
          data.remove(data.size() - 1);
          break;
        }
      } else {
        emptyLinesCount = 0;
      }
      data.add(line);
    }
    scanner.close();
    return data;
  }

  public List<String> readCarData() {
    Scanner scanner = new Scanner(System.in);
    List<String> data = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      data.add(scanner.nextLine());
    }
    scanner.close();
    return data;
  }
}
