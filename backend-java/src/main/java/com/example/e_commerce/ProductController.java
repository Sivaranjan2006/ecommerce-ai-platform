package com.example.e_commerce;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.repository.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST Controller managing the product catalog and external AI recommendations.
 */
@CrossOrigin
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a single product by ID and aggregates content-based recommendations
     * from the Python ML microservice.
     * * @param id The ID of the product being viewed
     * @return A unified JSON response containing the product data and an array of recommended product IDs
     */
    @GetMapping("/api/store/products/{id}")
    public Object getProductWithRecommendations(@PathVariable int id) {

        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isEmpty()) {
            return Map.of("error", "Product not found.");
        }

        Product mainProduct = productOpt.get();

        // Prepare payload for the external recommendation engine
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("product_id", id);
        requestBody.put("num_recommendations", 2);

        // TODO: Externalize this URL to application.properties (e.g., ${ml.service.url})
        String pythonUrl = "https://ecommerce-python-ai-8h85.onrender.com/api/recommend";
        RestTemplate restTemplate = new RestTemplate();

        Object recommendations;
        try {
            // Request similar products via ML microservice
            Map<String, Object> pythonResponse = restTemplate.postForObject(pythonUrl, requestBody, Map.class);
            recommendations = (pythonResponse != null) ? pythonResponse.get("recommendations") : "No data";
        } catch (Exception e) {
            // Fallback mechanism: Ensure the core product page still loads if the ML service is offline
            recommendations = "Error: Recommendation engine is temporarily unavailable.";
        }

        // Aggregate database entity and external API data
        Map<String, Object> finalResponse = new HashMap<>();
        finalResponse.put("viewing_product", mainProduct);
        finalResponse.put("recommended_for_you", recommendations);

        return finalResponse;
    }

    /**
     * Retrieves the entire catalog of available products.
     */
    @GetMapping("/api/store/products")
    public java.util.List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}