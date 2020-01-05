package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(4, 2);
    Point p2 = new Point(4, 2);

    System.out.println("x = " + p1.x + " y = " + p1.y);
    System.out.println("x = " + p2.x + " y = " + p2.y);

    System.out.println(distance(p1, p2));


    System.out.println(p1.Distance2(p2)); 

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
  }


}


