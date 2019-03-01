package com.open.dojo.vim.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.open.dojo.vim.DisplayBuffer;
import com.open.dojo.vim.KeyHandler;
import com.open.dojo.vim.KeyboardEvent;

public class DisplayView extends JPanel {

    private static final long serialVersionUID = -3918187694641923555L;

    private DisplayBuffer buffer;
    private Font font;
    private FontMetrics fm;
    private boolean cursorBlink;

    public DisplayView(final KeyHandler keyHandler, DisplayBuffer buffer) {
        this.buffer = buffer;
        setOpaque(true);
        setBackground(Color.WHITE);
        font = new Font(Font.MONOSPACED, 0, 15);
        setFont(font);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_SHIFT
                        && e.getKeyCode() != KeyEvent.VK_CONTROL
                        && e.getKeyCode() != KeyEvent.VK_ALT) {
                    keyHandler.handle(new KeyboardEvent(e));
                    repaint();
                }
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        cursorBlink = !cursorBlink;
                        Thread.sleep(500);
                        repaint();
                    } catch (InterruptedException e) {
                    }
                }
            }

        }, "cursor-blink").start();
    }

    private int getAdvance() {
        return getFontMetrics().getMaxAdvance();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                buffer.getWidth() * getAdvance(),
                buffer.getHeight() * getFontMetrics().getHeight());
    }

    private FontMetrics getFontMetrics() {
        if (fm == null) {
            fm = getGraphics().getFontMetrics(font);
        }
        return fm;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int y = 0; y < buffer.getHeight(); ++y) {
            for (int x = 0; x < buffer.getWidth(); ++x) {
                char c = buffer.get(x, y);
                if (c != 0) {
                    g.drawString(String.valueOf(c),
                            x * getAdvance(),
                            y * getFontMetrics().getHeight() + getFontMetrics().getAscent());
                }
            }
        }
        if (buffer.isCursorVisible()) {
            int x = buffer.getCursorX();
            int y = buffer.getCursorY();
            g.setXORMode(cursorBlink ? Color.WHITE : Color.BLACK);
            g.fillRect(
                    x * getAdvance(), y * getFontMetrics().getHeight(),
                    getAdvance(), getFontMetrics().getHeight());
        }
    }
}
