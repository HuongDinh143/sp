package ra.sp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100,message = "Tên sản phẩm có tối đa 100 ký tự")
    private String productName;
    private String description;
    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0.01", inclusive = true, message = "Giá sản phẩm phải lớn hơn 0")
    private double price;
    @Size(max = 255,message = "Đường dẫn ảnh có tối đa 255 ký tự")
    private String imageUrl;
    private LocalDateTime createdAt;
    @NotNull(message = "Tên danh mục không được để trống")
    private int catId;
}
