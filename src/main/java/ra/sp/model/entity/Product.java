package ra.sp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "product_name", columnDefinition = "varchar(100)", nullable = false)
    private String productName;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price", columnDefinition = "double check(price>0)")
    private double price;
    @Column(name = "image_url", columnDefinition = "varchar(255)")
    private String imageUrl;
    @Column(name = "status")
    private boolean status = true;
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Category category = new Category();


}
