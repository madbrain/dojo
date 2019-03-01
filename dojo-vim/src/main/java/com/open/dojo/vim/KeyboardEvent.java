package com.open.dojo.vim;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardEvent {

    private static Map<String, Integer> keyCodeMap = new HashMap<>();

    static {
        for (int i = 'A'; i <= 'Z'; ++i) {
            keyCodeMap.put(String.valueOf((char) i), i);
        }
        keyCodeMap.put("Escape", KeyEvent.VK_ESCAPE);
        keyCodeMap.put("Enter", KeyEvent.VK_ENTER);
        keyCodeMap.put("Space", KeyEvent.VK_SPACE);
        keyCodeMap.put("Left", KeyEvent.VK_LEFT);
        keyCodeMap.put("Right", KeyEvent.VK_RIGHT);
        keyCodeMap.put("Up", KeyEvent.VK_UP);
        keyCodeMap.put("Down", KeyEvent.VK_DOWN);

        keyCodeMap.put("/", KeyEvent.VK_SLASH);
        keyCodeMap.put(":", KeyEvent.VK_COLON);
    }

    private int keyCode;
    private char keyChar;
    private boolean hasControl;

    public KeyboardEvent(KeyEvent e) {
        this.keyChar = e.getKeyChar();
        this.keyCode = e.getKeyCode();
        this.hasControl = e.isControlDown();
    }

    public KeyboardEvent(int keyCode, boolean hasControl) {
        this.keyCode = keyCode;
        this.hasControl = hasControl;
    }

    public char getKeyChar() {
        return keyChar;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public KeyboardEvent(String keyExpr) {
        String[] elements = keyExpr.split("\\+");
        int index = 0;
        while (index < elements.length - 1) {
            if (elements[index].equals("CTRL")) {
                hasControl = true;
            }
            ++index;
        }
        keyChar = elements[index].charAt(0);
        keyCode = keyCodeMap.get(capitalize(elements[index]));
    }

    private static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    @Override
    public String toString() {
        return (keyCode > 32 ? String.valueOf(keyChar) : KeyEvent.getKeyText(keyCode)) + " (code: " + keyCode
                + ", ctrl: " + hasControl + ")";
    }

}
