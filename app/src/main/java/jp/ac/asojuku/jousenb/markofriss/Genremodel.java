package jp.ac.asojuku.jousenb.markofriss;

import java.io.Serializable;

/**
 * Created by user on 2017/07/07.
 */

public class Genremodel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int seikai;
    private int count;

    public String get_Name() {
        return name;
    }

    public void set_Name(String name) {
        this.name = name;
    }

    public int get_Seikai() {
        return seikai;
    }

    public void set_Seikai(int seikai) { this.seikai = seikai;}

    public int get_Count() {return count; }

    public void set_Count(int count) {
        this.count = count;
    }
}
