import processing.core.PApplet;
import processing.core.PFont;

import java.awt.*;
import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet processing;
    public static boolean darkMode = false;
    public static ArrayList<Button> buttons = new ArrayList<>(9);
    public static TextField textField = new TextField(0, 0, 450, 150, "");

    public void setup() {
        processing = this;
        PFont font = this.createFont("Congress", 24);
        this.textFont(font);
        background(50);
    }

    public void settings() {
        size(420, 655);
    }

    public static void main(String[] args) {
        PApplet.main("Main", args);
        buttons = Util.initializeButtons();
    }

    public void draw() {
        Color bc = Util.backgroundColor;
        background(bc.getRed(), bc.getGreen(), bc.getBlue());
        for (Button button : buttons) {
            button.show();
        }
        textField.update();
    }

    public void mousePressed() {
        for (Button button : buttons) {
            if (button.isMouseHovering()) {
                button.update();
            }
        }
    }

    public void keyPressed(){
        if (keyCode == SHIFT)
            return;

        if (keyCode == BACKSPACE) {
            textField.deleteLastChar();
        } else {
            textField.addText(String.valueOf(key));
        }
    }

}