package Lesson4.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garage_seq")
    @SequenceGenerator(name = "garage_seq", sequenceName = "garage_sequence", allocationSize = 1)
    private Long id;

    private String location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id")
    private List<Car> cars = new ArrayList<>();

    public Garage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
