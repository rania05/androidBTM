package Entity;

import java.io.Serializable;
import java.util.Date;

public class Horaires  implements Serializable {
    private String id;
    private Date heureDepart;
    private Date heureArrive;

    public Horaires(String id, Date heureDepart, Date heureArrive) {
        this.id = id;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
    }

    public Horaires() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Date getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(Date heureArrive) {
        this.heureArrive = heureArrive;
    }
}
