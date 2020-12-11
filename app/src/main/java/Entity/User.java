package Entity;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String FullName;
    private String password;
    private String token;
    private String email;

    private String phone;


    public User() {
    }

    public User(int id, String fullName, String password, String token, String email, String phone) {
        this.id = id;
        FullName = fullName;
        this.password = password;
        this.token = token;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}