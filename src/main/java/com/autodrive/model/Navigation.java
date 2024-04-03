package com.autodrive.model;

import static com.autodrive.model.Directions.EAST;
import static com.autodrive.model.Directions.NORTH;
import static com.autodrive.model.Directions.SOUTH;
import static com.autodrive.model.Directions.WEST;

import java.util.HashMap;
import java.util.Map;

public class Navigation {

  protected char direction;

  private static final Map<Character, Character> LEFT_TURN_MAPPING = new HashMap<>();
  private static final Map<Character, Character> RIGHT_TURN_MAPPING = new HashMap<>();

  static {
    LEFT_TURN_MAPPING.put(NORTH, WEST);
    LEFT_TURN_MAPPING.put(WEST, SOUTH);
    LEFT_TURN_MAPPING.put(SOUTH, EAST);
    LEFT_TURN_MAPPING.put(EAST, NORTH);

    RIGHT_TURN_MAPPING.put(NORTH, EAST);
    RIGHT_TURN_MAPPING.put(EAST, SOUTH);
    RIGHT_TURN_MAPPING.put(SOUTH, WEST);
    RIGHT_TURN_MAPPING.put(WEST, NORTH);
  }

  public void turnLeft() {
    direction = LEFT_TURN_MAPPING.get(direction);
  }

  public void turnRight() {
    direction = RIGHT_TURN_MAPPING.get(direction);
  }

  public char getDirection() {
    return direction;
  }

  public void setDirection(char direction) {
    this.direction = direction;
  }
}
