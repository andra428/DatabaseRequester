package com.example.demo.autentificare;
import jakarta.persistence.*;
@Entity
@Table(name="users")
public class users {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "uid", nullable = false)
    private int uid;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    public users(){}
    public  users(int uid, String username,String password, String role){
        this.uid=uid;
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public int getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
