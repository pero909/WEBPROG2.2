package finki.ukim.mk.webapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
        this.id=(long)(Math.random()*1000);

    }

    public Manufacturer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
