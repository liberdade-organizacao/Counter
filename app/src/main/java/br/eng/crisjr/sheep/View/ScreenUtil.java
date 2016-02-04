package br.eng.crisjr.sheep.View;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Class to provide some tools for dealing with screen.
 */
public class ScreenUtil
{
    private Context context = null;

    public ScreenUtil() { }

    public ScreenUtil(Context context)
    {
        this();
        this.context = context;
    }

    /* DP/Pixel tools */
    private static float getDensity(Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    /**
     * Converts density to pixel numbers, depending on the application context.
     * @param dip, the screen density.
     * @return the related number of pixels to the specified density.
     */
    public int convertDiptoPix(int dip){
        float scale = getDensity(this.context);
        return (int) (dip * scale + 0.5f);
    }

    /**
     * Converts pixel number to screen density, depending on the application context.
     * @param pixel, the number of pixels needed.
     * @return the related screen density to the specified number of pixels.
     */
    public int convertPixtoDip(int pixel){
        float scale = getDensity(this.context);
        return (int)((pixel - 0.5f)/scale);
    }

    private static Point getScreenPoint(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * gets screen height depending on context
     * @return screen height on pixels
     */
    public int getScreenHeight()
    {
        Point size = getScreenPoint(this.context);
        return size.y;
    }

    /**
     * gets screen width depending on context
     * @return screen width on pixels
     */
    public int getScreenWidth()
    {
        Point size = getScreenPoint(this.context);
        return size.x;
    }

    /**
     * Converts an integer to a String
     * @param i an integer
     * @return a corresponding string for this integer
     */
    public String itos(int i)
    {
        Integer bacon = new Integer(i);
        return bacon.toString();
    }

    public int stoi(String s)
    {
        Integer cheese = new Integer(s);
        return cheese.intValue();
    }
}
