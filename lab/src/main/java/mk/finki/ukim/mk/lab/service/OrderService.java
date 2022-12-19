package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Exceptions.ColorNotChosenException;
import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(Balloon balloon, String clientName, String address);
    Optional<Order> findById(Long id);
    Optional<Order> save(Order order);
    List<Order> findAll();

}
