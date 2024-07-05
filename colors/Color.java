public class Color {
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    private int rgb;

    public Color(int color) {
        this.rgb = color;
    }

    public Color(int red, int green, int blue) {
        if (red < 0) {
            red = 0;
            System.err.println("Red value too low, now set to 0");
        } else if (red > 255) {
            red = 255;
            System.err.println("Red value too high, now set to 255");
        } else if (green < 0) {
            green = 0;
            System.err.println("Green value too low, now set to 0");
        } else if (green > 255) {
            green = 255;
            System.err.println("Green value too high, now set to 255");
        } else if (blue < 0) {
            System.err.println("Blue value too low, now set to 0");
            blue = 0;
        } else if (blue > 255) {
            System.err.println("Blue value too high, now set to 255");
            blue = 255;
        }

        this.rgb = red << 16 | green << 8 | blue;
    }

    public Color() {
        this.rgb = 0;
    }

    public Color(String HexColorRepresentation) {
        String CleanedRepresentation = HexColorRepresentation.substring(1);
        this.rgb = Integer.decode("0x" + CleanedRepresentation);
    }

    public int getRgb() {
        return this.rgb;
    }

    public int getRed() {
        return (this.rgb & 16711680) >> 16;
    }

    public int getGreen() {
        return (this.rgb & '\uff00') >> 8;
    }

    public int getBlue() {
        return this.rgb & 255;
    }

    public String getHex() {
        String HexRepresentation = Integer.toHexString(this.rgb).toUpperCase();
        if (HexRepresentation.length() >= 6) {
            return "#" + HexRepresentation;
        } else {
            StringBuilder paddedHexRepresentation = new StringBuilder();

            while (paddedHexRepresentation.length() + HexRepresentation.length() < 6) {
                paddedHexRepresentation.append('0');
            }

            paddedHexRepresentation.append(HexRepresentation);
            return "#" + paddedHexRepresentation;
        }
    }

    public String toString() {
        return this.getHex();
    }

    public Color complementaryColor() {
        int red = 255 - this.getRed();
        int green = 255 - this.getGreen();
        int blue = 255 - this.getBlue();
        return new Color(red, green, blue);
    }

    public Color mixColor(Color color) {
        int red = (color.getRed() + this.getRed()) / 2;
        int green = (color.getGreen() + this.getGreen()) / 2;
        int blue = (color.getBlue() + this.getBlue()) / 2;
        return new Color(red, green, blue);
    }

    public static void main(String[] args) {
        Color Black = new Color();
        new ColorVisualizer(Black);
        Color ComplementaryColor = Black.complementaryColor();
        new ColorVisualizer(ComplementaryColor);
        Color Orange = new Color(255, 160, 0);
        String OrangeHexRepresentation = Orange.toString();
        System.out.println(OrangeHexRepresentation);
        Color WhiteGreen = new Color("#006400");
        Color MixedGreen = WhiteGreen.mixColor(new Color(255, 255, 255));
        new ColorVisualizer(MixedGreen);
        Color Azure = new Color("#F0FFFF");
        Color AzureBlack = Azure.mixColor(new Color(0, 0, 0));
        new ColorVisualizer(AzureBlack);
        Color AzureWhite = Azure.mixColor(WHITE);
        new ColorVisualizer(AzureWhite);
        Color CadetBlue = new Color("#5F9EA0");
        Color CadetBlueCornflowerBlue = CadetBlue.mixColor(new Color("#6495ED"));
        new ColorVisualizer(CadetBlueCornflowerBlue);
        Color BlanchedAlmond = new Color("#FFEBCD");
        Color BlanchedAlmondBrown = BlanchedAlmond.mixColor(new Color("#A52A2A"));
        new ColorVisualizer(BlanchedAlmondBrown);
    }
}
