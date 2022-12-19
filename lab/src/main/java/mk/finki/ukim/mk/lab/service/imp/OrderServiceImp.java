package mk.finki.ukim.mk.lab.service.imp;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final BalloonRepository balloonRepository;

    public OrderServiceImp(OrderRepository orderRepository, BalloonRepository balloonRepository) {
        this.orderRepository = orderRepository;
        this.balloonRepository = balloonRepository;
    }
    public List<Order> orderList(){
        return orderRepository.findAll();
    }


    @Override
    public Order placeOrder(Balloon balloon, String clientName, String address) {
        Order order = new Order(balloon.getBalloonColor(), balloon.getBalloonSize());
        DataHolder.orderList.add(order);
        return order;
    }


    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of
                (this.orderRepository
                        .save(order));
    }



    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }


}
