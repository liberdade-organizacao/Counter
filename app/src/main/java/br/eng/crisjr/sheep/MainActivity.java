package br.eng.crisjr.sheep;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import br.eng.crisjr.sheep.Controller.Sheeps;
import br.eng.crisjr.sheep.Model.Sheep;
import br.eng.crisjr.sheep.View.IconicConstants;
import br.eng.crisjr.sheep.View.MainView;

public class MainActivity
extends AppCompatActivity
{
    private Typeface fontIcon = null;
    private boolean isEditing = false;
    private boolean isRemoving = false;
    private LinearLayout layoutSheeps = null;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* change typefaces */
        fontIcon = Typeface.createFromAsset(getResources().getAssets(), "open-iconic.ttf");
        IconicConstants.setTypeface(fontIcon);
        Button btn = (Button) findViewById(R.id.buttonEdit);
        btn.setTypeface(fontIcon); btn.setText(IconicConstants.ADD);
        btn = (Button) findViewById(R.id.buttonRemove);
        btn.setTypeface(fontIcon); btn.setText(IconicConstants.DELETE);
        btn = (Button) findViewById(R.id.buttonAdd);
        btn.setTypeface(fontIcon); btn.setText(IconicConstants.ADD);

        updateSheeps();
    }

    /**
     * Update the list of sheeps on screen
     */
    private void updateSheeps()
    {
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        Context context = getApplicationContext();

        if (MainView.getSheepSize() > 0) {
            MainView.populateSheeps(context, layoutSheeps);
        }
        else {
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
            exitRemoving();
            isRemoving = !isRemoving;
        }

        if (isEditing) {
            exitEditing();
        }
        else {
            enterEditing();
        }

        isEditing = !isEditing;
    }

    /* Exiting edit mode */
    private void exitEditing()
    {
        context = getApplicationContext();
        layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonEdit.setText(IconicConstants.ADD);
        buttonAdd.setVisibility(View.GONE);
        MainView.extractSheeps(context, layoutSheeps);
        MainView.removeEveryOtherView(layoutSheeps);
        updateSheeps();
    }

    /* Entering edit mode */
    private void enterEditing()
    {
        context = getApplicationContext();
        layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);

        buttonAdd.setVisibility(View.VISIBLE);
        buttonEdit.setText(IconicConstants.OK);
        MainView.removeEveryOtherView(layoutSheeps);

        if (MainView.getSheepSize() == 0) {
            addEmptySheep(context, layoutSheeps);
        }
        else {
            MainView.populateEmptySheeps(context, layoutSheeps);
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
        layoutSheeps.addView(MainView.newSheep(context));
        return layoutSheeps;
    }

    private LinearLayout addEmptySheep(Context context)
    {
        LinearLayout ls = (LinearLayout) findViewById(R.id.layoutSheeps);
        addEmptySheep(context, ls);
        return ls;
    }

    /**
     * Sets the callback to the remove button
     * @param view
     */
    public void onClickRemoveButton(View view)
    {
        if (isEditing) {
            exitEditing();
            isEditing = !isEditing;
        }

        if (!isRemoving) {
            enterRemoving();
        }
        else {
            exitRemoving();
        }

        isRemoving = !isRemoving;
    }

    private void enterRemoving()
    {
        context = getApplicationContext();
        layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        Button button = (Button) findViewById(R.id.buttonRemove);

        MainView.removeEveryOtherView(layoutSheeps);
        button.setText(IconicConstants.OK);
        if (MainView.getSheepSize() == 0) {
            isRemoving = !isRemoving;
            exitRemoving();
        }
        else {
            MainView.populateFilledSheeps(context, layoutSheeps);
        }
    }

    private void exitRemoving()
    {
        context = getApplicationContext();
        layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        Button button = (Button) findViewById(R.id.buttonRemove);
        MainView.extractSheeps(context, layoutSheeps);
        MainView.removeEveryOtherView(layoutSheeps);
        button.setText(IconicConstants.DELETE);
        updateSheeps();
    }
}
