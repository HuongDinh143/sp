package ra.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.sp.model.dto.CategoryDto;
import ra.sp.model.dto.CategoryUpdateDto;
import ra.sp.model.entity.Category;
import ra.sp.service.ICategoryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categoryController")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/findAll")
    public String findAll(Model model, RedirectAttributes redirectAttributes) {
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("listCategories", listCategories);
        if (listCategories.isEmpty()) {
            model.addAttribute("errorMessage", "Danh mục sản phẩm rỗng");
        }
        return "/category/list";
    }

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "/category/create";
    }

    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại!");
            return "redirect:/categoryController/initCreate";
        }

        try {
            categoryService.create(categoryDto);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo danh mục thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi tạo danh mục: " + e.getMessage());
        }

        return "redirect:/categoryController/findAll";
    }


    @GetMapping("/initUpdate")
    public String initUpdate(Model model, int catId) {
        Category category = categoryService.findById(catId);
        model.addAttribute("category", category);
        return "/category/update";
    }

    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryUpdateDto request,
                                 Model model,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
            return "redirect:/categoryController/initUpdate?catId=" + request.getCatId();
        }
        Category isExitingName = categoryService.findByName(request.getCatName());
        if (isExitingName != null && !(isExitingName.getCatId() == (request.getCatId()))) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên danh mục đã tồn tại");
            return "redirect:/categoryController/initUpdate?catId=" + request.getCatId();
        }
        try {
            categoryService.update(request);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");
            return "redirect:/categoryController/findAll";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật thất bại");

        }
        return "redirect:/categoryController/findAll";
    }


}
