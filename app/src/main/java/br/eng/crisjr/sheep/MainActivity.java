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

public class MainActivity extends AppCompatActivity {
    private Sheeps controller = new Sheeps();
    private Typeface fontIcon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* change typefaces */
        fontIcon = Typeface.createFromAsset(getAssets(), "open-iconic.ttf");
        Button btn = (Button) findViewById(R.id.buttonEdit);
        btn.setTypeface(fontIcon); btn.setText("\uE0DB");
        btn = (Button) findViewById(R.id.buttonRemove);
        btn.setTypeface(fontIcon); btn.setText("\uE0AA");

        updateSheeps();
    }

    private void updateSheeps() {
        LinearLayout layoutSheeps = (LinearLayout) findViewById(R.id.layoutSheeps);
        ArrayList<Sheep> sheeps = controller.get();

        if (sheeps.size() != 0) {
            layoutSheeps.removeAllViews();
            populateSheeps(layoutSheeps, sheeps);
        } else {
            TextView tt = new TextView(getApplicationContext());
            tt.setText(R.string.empty_sheeps);
            tt.setTextColor(0xffeeeeee);
            tt.setBackgroundColor(0xff000000);
            layoutSheeps.addView(tt);
        }
    }

    private void populateSheeps(LinearLayout layout, ArrayList<Sheep> sheeps) {
        for (int index = 0; index < sheeps.size(); ++index)
        {
            layout.addView(createSheep(sheeps.get(index)));
        }
    }

    private View createSheep(Sheep sheep) {
        Context context = getApplicationContext();
        LinearLayout layout = new LinearLayout(context);
        TextView text = new TextView(context);

        text.setText(sheep.getName());
        text.setTextColor(0xffeeeeee);
        text.setBackgroundColor(0xff000000);
        layout.addView(text);

        return layout;
    }

}
