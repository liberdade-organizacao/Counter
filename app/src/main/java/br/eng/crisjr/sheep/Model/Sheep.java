package br.eng.crisjr.sheep.Model;

public class Sheep {
    private String name = null;
    private long count = 0;

    public Sheep(String name) {
        this.setName(name);
    }

    /* gets and sets */
    public String getName() {
        return this.name;
    }

    public long getCount() {
        return this.count;
    }

    public String setName(String name) {
        this.name = name;
        return this.name;
    }

    public long setCount(long count) {
        this.count = count;
        return this.count;
    }
}
