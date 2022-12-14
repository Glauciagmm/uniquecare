package com.uniquecare.models;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "facility",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})

public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double pricePerHour;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "facility_categories",
            joinColumns = {@JoinColumn(name = "facility_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @JsonIgnore
    private Set<Categories> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "assistant_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"facility", "roles"})
    private User assistant;

    @OneToMany(mappedBy = "facility")
    @JsonIgnoreProperties({"facility"})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Set<Contract> contract = new HashSet<>();


    public Facility() {
    }

    public Facility(Long id, String title, String description, double pricePerHour, Set<Categories> categories, User assistant, Set<Contract> contract) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.categories = categories;
        this.assistant = assistant;
        this.contract = contract;
    }

    public Facility(Long id) {
        this.id = id;
    }

    public Facility(String title, String description, double pricePerHour, Set<Categories> categories, User assistant, Set<Contract> contract) {
        this.title = title;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.categories = categories;
        this.assistant = assistant;
        this.contract = contract;
    }

    public Facility(String title, String description, double pricePerHour) {
        this.title = title;
        this.description = description;
        this.pricePerHour = pricePerHour;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Set<Categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }

    public User getAssistant() {
        return assistant;
    }

    public void setAssistant(User assistant) {
        this.assistant = assistant;
    }

    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }


    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", categories=" + categories +
                ", assistant=" + assistant +
                ", contract=" + contract +
                '}';
    }
}
