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

        if (sheeps.size() != 0) {
            layoutSheeps.removeAllViews();
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
        final Context context = getApplicationContext();
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        int howMany = layoutSheeps.getChildCount();

        layoutSheeps.removeViewAt(howMany-1);
        controller.setSheeps(MainView.extractSheeps(context, layoutSheeps));
    }

    /* Entering edit mode */
    private void enterEditing()
    {
        Context context = getApplicationContext();
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        ArrayList<Sheep> sheeps = controller.getSheeps();

        /* Change button's icon */
        // TODO: change the icon to a "right" sign

        if (sheeps.size() == 0) {
            layoutSheeps.removeAllViews();
            addEmptySheep(context);
        }

        layoutSheeps.addView(new Button(context) {
            @Override
            public void setOnClickListener(OnClickListener l) {
                addEmptySheep(getApplicationContext());
            }

            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText("+", type);
            }
        });
    }

    private LinearLayout addEmptySheep(Context context, LinearLayout layoutSheeps)
    {
        LinearLayout layoutSheep = MainView.newEmptySheep(context);
        int howMany = layoutSheeps.getChildCount();

        if (howMany == 0) {
            ++howMany;
        }

        layoutSheeps.addView(layoutSheep, howMany-1);
        return layoutSheeps;
    }

    private LinearLayout addEmptySheep(Context context)
    {
        LinearLayout ls = (LinearLayout) findViewById(R.id.layoutSheeps);
        addEmptySheep(context, ls);
        Toast.makeText(MainActivity.this,
                       new Integer(ls.getChildCount()).toString(),
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
