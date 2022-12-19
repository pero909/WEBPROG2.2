package mk.finki.ukim.mk.lab.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException {

    public ShoppingCartNotFoundException(Long id) {
        super(String.format("Shoppingcart with id %d was not found", id));
    }
}