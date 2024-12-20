package finki.ukim.mk.webapp;

import finki.ukim.mk.webapp.model.Category;
import finki.ukim.mk.webapp.model.Manufacturer;
import finki.ukim.mk.webapp.model.Product;
import finki.ukim.mk.webapp.model.Role;
import finki.ukim.mk.webapp.service.CategoryService;
import finki.ukim.mk.webapp.service.ManufacturerService;
import finki.ukim.mk.webapp.service.ProductService;
import finki.ukim.mk.webapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WebAppApplicationTests {
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;
    private static Category c1;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;



    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData(){
        if (!dataInitialized) {
            c1 = categoryService.create("c1", "c1");
            categoryService.create("c2", "c2");

            m1 = manufacturerService.save("m1", "m1").get();
            manufacturerService.save("m2", "m2");

            String user = "user_test";
            String admin = "admin_test";

            userService.register(user, user, user, user, user, Role.ROLE_USER);
            userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }

    }
    @Test
    void contextLoads() {
    }
    @Test
    public void testGetProducts() throws Exception {
      MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.
              get("/products");
      this.mockMvc.perform(productRequest)
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
              .andExpect(MockMvcResultMatchers.model().attribute("bodyContent","products"))
              .andExpect(MockMvcResultMatchers.view().name("master-template"));

    }
    @Test
    public void testDeleteProducts() throws Exception {
        Product product= this.productService.save("test",2.0,2,c1.getId(),
                m1.getId()).get();
        MockHttpServletRequestBuilder productDeleteRequest=MockMvcRequestBuilders
                .delete("/products/delete/"+product.getId());

        this.mockMvc.perform(productDeleteRequest)
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));

    }

}
