import java.awt.*;
import java.util.ArrayList;

public class Util {
    public static String[] allButtonLabels = {
            "mod", "π", "C", "⇚",
            "xʸ", "x²", "√x", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "–",
            "1", "2", "3", "+",
            "+/-", "0", ",", "="
    };
    public static String[] simpleButtonLabels = {
            "mod", "π", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "–",
            "1", "2", "3", "+",
            "+/-", "0", ","
    };
    public static String[] complexButtonLabels = {
            "C", "⇚", "xʸ", "x²", "√x", "="
    };


    public static Color primary = new Color(70, 70, 70);
    public static Color secondary = new Color(220, 220, 220);
    public static Color backgroundColor = new Color(50, 50, 50);

    public static ArrayList<Button> initializeButtons() {
        int rows = 6;
        int cols = 4;
        final int margin = 2;
        final int buttonWidth = (420 / cols) - margin;
        final int buttonHeight = (505 / rows) - margin;
        final int yOffset = 150 + margin;
        ArrayList<Button> buttons = new ArrayList<>(9);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * (buttonWidth + margin);
                int y = i * (buttonHeight + margin) + yOffset;
                buttons.add(new Button(x, y, buttonWidth, buttonHeight, primary));
            }
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setLabel(allButtonLabels[i]);
        }

        return buttons;
    }



    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
