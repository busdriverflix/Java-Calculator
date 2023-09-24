import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.awt.Font.createFont;

public class Button {
    private final int x, y, w, h;
    private final Color color;
    private String label = "";
    private final int r, g, b;

    public Button(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
    }

    public void update() {
        int decreaseOnClickAndHover = 10;
        Main.processing.strokeWeight(1);
        Main.processing.stroke(200);
        Main.processing.fill(r - decreaseOnClickAndHover, g - decreaseOnClickAndHover, b - decreaseOnClickAndHover); // Hover + Click = brightness - 30
        Main.processing.rect(x, y, w, h, 10);
        Main.processing.fill(200);
        Main.processing.text(label, x + (w - Main.processing.textWidth(label)) / 2, y + ((float) h / 2) + 5);
        onSimpleTokenClicked();
        onComplexTokenClicked();
    }

    public void show() {
        Main.processing.textSize(30);
        Main.processing.strokeWeight(1);
        int increaseOnHover = 10;
        if (isMouseHovering()) {
            Main.processing.stroke(200);
            Main.processing.fill(r + increaseOnHover, g + increaseOnHover, b + increaseOnHover); // Hover = brightness + 30
            Main.processing.rect(x, y, w, h, 10);
            Main.processing.fill(Util.secondary.getRed(), Util.secondary.getGreen(), Util.secondary.getBlue());
            Main.processing.text(label, x + (w - Main.processing.textWidth(label)) / 2, y + ((float) h / 2) + 5);
        } else {
            Main.processing.noStroke();
            Main.processing.fill(color.getRGB());
            Main.processing.rect(x, y, w, h, 10);
            Main.processing.fill(255);
            Main.processing.text(label, x + (w - Main.processing.textWidth(label)) / 2, (float) y + (float) (h + 24) / 2);
        }
    }

    public boolean isMouseHovering() {
        if (Main.processing.mouseX >= this.x && Main.processing.mouseX <= this.x + this.w) {
            return Main.processing.mouseY >= this.y && Main.processing.mouseY <= this.y + this.h;
        }
        return false;
    }

    public void onSimpleTokenClicked() {
        ArrayList<String> simpleLabels = new ArrayList<>(Arrays.asList(Util.simpleButtonLabels));
        if (simpleLabels.contains(label)) {
            Main.textField.addText(this.label);
        }
    }

    public void onComplexTokenClicked() {
        ArrayList<String> complexLabels = new ArrayList<>(Arrays.asList(Util.complexButtonLabels));
        if (complexLabels.contains(label)) {
            switch (label) {
                case "=":
                    Main.textField.setInputExpression(Main.textField.getText());
                    Main.textField.setShow(false);
                    Lexer lexer = new Lexer(Main.textField.getText());
                    ArrayList<Token> tokens = lexer.Tokenize();
                    ArrayList<Token> tokensRPN = Parser.toRPN(tokens);
                    Token result = Parser.Parse(tokensRPN);
                    Main.textField.setText(result.getLexeme());
                    break;
                case "C" :
                    Main.textField.reset();
                    break;
                case "⇚":
                    Main.textField.deleteLastChar();
                    break;
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
