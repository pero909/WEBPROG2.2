package mk.finki.ukim.mk.lab.bootstrap;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static Long OrderId= 0L;
    public static List<Balloon> balloonList =new ArrayList<>();
    public static List<Order> orderList = new ArrayList<>();
    public static List<Manufacturer> manufacturers =  new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts=new ArrayList<>();
    public static List<User> users=new ArrayList<>();
//@PostConstruct
  //  public void init(){
     //   balloonList.add(new Balloon("mite","Green Balloon"));


     //   manufacturers.add(new Manufacturer("Nike","USA","Meckin Kamen"));
    //    manufacturers.add(new Manufacturer("Adidas","USA","Roosvelt"));
    //    manufacturers.add(new Manufacturer("Metgo","USA","4 July"));
     //   manufacturers.add(new Manufacturer("Miles","USA","DUselforf"));
     //   manufacturers.add(new Manufacturer("MMP","USA","DDBNS"));

   // balloonList.add(new Balloon("kite" ,"breen Balloon",manufacturers.get(0)));
  //  balloonList.add(new Balloon("rite","sreen Balloon",manufacturers.get(0)));
   // balloonList.add(new Balloon("2ite","dreen Balloon",manufacturers.get(0)));
  //  balloonList.add(new Balloon("site","Green Balloon",manufacturers.get(0)));
  //  balloonList.add(new Balloon("dite","Green Balloon",manufacturers.get(0)));
   // balloonList.add(new Balloon("pite","Green Balloon",manufacturers.get(0)));
   // balloonList.add(new Balloon("vite","Green Balloon",manufacturers.get(0)));
  //  balloonList.add(new Balloon("nite","Green Balloon",manufacturers.get(0)));
  //  balloonList.add(new Balloon("sjite","Green Balloon",manufacturers.get(0)));
  //  balloonList.add(new Balloon("lite","Green Balloon",manufacturers.get(0)));

//}
}
