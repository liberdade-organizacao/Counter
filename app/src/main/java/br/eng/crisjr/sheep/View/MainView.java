package br.eng.crisjr.sheep.View;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.eng.crisjr.sheep.Model.Sheep;
import java.util.ArrayList;
import java.util.Random;

/**
 * View class for the main activity.
 */
public class MainView {
    private static LinearLayout layoutSheeps = null;

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
        for (Sheep sheep: sheeps)
        {
            String name = sheep.getName();
            int count = sheep.getCount();
            layout.addView(newSheep(context, name, count));
        }

        return layout;
    }

    /**
     * Fills the layout with sheeps ready to be changed
     * @param context application's context
     * @param layout the layout to be filled
     * @param sheeps the arraylist of sheeps
     * @return the filled layout
     */
    public static LinearLayout populateEmptySheeps(Context context,
                                                   LinearLayout layout,
                                                   ArrayList<Sheep> sheeps)
    {
        for (Sheep sheep : sheeps) {
            layout.addView(newSheepToFill(context, sheep.getName(), sheep.getCount()));
        }

        return layout;
    }

    /**
     * Adds a sheep with the defined information for filling
     * @param context The application's context
     * @param name The sheep's name
     * @param count The sheep's count
     * @return A layout containing the specified sheep
     */
    public static LinearLayout newSheepToFill(Context context, String name, int count)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText textSheep = new EditText(context);

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
        textSheep.setText(name);
        textSheep.setTextColor(0xffeeeeee);
        textSheep.setBackgroundColor(0xff000000);
        layoutSheep.addView(textSheep);
        return populateSheep(context, layoutSheep, count);
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

        textSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
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
    public static LinearLayout newSheep(Context context) {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText editSheep = new EditText(context);

        editSheep.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                0.6f));
        layoutSheep.addView(editSheep);
        return populateSheep(context, layoutSheep, 0);
    }

    /**
     * Creates the rest of a sheep, regardless of its type
     * @param context application's context
     * @param layoutSheep sheep's layout
     * @param count counting measure
     * @return the filled layoutSheep
     */
    private static LinearLayout populateSheep(Context context, LinearLayout layoutSheep, int count)
    {
        TextView textCounter = new TextView(context);
        Button buttonMinus = new Button(context) {
            @Override
            public void setOnClickListener(OnClickListener l) { // TODO: write "-" button callback
                super.setOnClickListener(l);
            }
        };
        Button buttonPlus = new Button(context) { // TODO: write "+" button callback
            @Override
            public void setOnClickListener(OnClickListener l) {
                super.setOnClickListener(l);
            }
        };

        buttonMinus.setText("-");
        buttonMinus.setBackgroundColor(0xff000000);
        buttonMinus.setTextColor(0xffeeeeee);
        buttonMinus.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.1f));
        textCounter.setText(new Integer(count).toString());
        textCounter.setTextColor(0xffeeeeee);
//        textCounter.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textCounter.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.2f));
        buttonPlus.setText("+");
        buttonPlus.setBackgroundColor(0xff000000);
        buttonPlus.setTextColor(0xffeeeeee);
        buttonPlus.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  ViewGroup.LayoutParams.WRAP_CONTENT,
                                                                  0.1f));

        layoutSheep.setOrientation(LinearLayout.HORIZONTAL);
        layoutSheep.addView(buttonMinus);
        layoutSheep.addView(textCounter);
        layoutSheep.addView(buttonPlus);
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

        for (int i = 0; i < howMany; ++i)
        {
            LinearLayout layoutSheep = (LinearLayout) layoutSheeps.getChildAt(i);
            String name = getNameFromLayout(layoutSheep);
            int count = getCountFromLayout(layoutSheep);

            if (name.length() >= 1) {
                Sheep sheep = new Sheep();
                sheep.setName(name);
                sheep.setCount(count);
                sheeps.add(sheep);
            }
        }

        return sheeps;
    }

    private static String getNameFromLayout(LinearLayout ls)
    {
        String outlet = null;

        try {
            EditText edit = (EditText) ls.getChildAt(0);
            outlet = edit.getText().toString();
        }
        catch (ClassCastException cc) {
            TextView text = (TextView) ls.getChildAt(0);
            outlet = text.getText().toString();
        }

        return outlet;
    }

    private static int getCountFromLayout(LinearLayout ls)
    {
        TextView tv = (TextView) ls.getChildAt(2);
        return Integer.parseInt(tv.getText().toString());
    }

    /**
     * Removes every view but the desired button from the Sheeps layout
     * @param layoutSheeps layout to be dismembered
     */
    public static void removeEveryOtherView(LinearLayout layoutSheeps)
    {
        while (layoutSheeps.getChildCount() > 0)
        {
            layoutSheeps.removeViewAt(0);
        }
    }

    /**
     * Creates a sheeps' layout ready for remotion
     * @param context application's context
     * @param ls layout to be edited
     * @param sheeps arraylist of sheeps
     */
    public static void populateFilledSheeps(Context context,
                                            LinearLayout ls,
                                            ArrayList<Sheep> sheeps)
    {
        layoutSheeps = ls;

        for (Sheep sheep: sheeps)
        {
            layoutSheeps.addView(createFilledSheep(context, sheep));
        }
    }

    private static LinearLayout createFilledSheep(Context context, Sheep sheep)
    {
        Random random = new Random();
        LinearLayout layout = new LinearLayout(context);
        TextView tvn = new TextView(context);
        TextView tvc = new TextView(context);
        Button bt = new Button(context) { // TODO: write "delete" button callback
            @Override
            public void setOnClickListener(OnClickListener l) {
                MainView.deleteSheep(this);
            }
        };

        tvn.setText(sheep.getName());
        tvn.setTextColor(0xffeeeeee);
        tvc.setText(new Integer(sheep.getCount()).toString());
        tvc.setTextColor(0xffeeeeee);
        bt.setText("Delete");
        bt.setBackgroundColor(0xff000000);
        bt.setTextColor(0xfff93822);
        bt.setId(random.nextInt());
        bt.setClickable(true);

        tvn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0.6f));
        tvc.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0.2f));
        bt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0.2f));

        layout.addView(tvn);
        layout.addView(bt);
        layout.addView(tvc);

        return layout;
    }

    private static void deleteSheep(Button button)
    {
        int index = getSheepIndex(button);
        layoutSheeps.removeViewAt(index);
    }

    private static int getSheepIndex(Button toFind)
    {
        for (int i = 0; i < layoutSheeps.getChildCount(); i++)
        {
            LinearLayout sheep = (LinearLayout) layoutSheeps.getChildAt(i);
            Button current = (Button) sheep.getChildAt(1);
            if (current.getId() == toFind.getId()) {
                return i;
            }
        }

        return -1;
    }
}
