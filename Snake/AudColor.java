// =================================
// DO NOT MODIFY THIS CLASS
// =================================

import java.beans.ConstructorProperties;

public class AudColor {

    /**
     * The color white.  In the default sRGB space.
     */
    public final static AudColor white = new AudColor(255, 255, 255);

    /**
     * The color white.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor WHITE = white;

    /**
     * The color light gray.  In the default sRGB space.
     */
    public final static AudColor lightGray = new AudColor(192, 192, 192);

    /**
     * The color light gray.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor LIGHT_GRAY = lightGray;

    /**
     * The color gray.  In the default sRGB space.
     */
    public final static AudColor gray = new AudColor(128, 128, 128);

    /**
     * The color gray.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor GRAY = gray;

    /**
     * The color dark gray.  In the default sRGB space.
     */
    public final static AudColor darkGray = new AudColor(64, 64, 64);

    /**
     * The color dark gray.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor DARK_GRAY = darkGray;

    /**
     * The color black.  In the default sRGB space.
     */
    public final static AudColor black = new AudColor(0, 0, 0);

    /**
     * The color black.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor BLACK = black;

    /**
     * The color red.  In the default sRGB space.
     */
    public final static AudColor red = new AudColor(255, 0, 0);

    /**
     * The color red.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor RED = red;

    /**
     * The color pink.  In the default sRGB space.
     */
    public final static AudColor pink = new AudColor(255, 175, 175);

    /**
     * The color pink.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor PINK = pink;

    /**
     * The color orange.  In the default sRGB space.
     */
    public final static AudColor orange = new AudColor(255, 200, 0);

    /**
     * The color orange.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor ORANGE = orange;

    /**
     * The color yellow.  In the default sRGB space.
     */
    public final static AudColor yellow = new AudColor(255, 255, 0);

    /**
     * The color yellow.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor YELLOW = yellow;

    /**
     * The color green.  In the default sRGB space.
     */
    public final static AudColor green = new AudColor(0, 255, 0);

    /**
     * The color green.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor GREEN = green;

    /**
     * The color magenta.  In the default sRGB space.
     */
    public final static AudColor magenta = new AudColor(255, 0, 255);

    /**
     * The color magenta.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor MAGENTA = magenta;

    /**
     * The color cyan.  In the default sRGB space.
     */
    public final static AudColor cyan = new AudColor(0, 255, 255);

    /**
     * The color cyan.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor CYAN = cyan;

    /**
     * The color blue.  In the default sRGB space.
     */
    public final static AudColor blue = new AudColor(0, 0, 255);

    /**
     * The color blue.  In the default sRGB space.
     *
     * @since 1.4
     */
    public final static AudColor BLUE = blue;

    /**
     * The color value.
     *
     * @serial
     * @see #getRGB
     */
    int value;

    /**
     * The color value in the default sRGB <code>ColorSpace</code> as
     * <code>float</code> components (no alpha).
     * If <code>null</code> after object construction, this must be an
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color value.
     *
     * @serial
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    private float frgbvalue[] = null;

    /**
     * The color value in the native <code>ColorSpace</code> as
     * <code>float</code> components (no alpha).
     * If <code>null</code> after object construction, this must be an
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color value.
     *
     * @serial
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    private float fvalue[] = null;

    /**
     * The alpha value as a <code>float</code> component.
     * If <code>frgbvalue</code> is <code>null</code>, this is not valid
     * data, so compute from the <code>int</code> color value.
     *
     * @serial
     * @see #getRGBComponents
     * @see #getComponents
     */
    private float falpha = 0.0f;

