package finki.ukim.mk.webapp.service.impl;

import finki.ukim.mk.webapp.Enum.ShoppingCartStatus;
import finki.ukim.mk.webapp.model.Exceptions.ProductAlreadyInShoppingCart;
import finki.ukim.mk.webapp.model.Exceptions.ProductNotFoundException;
import finki.ukim.mk.webapp.model.Exceptions.ShoppingCartNotFoundException;
import finki.ukim.mk.webapp.model.Exceptions.UserNotFoundException;
import finki.ukim.mk.webapp.model.Product;
import finki.ukim.mk.webapp.model.ShoppingCart;
import finki.ukim.mk.webapp.model.User;
import finki.ukim.mk.webapp.repository.impl.InMemoryProductRepository;
import finki.ukim.mk.webapp.repository.impl.InMemoryShoppingCartRepository;
import finki.ukim.mk.webapp.repository.impl.InMemoryUserRepository;
import finki.ukim.mk.webapp.repository.jpa.ProductRepository;
import finki.ukim.mk.webapp.repository.jpa.ShoppingCartRepository;
import finki.ukim.mk.webapp.repository.jpa.UserRepository;
import finki.ukim.mk.webapp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImp(ShoppingCartRepository shoppingCartRepository,
                                  UserRepository userRepository,
                                  ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProductList();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user =this.userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));

        return  this.shoppingCartRepository
                .findByUserAndStatus(user,ShoppingCartStatus.CREATED)
                .orElseGet(()->{
                        ShoppingCart cart = new ShoppingCart(user);
                return this.shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart=this.getActiveShoppingCart(username);
        Product product = this.productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));
        if(shoppingCart.getProductList().stream()
                .filter((i->i.getId().equals(productId)))
                .collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInShoppingCart(productId,username);
        shoppingCart.getProductList().add(product);
        return this.shoppingCartRepository.save(shoppingCart);

    }
}
