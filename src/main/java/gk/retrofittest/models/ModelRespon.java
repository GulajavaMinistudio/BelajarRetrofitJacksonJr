package gk.retrofittest.models;

/**
 * Created by Gulajava Ministudio on 11/13/15.
 */
public class ModelRespon {

    private int id;
    private String name;
    private String full_name;

    public ModelRespon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
