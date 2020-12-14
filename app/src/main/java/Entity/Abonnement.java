package Entity;

import java.io.Serializable;
import java.util.Date;

public class Abonnement  implements Serializable {
    private String id;
     String dateDebut;
    String image;
    String idUser;
    String moyenTransport;
    String ville;
    String ligne;
    String depart;
    String destination;
    String duree;
    Double prix;

    public Abonnement() {
    }

    public Abonnement(String id, String dateDebut, String image, String idUser, String moyenTransport, String ville, String ligne, String depart, String destination, String duree, Double prix) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.image = image;
        this.idUser = idUser;
        this.moyenTransport = moyenTransport;
        this.ville = ville;
        this.ligne = ligne;
        this.depart = depart;
        this.destination = destination;
        this.duree = duree;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}
