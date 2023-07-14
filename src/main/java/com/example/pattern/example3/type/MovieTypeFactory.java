package com.example.pattern.example3.type;


public class MovieTypeFactory {
  public static MovieType createMovieType(String type) {
    switch (type) {
      case "REGULAR":
        return MovieType.REGULAR;
      case "CHILDREN":
        return MovieType.CHILDREN;
      case "NEW_RELEASE":
        return MovieType.NEW_RELEASE;
      default:
        throw new IllegalArgumentException("Invalid movie type: " + type);
    }
  }

  public static double calculateAmount(MovieType movieType, int daysRented) {
    switch (movieType) {
      case REGULAR:
        double amount = 2;
        if (daysRented > 2) {
          amount += (daysRented - 2) * 1.5;
        }
        return amount;
      case CHILDREN:
        amount = 1.5;
        if (daysRented > 3) {
          amount += (daysRented - 3) * 1.5;
        }
        return amount;
      case NEW_RELEASE:
        return daysRented * 3;
      default:
        throw new IllegalArgumentException("Invalid movie type: " + movieType);
    }
  }

  public static int calculateBonus(MovieType movieType, int daysRented) {
    return movieType == MovieType.NEW_RELEASE && daysRented > 1 ? 2 : 1;
  }
}
