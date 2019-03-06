package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.myapplication.Render;

import java.lang.Math;



public class RenderTest {

    public RenderTest(){
        this.TestClass = new Render();
    }

    @Test
    public void testSimpleMoveWithoutMu(){
        TestClass.setDegrees(30,-30);
        TestClass.setMuk(0);
        TestClass.setMus(0);
        TestClass.render(1);
        assertEquals(2.5, TestClass.getX(),0.01);
        assertEquals(-2.5, TestClass.getY(),0.01);
    }

    @Test
    public void testSimpleMoveWithoutMu2(){
        TestClass.setDegrees(30,0);
        TestClass.setMuk(0);
        TestClass.setMus(0);
        TestClass.render(2);
        // now v1 should be 10 and x is 5
        TestClass.setDegrees(-90,0);
        TestClass.render(0.5);
        // now v1 is 5 and x is 13.75
        assertEquals(13.75, TestClass.getX(),0.01);
        TestClass.render(0.5);
        //now v1 is 0 and x is 15
        assertEquals(15, TestClass.getX(), 0.01);
        TestClass.render(1);
        // now v1 is -10 and x is 10
        assertEquals(10.0,TestClass.getX(),0.01);
    }

    @Test
    public void testSimpleMoveWithMus(){
        TestClass.setDegrees(30,0);
        TestClass.setMuk(0);
        TestClass.setMus(1); // should not move
        TestClass.render(1);
        assertEquals(0, TestClass.getX(),0.01);
        assertEquals(0, TestClass.getY(),0.01);
        TestClass.setDegrees(45,0); // should not move again
        TestClass.render(1);
        assertEquals(0, TestClass.getX(),0.01);
        assertEquals(0, TestClass.getY(),0.01);
        TestClass.setDegrees(46,0); // now it moves
        TestClass.render(1);
        assertNotEquals(0.0,TestClass.getX());
    }

    @Test
    public void testSimpleMoveWithMuk(){
        TestClass.setDegrees(-90,0);
        TestClass.setMuk(0);
        TestClass.setMus(0); // should not move
        TestClass.render(0.75*Math.pow(2,0.5));
        double prevx = TestClass.getX();
//        System.out.println(prevx);
        TestClass.setDegrees(45,0);
        TestClass.setMuk(0.5);
        TestClass.render(1); // now v is 0
        double delta1 = TestClass.getX() - prevx;
        prevx = TestClass.getX();
//        System.out.println(prevx);
        TestClass.render(1);
        double delta2 = TestClass.getX() - prevx;
//        System.out.println(TestClass.getX());
        assertTrue(Math.abs(delta1)>Math.abs(delta2));
    }


    @Test
    public void testSimpleMoveWithMu(){
        TestClass.setDegrees(-90,0);
        TestClass.setMuk(0);
        TestClass.setMus(1);
        TestClass.render(0.75*Math.pow(2,0.5));
        double prevx = TestClass.getX();
//        System.out.println(prevx);
        TestClass.setDegrees(45,0);
        TestClass.setMuk(0.5);
        TestClass.render(1); // now v is 0 and because of Mus cannot move
        double delta1 = TestClass.getX() - prevx;
        prevx = TestClass.getX();
//        System.out.println(prevx);
        TestClass.render(5);
        double delta2 = TestClass.getX() - prevx;
//        System.out.println(TestClass.getX());
        assertEquals(0.0, delta2, 0.01);
    }

    private Render TestClass;
}
