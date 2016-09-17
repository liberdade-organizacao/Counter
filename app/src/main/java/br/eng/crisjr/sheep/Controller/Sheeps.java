package br.eng.crisjr.sheep.Controller;

import java.util.ArrayList;

import android.content.Context;
import br.eng.crisjr.sheep.Model.Database;
import br.eng.crisjr.sheep.Model.Sheep;

/**
 * Class that implements the controller.
 */
public class Sheeps {
    /* I know that the plural of sheep is sheep
     * but I needed to differentiate the model from the controller somehow. SheepController
     * would occupy too much space and make me waste time. */
    private ArrayList<Sheep> sheeps = null;
    private Database data = null;

    public Sheeps() {
        this.data = new Database();
        this.sheeps = new ArrayList<Sheep>();
    }

    public Sheeps(Context context)
    {
        this();
        this.sheeps = this.data.retrieve(context);
    }


    public void addSheep(String name) {
        Sheep sheep = new Sheep(name);
        this.sheeps.add(sheep);
    }

    /**
     * @return the current arraylist of sheeps
     */
    public ArrayList<Sheep> getSheeps() {
        return this.sheeps;
    }

    /**
     * Sets the sheeps in this controller
     * @param sheeps the arraylist of sheep
     */
    public ArrayList<Sheep> setSheeps(ArrayList<Sheep> sheeps)
    {
        this.sheeps = sheeps;
        return this.sheeps;
    }

    public Sheep removeSheep(int index) {
        Sheep sheep = this.sheeps.get(index);
        this.sheeps.remove(index);
        return sheep;
    }

    /**
     * Creates a sheep out of a name and a count
     * @param name the sheep's name
     * @param count the counting it is related
     * @return the respective sheep object
     */
    public Sheep createSheep(String name, int count) {
        Sheep sheep = new Sheep();
        sheep.setName(name);
        sheep.setCount(count);
        return sheep;
    }

    /**
     * Retrieve sheeps from an existing context
     * @param context the application's context
     * @return the set sheeps
     */
    public ArrayList<Sheep> retrieve(Context context)
    {
        return setSheeps(data.retrieve(context));
    }

    /**
     * Saves the current sheep array to memory
     */
    public void store(Context context)
    {
        this.data.store(context, this.sheeps);
    }
}
