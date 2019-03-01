package com.open.dojo.vim;

public class VimEngine implements KeyHandler {

    private DisplayBuffer buffer;

    public VimEngine(DisplayBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void handle(KeyboardEvent event) {
        throw new RuntimeException(event.toString());
    }

}
