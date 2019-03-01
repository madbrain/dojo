package com.open.dojo.vim;

public class DisplayBuffer {

    private char[][] content;
    private int width;
    private int height;
    private int cursorX;
    private int cursorY;

    public DisplayBuffer(int width, int height) {
        this.content = new char[height][width];
        this.width = width;
        this.height = height;
    }

    public void setContent(int line, int column, String lineContent) {
        for (int i = 0; i < lineContent.length(); ++i) {
            int pos = column + i;
            if (pos > width) {
                break;
            }
            content[line][pos] = lineContent.charAt(i);
        }
    }

    public void setCursor(int line, int column) {
        cursorX = column;
        cursorY = line;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char get(int col, int line) {
        return content[line][col];
    }

    public boolean isCursorVisible() {
        return true;
    }

    public int getCursorX() {
        return cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public String getLine(int i) {
        return new String(content[i]).trim();
    }
}
