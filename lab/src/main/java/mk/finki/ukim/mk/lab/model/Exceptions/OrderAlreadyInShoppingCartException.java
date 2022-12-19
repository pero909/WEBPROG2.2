package mk.finki.ukim.mk.lab.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class OrderAlreadyInShoppingCartException extends RuntimeException{
    public OrderAlreadyInShoppingCartException(Long id, String username){
        super(String.format("Product with id: %d already exists in shopping cart for username %s", id, username));
    }
}