    /**
     * Checks the color integer components supplied for validity.
     * Throws an {@link IllegalArgumentException} if the value is out of
     * range.
     *
     * @param r the Red component
     * @param g the Green component
     * @param b the Blue component
     **/
    private static void testColorValueRange(int r, int g, int b, int a) {
        boolean rangeError = false;
        String badComponentString = "";

        if (a < 0 || a > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if (r < 0 || r > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if (g < 0 || g > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if (b < 0 || b > 255) {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if (rangeError == true) {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                    + badComponentString);
        }
    }

    /**
     * Checks the color <code>float</code> components supplied for
     * validity.
     * Throws an <code>IllegalArgumentException</code> if the value is out
     * of range.
     *
     * @param r the Red component
     * @param g the Green component
     * @param b the Blue component
     **/
    private static void testColorValueRange(float r, float g, float b, float a) {
        boolean rangeError = false;
        String badComponentString = "";
        if (a < 0.0 || a > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if (r < 0.0 || r > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if (g < 0.0 || g > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if (b < 0.0 || b > 1.0) {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if (rangeError == true) {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                    + badComponentString);
        }
    }

    /**
     * Creates an opaque sRGB color with the specified red, green,
     * and blue values in the range (0 - 255).
     * The actual color used in rendering depends
     * on finding the best match given the color space
     * available for a given output device.
     * Alpha is defaulted to 255.
     *
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *                                  or <code>b</code> are outside of the range
     *                                  0 to 255, inclusive
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public AudColor(int r, int g, int b) {
        this(r, g, b, 255);
    }

    /**
     * Creates an sRGB color with the specified red, green, blue, and alpha
     * values in the range (0 - 255).
     *
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @param a the alpha component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>,
     *                                  <code>b</code> or <code>a</code> are outside of the range
     *                                  0 to 255, inclusive
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    @ConstructorProperties({"red", "green", "blue", "alpha"})
    public AudColor(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                ((b & 0xFF) << 0);
        testColorValueRange(r, g, b, a);
    }

    /**
     * Creates an opaque sRGB color with the specified combined RGB value
     * consisting of the red component in bits 16-23, the green component
     * in bits 8-15, and the blue component in bits 0-7.  The actual color
     * used in rendering depends on finding the best match given the
     * color space available for a particular output device.  Alpha is
     * defaulted to 255.
     *
     * @param rgb the combined RGB components
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public AudColor(int rgb) {
        value = 0xff000000 | rgb;
    }

    /**
     * Creates an sRGB color with the specified combined RGBA value consisting
     * of the alpha component in bits 24-31, the red component in bits 16-23,
     * the green component in bits 8-15, and the blue component in bits 0-7.
     * If the <code>hasalpha</code> argument is <code>false</code>, alpha
     * is defaulted to 255.
     *
     * @param rgba     the combined RGBA components
     * @param hasalpha <code>true</code> if the alpha bits are valid;
     *                 <code>false</code> otherwise
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public AudColor(int rgba, boolean hasalpha) {
        if (hasalpha) {
            value = rgba;
        } else {
            value = 0xff000000 | rgba;
        }
    }

    /**
     * Creates an opaque sRGB color with the specified red, green, and blue
     * values in the range (0.0 - 1.0).  Alpha is defaulted to 1.0.  The
     * actual color used in rendering depends on finding the best
     * match given the color space available for a particular output
     * device.
     *
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *                                  or <code>b</code> are outside of the range
     *                                  0.0 to 1.0, inclusive
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public AudColor(float r, float g, float b) {
        this((int) (r * 255 + 0.5), (int) (g * 255 + 0.5), (int) (b * 255 + 0.5));
        testColorValueRange(r, g, b, 1.0f);
        frgbvalue = new float[3];
        frgbvalue[0] = r;
        frgbvalue[1] = g;
        frgbvalue[2] = b;
        falpha = 1.0f;
        fvalue = frgbvalue;
    }

    /**
     * Creates an sRGB color with the specified red, green, blue, and
     * alpha values in the range (0.0 - 1.0).  The actual color
     * used in rendering depends on finding the best match given the
     * color space available for a particular output device.
     *
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @param a the alpha component
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *                                  <code>b</code> or <code>a</code> are outside of the range
     *                                  0.0 to 1.0, inclusive
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public AudColor(float r, float g, float b, float a) {
        this((int) (r * 255 + 0.5), (int) (g * 255 + 0.5), (int) (b * 255 + 0.5), (int) (a * 255 + 0.5));
        frgbvalue = new float[3];
        frgbvalue[0] = r;
        frgbvalue[1] = g;
        frgbvalue[2] = b;
        falpha = a;
        fvalue = frgbvalue;
    }

    /**
     * Returns the red component in the range 0-255 in the default sRGB
     * space.
     *
     * @return the red component.
     * @see #getRGB
     */
    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }

    /**
     * Returns the green component in the range 0-255 in the default sRGB
     * space.
     *
     * @return the green component.
     * @see #getRGB
     */
    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }

    /**
     * Returns the blue component in the range 0-255 in the default sRGB
     * space.
     *
     * @return the blue component.
     * @see #getRGB
     */
    public int getBlue() {
        return (getRGB() >> 0) & 0xFF;
    }

    /**
     * Returns the alpha component in the range 0-255.
     *
     * @return the alpha component.
     * @see #getRGB
     */
    public int getAlpha() {
        return (getRGB() >> 24) & 0xff;
    }

    /**
     * Returns the RGB value representing the color in the default sRGB
     * {@link ColorModel}.
     * (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are
     * blue).
     *
     * @return the RGB value of the color in the default sRGB
     * <code>ColorModel</code>.
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @since JDK1.0
     */
    public int getRGB() {
        return value;
    }

    /**
     * Computes the hash code for this <code>Color</code>.
     *
     * @return a hash code value for this object.
     * @since JDK1.0
     */
    public int hashCode() {
        return value;
    }

    /**
     * Determines whether another object is equal to this
     * <code>Color</code>.
     * <p>
     * The result is <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>Color</code> object that has the same
     * red, green, blue, and alpha values as this object.
     *
     * @param obj the object to test for equality with this
     *            <code>Color</code>
     * @return <code>true</code> if the objects are the same;
     * <code>false</code> otherwise.
     * @since JDK1.0
     */
    public boolean equals(Object obj) {
        return obj instanceof AudColor && ((AudColor) obj).getRGB() == this.getRGB();
    }

    /**
     * Returns a string representation of this <code>Color</code>. This
     * method is intended to be used only for debugging purposes.  The
     * content and format of the returned string might vary between
     * implementations. The returned string might be empty but cannot
     * be <code>null</code>.
     *
     * @return a string representation of this <code>Color</code>.
     */
    public String toString() {
        return getClass().getName() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + "]";
    }
}
