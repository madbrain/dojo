package com.github.madbrain.dojo.properties.test;

import com.github.madbrain.dojo.properties.Point;
import com.github.madbrain.dojo.properties.Rectangle;
import com.github.madbrain.dojo.properties.RectanglePacker;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class RectanglePackerTest {

    public void testPack(List<Rectangle> rectangles) {
        RectanglePacker packer = new RectanglePacker();
        List<Point> positions = packer.pack(rectangles);

        // Assert final area is >= sum of rectangle area
        // Assert every rectangle share a border with an another
        // Assert that rectangles are packed in one connected component
        // etc.
    }
}
