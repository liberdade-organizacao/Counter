package br.eng.crisjr.sheep;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import android.widget.Toast;
import br.eng.crisjr.sheep.Controller.Sheeps;
import br.eng.crisjr.sheep.Model.Sheep;
import br.eng.crisjr.sheep.View.MainView;

public class MainActivity extends AppCompatActivity {
    private Sheeps controller = new Sheeps();
    private Typeface fontIcon = null;
    private boolean isEditing = false;
    private boolean isRemoving = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* change typefaces */
        // TODO: discover how to change app's typeface
//        fontIcon = Typeface.createFromAsset(getAssets(), "open-iconic.ttf");
//        Button btn = (Button) findViewById(R.id.buttonEdit);
//        btn.setTypeface(fontIcon); btn.setText("\uE0DB");
//        btn = (Button) findViewById(R.id.buttonRemove);
//        btn.setTypeface(fontIcon); btn.setText("\uE0AA");

        updateSheeps();
    }

    /**
     * Update the list of sheeps on screen
     */
    private void updateSheeps()
    {
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        ArrayList<Sheep> sheeps = controller.getSheeps();
        Context context = getApplicationContext();

        if (sheeps.size() > 1) {
            MainView.removeEveryOtherView(layoutSheeps);
            MainView.populateSheeps(context, layoutSheeps, sheeps);
        } else {
            TextView tt = new TextView(context);
            tt.setText(R.string.empty_sheeps);
            tt.setTextColor(0xffeeeeee);
            tt.setBackgroundColor(0xff000000);
            layoutSheeps.addView(tt);
        }
    }

    /**
     * Callback to edit button. If it is not in editing mode, it will trigger the
     * edition tools; otherwise it will save the current sheeps' state.
     * @param view
     */
    public void onClickEditButton(View view)
    {
        if (isRemoving) {
            isRemoving = !isRemoving;
        }

        if (isEditing) {
            exitEditing();
            updateSheeps();
        }
        else {
            enterEditing();
        }

        isEditing = !isEditing;
    }

    /* Exiting edit mode */
    private void exitEditing()
    {
        Context context = getApplicationContext();
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        /* Change button's icon */
        // TODO: change the icon to an "add" sign

        buttonAdd.setVisibility(View.GONE);
        controller.setSheeps(MainView.extractSheeps(context, layoutSheeps));
    }

    /* Entering edit mode */
    private void enterEditing()
    {
        Context context = getApplicationContext();
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        ArrayList<Sheep> sheeps = controller.getSheeps();
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        /* Change button's icon */
        // TODO: change the icon to a "right" sign

        buttonAdd.setVisibility(View.VISIBLE);

        if (sheeps.size() == 0) {
            layoutSheeps.removeViewAt(layoutSheeps.getChildCount()-1);
            addEmptySheep(context);
        }
    }

    /**
     * Callback to "+" button
     * @param view
     */
    public void onClickAddButton(View view) {
        addEmptySheep(getApplicationContext());
    }

    /**
     * Adds an empty sheep to the sheeps layout
     * @param context the application's context
     * @param layoutSheeps the sheeps layout
     * @return layoutSheeps
     */
    private LinearLayout addEmptySheep(Context context, LinearLayout layoutSheeps)
    {
        layoutSheeps.addView(MainView.newEmptySheep(context));
        return layoutSheeps;
    }

    private LinearLayout addEmptySheep(Context context)
    {
        LinearLayout ls = (LinearLayout) findViewById(R.id.layoutSheeps);
        addEmptySheep(context, ls);
        Toast.makeText(MainActivity.this,
                       new Integer(ls.getChildCount()-1).toString(),
                       Toast.LENGTH_SHORT).show();
        return ls;
    }

    /**
     * Sets the callback to the remove button
     * @param view
     */
    public void onClickRemoveButton(View view)
    {
        String text = "Not removing";

        if (isEditing) {
            exitEditing();
            isEditing = !isEditing;
        }

        // TODO: implement logic to remove button
        if (!isRemoving) {
            text = "Removing now";
        }

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        isRemoving = !isRemoving;
    }


}
