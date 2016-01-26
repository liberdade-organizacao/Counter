package br.eng.crisjr.sheep.View;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.eng.crisjr.sheep.Model.Sheep;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by cris on 23/01/2016.
 */
public class MainView {

    /**
     * Fills the sheeps layout with sheep
     * @param context application context
     * @param layout layout that will have the sheep added to
     * @param sheeps list of sheep
     * @return layout filled with sheep
     */
    public static LinearLayout populateSheeps(Context context,
                                              LinearLayout layout,
                                              ArrayList<Sheep> sheeps)
    {
        for (int index = 0; index < sheeps.size(); ++index)
        {
            Sheep sheep = sheeps.get(index);
            String name = sheep.getName();
            int count = sheep.getCount();
            layout.addView(newSheep(context, name, count));
        }

        return layout;
    }

    /**
     * Adds a sheep with the defined information
     * @param context The application's context
     * @param name The sheep's name
     * @param count The sheep's count
     * @return A layout containing the specified sheep
     */
    public static LinearLayout newSheep(Context context, String name, int count)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        TextView textSheep = new TextView(context);

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
        textSheep.setText(name);
        textSheep.setTextColor(0xffeeeeee);
        textSheep.setBackgroundColor(0xff000000);
        layoutSheep.addView(textSheep);
        return populateSheep(context, layoutSheep, count);
    }

    /**
     * Creates an empty sheep
     * @param context the application context
     * @return an empty layout containing a sheep to be filled
     */
    public static LinearLayout newEmptySheep(Context context) {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText editSheep = new EditText(context);

        editSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
        layoutSheep.addView(editSheep);
        return populateSheep(context, layoutSheep, 0);
    }

    private static LinearLayout populateSheep(Context context, LinearLayout layoutSheep, final int count)
    {

        layoutSheep.setOrientation(LinearLayout.HORIZONTAL);
        layoutSheep.addView(new Button(context) { // MINUS BUTTON
            @Override
            public void setOnClickListener(OnClickListener l) {
                super.setOnClickListener(l); // TODO: implement decrement
            }

            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText("-", type);
            }

            @Override
            public void setLayoutParams(ViewGroup.LayoutParams params) {
                super.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                    0.1f));
            }
        });
        layoutSheep.addView(new TextView(context) { // COUNTER
            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText(new Integer(count).toString(), type);
            }

            @Override
            public void setLayoutParams(ViewGroup.LayoutParams params) {
                super.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                    0.2f));
            }
        });
        layoutSheep.addView(new Button(context) { // PLUS BUTTON
            @Override
            public void setOnClickListener(OnClickListener l) {
                super.setOnClickListener(l); // TODO: implement increment
            }

            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText("+", type);
            }

            @Override
            public void setLayoutParams(ViewGroup.LayoutParams params) {
                super.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                    0.1f));
            }
        });

        return layoutSheep;
    }

    /**
     * Creates a list out a layout on the screen.
     * @param context the application context.
     * @param layoutSheeps the layout from which the method will extract the sheep.
     * @return an arraylist of the sheep currently on screen.
     */
    public static ArrayList<Sheep> extractSheeps(Context context, LinearLayout layoutSheeps)
    {
        ArrayList<Sheep> sheeps = new ArrayList<>();
        int howMany = layoutSheeps.getChildCount();

        for (int i = 1; i < howMany; ++i)
        {
            LinearLayout layoutSheep = (LinearLayout) layoutSheeps.getChildAt(i);
            String name = getNameFromLayout(layoutSheep);
            int count = getCountFromLayout(layoutSheep);

            Sheep sheep = new Sheep();
            sheep.setName(name);
            sheep.setCount(count);
            sheeps.add(sheep);
        }

        return sheeps;
    }

    private static String getNameFromLayout(LinearLayout ls)
    {
        String name = null;
        View view = ls.getChildAt(0);

        try {
            TextView text = (TextView) view;
            name = text.getText().toString();

        }
        catch (ClassCastException cc) {
            EditText edit = (EditText) view;
            name = edit.getText().toString();
        }

        return name;
    }

    private static int getCountFromLayout(LinearLayout ls)
    {
        TextView tv = (TextView) ls.getChildAt(2);
        return Integer.parseInt(tv.getText().toString());
    }

    /**
     * Removes every view but the desired button from the Sheeps layout
     * @param layoutSheeps
     */
    public static void removeEveryOtherView(LinearLayout layoutSheeps) {
        for (int i = 1; i < layoutSheeps.getChildCount(); i++) {
            layoutSheeps.removeViewAt(1);
        }
    }
}
