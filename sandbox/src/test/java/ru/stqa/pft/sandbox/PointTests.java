package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test

  public void TestDistance() {

    Point p1 = new Point(4, 2);
    Point p2 = new Point(5, 2);
    Assert.assertEquals(p1.Distance2(p2), 1);
  }


  @Test

  public void TestDistance2() {

    Point p1 = new Point(4, 2);
    Point p2 = new Point(5, 2);

    assert p1.Distance2(p2) == 1;

  }

  @Test

  public void TestDistance3() {

    Point p1 = new Point(4, 2);
    Point p2 = new Point(5, 2);

    if (p1.Distance2(p2) == 1) {
      System.out.println("Correct");
    } else {
      throw new IllegalArgumentException("Incorrect");
    }

  }
}
