package com.project3.Ecommerce.DTO;
/*The ProductDto is created to decouple the internal entity (Product) from the data that is exchanged with the client.
 It simplifies the data representation, improves security, and helps prevent 
performance and lazy-loading issues while transferring only the required information between layers. */
// / It serves as a bridge between different layers of the application (e.g., the controller and the service layers) and has several key purposes:

/*categoryId: Instead of directly using the Category entity (which could be complex), ProductDto has categoryId,
 which is a simpler, more concise representation of the relationship.
No Hibernate Annotations: DTOs are pure Java objects without JPA or database-related annotations,
 making them lightweight and focused on transferring data only. */
import lombok.Data;

@Data
public class ProductDto {
    
    private Long id;
    private String name;
    private int categoryId;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
