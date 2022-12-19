package finki.ukim.mk.webapp.bootstrap;

import finki.ukim.mk.webapp.model.*;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers=new ArrayList<>();
    public static List<Product> products=new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();


//    @PostConstruct
  //  public void init(){
      //  categories=new ArrayList<>();
     //   categories.add(new Category("Books","category for books"));
     //   categories.add(new Category("Software","category for software"));

     //   users.add(new User("kostadin909","pass","kostadin","Mishev"));
     //   users.add(new User("kostadin912","pass","Martin","Vasev"));
     //   Manufacturer manufacturer= new Manufacturer("Nike","Meckin kamen");
     //   manufacturers.add(manufacturer);
     //   Category category= new Category("Sport","Sport category");
     //   categories.add(category);
     //   products.add(new Product("Ball",24.00,7,category,manufacturer));
     //   products.add(new Product("Ball",24.0,7,category,manufacturer));
      //  products.add(new Product("Ball",24.32,7,category,manufacturer));

   // }
}
