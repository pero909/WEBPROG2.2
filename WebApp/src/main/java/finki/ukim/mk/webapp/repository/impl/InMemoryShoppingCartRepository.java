package finki.ukim.mk.webapp.repository.impl;

import finki.ukim.mk.webapp.Enum.ShoppingCartStatus;
import finki.ukim.mk.webapp.bootstrap.DataHolder;
import finki.ukim.mk.webapp.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {
    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts
                .stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
    }
    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status){
        return DataHolder.shoppingCarts.stream()
                .filter(i->i.getUser().getUsername().equals(username)
                && i.getStatus().equals(status))
                .findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        DataHolder.shoppingCarts.
                removeIf(i->i.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }
}
