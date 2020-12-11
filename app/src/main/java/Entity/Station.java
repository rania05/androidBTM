package Entity;

import java.io.Serializable;

public class Station  implements Serializable {
    private String id;
    private String nom;
    private String localisation;
    private Transport transport;

    public Station(String id, String nom, String localisation,Transport transport) {
        this.transport = transport;
        this.id = id;
        this.nom = nom;
        this.localisation = localisation;
    }

    public Station() {
    }

    public Transport getTransport(){ return transport;}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
