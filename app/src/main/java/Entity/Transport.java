package Entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transport implements Serializable {

    private String id;
    private String type;
    private String region;
    private String ligne;
    private String depart;
private int numero;
    private String heure;

    List<Station> station = new ArrayList<Station>();
    List<Horaires> horaires = new ArrayList<Horaires>();
public  Transport (){


}    public Transport( String type, String region, String ligne, int numero, String heure,String depart) {

        this.type = type;
        this.region = region;
        this.ligne = ligne;
        this.numero = numero;
        this.heure = heure;
        this.depart = depart;
    }

    public Transport(String id, String type, String region, String ligne, int numero, String heure) {
        this.id = id;
        this.type = type;
        this.region = region;
        this.ligne = ligne;
        this.numero = numero;
        this.heure = heure;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Transport(String id, String region) {
        this.region=region;
        this.id = id;
    }

    public Transport(String id, String type, String region) {
        this.id = id;
        this.type = type;
        this.region = region;
    }

    public Transport(String id, String type, String region, String ligne, List<Station> station, List<Horaires> horaires) {
        this.id = id;
        this.type = type;
        this.region = region;
        this.ligne = ligne;
        this.station = station;
        this.horaires = horaires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        ligne = ligne;
    }
}