package com.betaplan.krisela.beltexam.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    @Size(min=3, max=100, message="Username must be between 3-100.")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message="Please enter a valid email!")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min=8, max=100, message="Password must be between 3-100.")
    private String password;

    @Transient
    @NotEmpty(message = "Confirm Password is required")
    @Size(min=8, max=100, message="Confirm Password must be between 3-100.")
    private String confirm;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


  /*  @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id")
    )
    private List<Show> shows;*/

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Show> showsUser;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> ratingsUser;


    // Constructor, Getters & Setters
    public User() {
    }

/*    public User(Long id, String username, String email, String password, String confirm, Date createdAt, Date updatedAt, *//*List<Show> shows*//**//*,*//* List<Show> showsUser, List<Rating> ratingsUser) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
       *//* this.shows = shows;*//*
        this.showsUser = showsUser;
        this.ratingsUser = ratingsUser;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

   /* public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }*/

    public List<Show> getShowsUser() {
        return showsUser;
    }

    public void setShowsUser(List<Show> showsUser) {
        this.showsUser = showsUser;
    }

    public List<Rating> getRatingsUser() {
        return ratingsUser;
    }

    public void setRatingsUser(List<Rating> ratingsUser) {
        this.ratingsUser = ratingsUser;
    }
}

