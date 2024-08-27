package Lesson4.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Car")
public class Car extends EntityBase{

    private String type;

    public Car() {
    }

    public Car(String type, String model) {
        super(model);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String model) {
        this.type = model;
    }
}
