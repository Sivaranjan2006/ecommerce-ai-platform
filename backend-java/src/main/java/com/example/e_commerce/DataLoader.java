package com.example.e_commerce;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();
        System.out.println("Database cleared. Loading expanded catalog...");

        // --- CATEGORY: PC GEAR
        Product p1 = new Product();
        p1.setProductId(101); p1.setName("Wireless Gaming Mouse");
        p1.setDescription("High DPI RGB wireless mouse for gaming"); p1.setPrice(49.99); p1.setCategory("PC Gear");

        Product p2 = new Product();
        p2.setProductId(102); p2.setName("Mechanical Keyboard");
        p2.setDescription("RGB backlit mechanical keyboard with blue switches"); p2.setPrice(89.99); p2.setCategory("PC Gear");

        Product p3 = new Product();
        p3.setProductId(103); p3.setName("Ergonomic Desk Chair");
        p3.setDescription("Comfortable mesh chair for long office hours"); p3.setPrice(199.99); p3.setCategory("Office");

        Product p4 = new Product();
        p4.setProductId(104); p4.setName("Gaming Monitor");
        p4.setDescription("27-inch 144Hz monitor with vivid colors for gamers"); p4.setPrice(299.99); p4.setCategory("PC Gear");

        Product p5 = new Product();
        p5.setProductId(105); p5.setName("Leather Journal");
        p5.setDescription("Premium leather notebook for office writing"); p5.setPrice(19.99); p5.setCategory("Office");

        // ---CATEGORY: AUDIO ---
        Product p6 = new Product();
        p6.setProductId(106); p6.setName("Noise Cancelling Headphones");
        p6.setDescription("Over-ear bluetooth headphones with active noise cancellation"); p6.setPrice(149.99); p6.setCategory("Audio");

        Product p7 = new Product();
        p7.setProductId(107); p7.setName("Wireless Earbuds");
        p7.setDescription("Compact sweat-proof earbuds for workouts"); p7.setPrice(79.99); p7.setCategory("Audio");

        // ---CATEGORY: WEARABLES ---
        Product p8 = new Product();
        p8.setProductId(108); p8.setName("Pro Smart Watch");
        p8.setDescription("Track your health, heart rate, and notifications on your wrist"); p8.setPrice(199.99); p8.setCategory("Wearables");

        Product p9 = new Product();
        p9.setProductId(109); p9.setName("Minimalist Fitness Band");
        p9.setDescription("Slim step-tracker and sleep monitor"); p9.setPrice(49.99); p9.setCategory("Wearables");

        // ---CATEGORY: SMART HOME ---
        Product p10 = new Product();
        p10.setProductId(110); p10.setName("Smart Home Hub");
        p10.setDescription("Voice-controlled assistant to manage your smart devices"); p10.setPrice(89.99); p10.setCategory("Smart Home");

        Product p11 = new Product();
        p11.setProductId(111); p11.setName("RGB Smart Bulb");
        p11.setDescription("Color-changing WiFi LED bulb controllable via app"); p11.setPrice(15.99); p11.setCategory("Smart Home");

        // Save all 11 products!
        productRepository.saveAll(java.util.Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        System.out.println("All 11 products successfully saved to MySQL!");
    }

}
