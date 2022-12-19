package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {
    public Balloon(String name, String description, String balloonColor, String balloonSize, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.manufacturer = manufacturer;
    }

    private String name;
    private String description;
    private String balloonColor;
    private String balloonSize;

    public String getBalloonColor() {
        return balloonColor;
    }

    public void setBalloonColor(String balloonColor) {
        this.balloonColor = balloonColor;
    }

    public String getBalloonSize() {
        return balloonSize;
    }

    public void setBalloonSize(String balloonSize) {
        this.balloonSize = balloonSize;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Manufacturer manufacturer;

    public Balloon(String name, String description,Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer=manufacturer;
    }

    public Balloon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
