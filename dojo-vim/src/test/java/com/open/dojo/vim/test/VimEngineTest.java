package com.open.dojo.vim.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.open.dojo.vim.DisplayBuffer;
import com.open.dojo.vim.KeyboardEvent;
import com.open.dojo.vim.VimEngine;

public class VimEngineTest {

    private DisplayBuffer buffer;
    private VimEngine engine;

    @Before
    public void setup() {
        buffer = new DisplayBuffer(10, 10);
        engine = new VimEngine(buffer);
    }

    @Test
    public void testEnterInsertModeOnI() {

        keys("i");

        Assert.assertEquals(0, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("INSERT", buffer.getLine(9));
    }

    @Test
    public void testLeaveInsertMode() {
        keys("i Escape");

        Assert.assertEquals(0, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testEnterTestInInsertMode() {
        keys("i h e l l o Escape");

        Assert.assertEquals(5, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testMoveLeft() {
        keys("i h e l l o Escape");
        keys("Left Left");

        Assert.assertEquals(3, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testDeleteOneCharWithX() {
        keys("i h e l l o Escape");
        keys("Left Left x");

        Assert.assertEquals(3, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("helo", buffer.getLine(0));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testEnterInsertModeAtEndWithMajA() {
        keys("i h e l l o Escape");
        keys("Left Left A");

        Assert.assertEquals(5, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("INSERT", buffer.getLine(9));
    }

    @Test
    public void testUndoWithU() {
        keys("i h e l l Escape");
        keys("A o Escape u");

        Assert.assertEquals(4, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hell", buffer.getLine(0));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testEnterNewLine() {
        keys("i h e l l o Enter");
        keys("l e Space m o n d e Escape");

        Assert.assertEquals(8, buffer.getCursorX());
        Assert.assertEquals(1, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("le monde", buffer.getLine(1));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testDeleteOneLineWithDD() {
        keys("i h e l l o Enter");
        keys("l e Space m o n d e Escape");
        keys("d d");

        Assert.assertEquals(0, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("", buffer.getLine(1));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testEnterSearch() {
        keys("i h e l l o Enter");
        keys("l e Space m o n d e Escape");
        keys("/ l o");

        Assert.assertEquals(3, buffer.getCursorX());
        Assert.assertEquals(9, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("le monde", buffer.getLine(1));
        Assert.assertEquals("/lo", buffer.getLine(9));
    }

    @Test
    public void testDoSearch() {
        keys("i h e l l o Enter");
        keys("l e Space m o n d e Escape");
        keys("/ l o Enter");

        Assert.assertEquals(3, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("le monde", buffer.getLine(1));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testSearchNext() {
        keys("i h e l l o Enter");
        keys("l e Space m e l o n Escape");
        keys("/ l o Enter");
        keys("n");

        Assert.assertEquals(5, buffer.getCursorX());
        Assert.assertEquals(1, buffer.getCursorY());
        Assert.assertEquals("hello", buffer.getLine(0));
        Assert.assertEquals("le melon", buffer.getLine(1));
        Assert.assertEquals("", buffer.getLine(9));
    }

    @Test
    public void testInsertInMiddle() {
        keys("i h e l l o Escape");
        keys("Left Left Left Left x");
        keys("i a Escape");

        Assert.assertEquals(2, buffer.getCursorX());
        Assert.assertEquals(0, buffer.getCursorY());
        Assert.assertEquals("hallo", buffer.getLine(0));
        Assert.assertEquals("", buffer.getLine(9));
    }

    private void keys(String keys) {
        Arrays.stream(keys.split(" "))
                .map(key -> new KeyboardEvent(key))
                .forEachOrdered(event -> engine.handle(event));
    }

}
