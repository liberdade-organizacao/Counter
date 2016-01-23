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

import br.eng.crisjr.sheep.Controller.Sheeps;
import br.eng.crisjr.sheep.Model.Sheep;
import br.eng.crisjr.sheep.View.MainView;

public class MainActivity extends AppCompatActivity {
    private Sheeps controller = new Sheeps();
    private Typeface fontIcon = null;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* change typefaces */
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
        if (isEditing) {
            exitEditing();
            updateSheeps();
        }
        else {
            enterEditing();
        }

        isEditing = !isEditing;
    }

    private void exitEditing()
    {

    }

    private void enterEditing()
    {
        Context context = getApplicationContext();
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        ArrayList<Sheep> sheeps = controller.getSheeps();

        if (sheeps.size() == 0) {
            layoutSheeps.removeAllViews();
            LinearLayout layoutSheep = MainView.newEmptySheep(context);
            layoutSheeps.addView(layoutSheep);
        }

        layoutSheeps.addView(new Button(context) {
            @Override
            public void setOnClickListener(OnClickListener l) {
                super.setOnClickListener(l); // TODO: give a callback to this button
            }
        });
    }
}
