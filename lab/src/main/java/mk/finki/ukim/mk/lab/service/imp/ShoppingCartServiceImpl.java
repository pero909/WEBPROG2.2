package mk.finki.ukim.mk.lab.service.imp;


import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.lab.model.Exceptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final OrderRepository orderRepository;
    private final BalloonRepository balloonRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;


    public ShoppingCartServiceImpl(OrderRepository orderRepository,
                                   BalloonRepository balloonRepository,
                                   ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.balloonRepository = balloonRepository;
        this.shoppingCartRepository = shoppingCartRepository;

        this.userRepository = userRepository;
    }

    @Override
    public List<Order> listAllOrders(Long cartId) {
        if(this.shoppingCartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getOrders();
    }

    @Override
    public ShoppingCart getCurrentUserCart(String username) {
        User user= this.userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));


        return this.shoppingCartRepository.findByUser(user)
                .orElseGet(()->{
                   ShoppingCart cart=new ShoppingCart(user);
                   return this.shoppingCartRepository.save(cart);
                });


    }

    @Transactional
    @Override
    public ShoppingCart addOrder(Order order, Long cartId) {
        Optional<ShoppingCart> cart=Optional.of(this.shoppingCartRepository.findById(cartId))
                .orElseThrow(InvalidArgumentException::new);
        cart.get().getOrders().add(order);
        this.shoppingCartRepository.save(cart.get());
        return cart.get();
    }
}
