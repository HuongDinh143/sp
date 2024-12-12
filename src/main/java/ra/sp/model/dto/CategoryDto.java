package ra.sp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    @NotBlank(message = "Tên danh mục không được bỏ trống")
    @Size(max = 50,message = "Tên danh mục có tối đa 50 ký tự")
    private String catName;
    private String catDesc;

}
