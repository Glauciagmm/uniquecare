package com.uniquecare.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Contract {

    public enum State {
        OPEN,
        ACCEPTED,
        DECLINED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date start;
    private Date finish;
    private double totalPrice;
    private State state;


    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"contract"})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Facility facility;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"contract", "facility", "roles"})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User client;

    public Contract() {
    }

    public Contract(Long id, Date start, Date finish, double totalPrice, Facility facility, User client) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.totalPrice = totalPrice;
        this.facility = facility;
        this.client = client;
    }

    public Contract(Date start, Date finish, double totalPrice, Facility facility, User client) {
        this.start = start;
        this.finish = finish;
        this.totalPrice = totalPrice;
        this.facility = facility;
        this.client = client;
    }

    public Contract(Long id) {
        this.id = id;
    }

    public Contract(Date start, Date finish) {
        this.start = start;
        this.finish = finish;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {

        this.facility = facility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", start=" + start +
                ", finish=" + finish +
                ", totalPrice=" + totalPrice +
                ", facility=" + facility +
                ", client=" + client +
                '}';
    }
}