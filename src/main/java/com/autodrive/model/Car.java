package com.autodrive.model;

import static com.autodrive.model.Constants.SPACE;
import static com.autodrive.model.Directions.EAST;
import static com.autodrive.model.Directions.NORTH;
import static com.autodrive.model.Directions.SOUTH;
import static com.autodrive.model.Directions.WEST;

public class Car extends Navigation {

  private String name;
  private int positionX;
  private int positionY;
  private String commands;

  public String getPosition() {
    return positionX + SPACE + positionY;
  }

  public void moveForward(int width, int height) {
    switch (direction) {
      case NORTH:
        if (positionY < height) {
          positionY++;
        }
        break;
      case EAST:
        if (positionX < width) {
          positionX++;
        }
        break;
      case SOUTH:
        if (positionY > 0) {
          positionY--;
        }
        break;
      case WEST:
        if (positionX > 0) {
          positionX--;
        }
        break;
      default:
        break;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  public String getCommands() {
    return commands;
  }

  public void setCommands(String commands) {
    this.commands = commands;
  }
}

