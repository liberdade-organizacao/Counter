package br.eng.crisjr.sheep.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class to store and retrieve data
 */
public class Database
{
    public Database()
    {

    }

    public void store(Context context, ArrayList<Sheep> sheep)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        int size = sheep.size();

        editor.putInt("size", size);
        for (int i = 0; i < size; ++i)
        {
            Sheep animal = sheep.get(i);
            String name = animal.getName();
            int count = animal.getCount();
            editor.putString("tag" + i, name);
            editor.putInt("count" + i, count);
        }

        editor.commit();
    }

    public ArrayList<Sheep> retrieve(Context context)
    {
        ArrayList<Sheep> sheep = new ArrayList<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int size = preferences.getInt("size", -1);

        if (size < 0) {
            for (int i = 0; i < size; ++i)
            {
                Sheep animal = new Sheep();
                animal.setName(preferences.getString("tag"+i, "none"));
                animal.setCount(preferences.getInt("count"+i, 0));
                sheep.add(animal);
            }
        }

        return sheep;
    }
}
