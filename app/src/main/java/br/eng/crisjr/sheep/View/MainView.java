package br.eng.crisjr.sheep.View;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.eng.crisjr.sheep.Model.Sheep;

import java.util.ArrayList;

/**
 * Created by cris on 23/01/2016.
 */
public class MainView {

    /**
     *
     * @param context
     * @param layout
     * @param sheeps
     * @return
     */
    public static LinearLayout populateSheeps(Context context,
                                               LinearLayout layout,
                                               ArrayList<Sheep> sheeps) {
        for (int index = 0; index < sheeps.size(); ++index)
        {
            layout.addView(createSheep(context, sheeps.get(index)));
        }

        return layout;
    }

    private static View createSheep(Context context, Sheep sheep) {
        LinearLayout layout = new LinearLayout(context);
        TextView text = new TextView(context);

        text.setText(sheep.getName());
        text.setTextColor(0xffeeeeee);
        text.setBackgroundColor(0xff000000);
        layout.addView(text);

        return layout;
    }

    /**
     * Creates an empty sheep
     * @param context the application context
     * @return an empty layout containing a sheep to be filled
     */
    public static LinearLayout newEmptySheep(Context context)
    {
        LinearLayout layoutSheep = new LinearLayout(context);
        EditText editSheep = new EditText(context);

        layoutSheep.setOrientation(LinearLayout.HORIZONTAL);
        layoutSheep.addView(editSheep);
        layoutSheep.addView(new Button(context) {
            @Override
            public void setOnClickListener(OnClickListener l) {
                super.setOnClickListener(l); // TODO: implement decrement
            }

            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText("-", type);
            }
        });
        layoutSheep.addView(new TextView(context) {
            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText("0", type);
            }
        });
        layoutSheep.addView(new Button(context) {
            @Override
            public void setOnClickListener(OnClickListener l) {
                super.setOnClickListener(l); // TODO: implement increment
            }

            @Override
            public void setText(CharSequence text, BufferType type) {
                super.setText("+", type);
            }
        });

        return layoutSheep;
    }

}
