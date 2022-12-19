package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryOrderRepository {
    public List<Order> findAll(){
        return DataHolder.orderList;
    }
   // public Order createOrder(Order c){
      //  if(c==null||c.getClientName().isEmpty()){

     //   }
     //   DataHolder.orderList.add(c);
    //    return c;
   // }
    public Optional<Order> findById(Long id){
        return DataHolder.orderList
                .stream()
                .filter(i->i.getOrderId().equals(id))
                .findFirst();
    }
    public Optional<Order> save(Balloon balloon
                                ,String clientName,String clientAdress,Long orderId){
       return null;
    }
}
