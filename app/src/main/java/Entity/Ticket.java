package Entity;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String id;
    private String date;
    private String gouv;
    private String iduser;
    private String moyenTransport;
    private String stationDepart;
    private String stationArrive;
    private double prix;
    private String ligne;

    public Ticket() {
    }

    public String getId() {
        return id;
    }



    public Ticket(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGouv() {
        return gouv;
    }

    public void setGouv(String gouv) {
        this.gouv = gouv;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public String getStationDepart() {
        return stationDepart;
    }

    public void setStationDepart(String stationDepart) {
        this.stationDepart = stationDepart;
    }

    public String getStationArrive() {
        return stationArrive;
    }

    public void setStationArrive(String stationArrive) {
        this.stationArrive = stationArrive;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }
}
