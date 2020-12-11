package Entity;

import java.io.Serializable;

public class Ligne implements Serializable {
    private String id;
    private String villeDepart;
   private String villeArrive;

    public Ligne() {
    }

    public Ligne(String id, String villeDepart, String villeArrive) {
        this.id = id;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }
}
