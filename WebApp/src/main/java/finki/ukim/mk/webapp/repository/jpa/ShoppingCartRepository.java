package finki.ukim.mk.webapp.repository.jpa;

import finki.ukim.mk.webapp.Enum.ShoppingCartStatus;
import finki.ukim.mk.webapp.model.ShoppingCart;
import finki.ukim.mk.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
