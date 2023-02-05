package com.betaplan.krisela.beltexam.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title name is required")
    @Column(unique=true)
    @Size(min = 3, max = 100, message = "Title name must be between 3-100.")
    private String title;

    @NotEmpty(message = "Network is required")
    @Size(min = 3, max = 1000, message = "Network must be between 3-100.")
    private String network;

    @NotEmpty(message = "Description is required")
    @Size(min = 3, max = 1000, message = "Description must be between 3-1000.")
    private String description;

   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;*/

    private double average;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
    private List<Rating> ratingsShow;

    // Constructor, Getters & Setters
    public Show() {
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Rating> getRatingsShow() {
        return ratingsShow;
    }

    public void setRatingsShow(List<Rating> ratingsShow) {
        this.ratingsShow = ratingsShow;
    }
}
