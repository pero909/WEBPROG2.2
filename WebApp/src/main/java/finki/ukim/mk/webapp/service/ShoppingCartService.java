package finki.ukim.mk.webapp.service;

import finki.ukim.mk.webapp.model.Product;
import finki.ukim.mk.webapp.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username,Long productId);
}
