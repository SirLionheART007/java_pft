package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;
  public double x1;
  public double y1;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;

  }


  public double Distance2(double x, double y) {
    double dx = this.x - x;
    double dy = this.y - y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  public double Distance2(Point p) {
    return Distance2(p.x, p.y);
  }

}



