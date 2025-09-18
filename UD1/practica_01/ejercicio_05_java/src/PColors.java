import java.awt.Color;

public enum PColors {
    TURQUOISE(new Color(76, 235, 169)),
    RED(new Color(255, 0, 0)),
    ORANGE(new Color(255, 109, 0)),
    PINK(new Color(240, 0, 255)),
    GREEN(new Color(0, 128, 0)),
    BLUE(new Color(1, 80, 159)),
    BEIGE(new Color(202, 158, 97));

    public final Color color;

    private PColors(Color color) {
        this.color = color;
    }
}
