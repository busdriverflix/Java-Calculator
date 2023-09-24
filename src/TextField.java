public class TextField {
    private int x, y, w, h;
    private int textSize = 30;
    private String text;

    private String inputExpression;

    public boolean show = true;

    public TextField(int x, int y, int w, int h, String text) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
    }
    public void update() {
        if (show) {
            show();
        }else {
            showResult();
        }
    }

    public void show() {
        Main.processing.fill(255);
        Main.processing.strokeWeight(3);
        Main.processing.stroke(0);
        Main.processing.rect(x, y, w, h);
        Main.processing.fill(0);
        Main.processing.textSize(textSize);
        Main.processing.text(text, 10, 10 + textSize);
    }

    public void showResult() {
        Main.processing.fill(255);
        Main.processing.strokeWeight(3);
        Main.processing.stroke(0);
        Main.processing.rect(x, y, w, h);
        Main.processing.fill(0);
        Main.processing.textSize(textSize + 25);
        Main.processing.text(text, w - Main.processing.textWidth(text) - 40, h - 10);
        Main.processing.textSize(textSize);
        Main.processing.fill(120);
        Main.processing.text(inputExpression, 10, 10 + textSize);
    }

    public void addText(String label) {
        text += label;
    }

    public void deleteLastChar() {
        text = text.substring(0, text.length() - 1);
    }

    public void reset() {
        setText("");
        show = true;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setInputExpression(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
