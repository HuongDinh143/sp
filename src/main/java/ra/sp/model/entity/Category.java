package ra.sp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @Column(name="cat_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int catId;
    @Column(name="cat_name",columnDefinition = "varchar(50)",nullable = false,unique = true)
    private String catName;
    @Column(name="cat_desc",columnDefinition = "text")
    private String catDesc;
    @Column(name="cat_status")
    private boolean catStatus = true;
    @OneToMany(mappedBy = "category")
    List<Product> listProduct = new ArrayList<>();
}
