package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "balloon_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;
    String balloonColor;
    String balloonSize;


    public Order(String balloonColor, String balloonSize) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
    }

    public Order(String balloonColor) {
        this.balloonColor = balloonColor;
        this.balloonSize = " ";

    }

    public Order() {

    }

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


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}