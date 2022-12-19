package mk.finki.ukim.mk.lab;


import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.BaloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {
    MockMvc mockMvc;

    @Autowired
    AuthService authService;

    @Autowired
    BaloonService baloonService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    private static Manufacturer m1;

    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }
    @Test
    void contextLoads() {
    }
    private void initData(){
        if (!dataInitialized) {
            m1=manufacturerService.findAll().get(0);


            String user = "user_test";
            String admin = "admin_test";

            authService.login(user,user);

            dataInitialized = true;
        }

    }
    @Test
    public void getHomePage()throws Exception{
        MockHttpServletRequestBuilder homePageRequest= MockMvcRequestBuilders
                .get("/balloons/home");
        this.mockMvc.perform(homePageRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"));
    }

}
