package com.open.dojo.vim.gui;

import javax.swing.JFrame;

import com.open.dojo.vim.DisplayBuffer;
import com.open.dojo.vim.VimEngine;

public class VimEditor {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DisplayBuffer buffer = new DisplayBuffer(80, 25);
        VimEngine engine = new VimEngine(buffer);
        frame.getContentPane().add(new DisplayView(engine, buffer));
        frame.pack();
        frame.setVisible(true);
    }
}
