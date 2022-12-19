package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
     List<Order> listAllOrders(Long cartId);
     ShoppingCart getCurrentUserCart(String username);
     ShoppingCart addOrder(Order order,Long cartId);

}
