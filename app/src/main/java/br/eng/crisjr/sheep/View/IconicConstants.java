package br.eng.crisjr.sheep.View;

import android.graphics.Typeface;

/**
 * Class to deal with strings for symbols in the Open Iconic typeface.
 */
public class IconicConstants
{
    final public static String ADD = "\uE0AA";
    final public static String DELETE = "\uE0DB";
    final public static String OK = "\uE033";
    final public static String ERASE = "\uE050";
    final public static String MINUS = "\uE09D";
    final public static String LEFT = "\uE035";
    final public static String RIGHT = "\uE036";
    private static Typeface font = null;

    public static Typeface setTypeface(Typeface typeface)
    {
        font = typeface;
        return font;
    }

    public static Typeface getTypeface()
    {
        return font;
    }
}
