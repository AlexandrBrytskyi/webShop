package app.mvc_controllers.admin.categories;

import app.model.items.Categoriya;
import app.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/shop/admin/categories/")
public class CategoriesController {

    @Autowired
    private CategoryRepo categoryRepo;


    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String showCategoriesPage(Model model) {
        Iterable<Categoriya> categories = categoryRepo.findAllByParentIsNull();
        model.addAttribute("categories", categories);

        return "categoriesPage";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createCategory(Model model,
                                 @RequestParam(name = "categoryName") String categoryName,
                                 RedirectAttributes redirectAttributes) {
        System.out.println("creating category ");
        try {
            categoryRepo.save(new Categoriya(categoryRepo.findOne(0), categoryName));
            System.out.println("saved category");
            return "redirect:all";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "categoriesPage";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteCategory(Model model,
                                 @RequestParam(name = "categoryID") int catId,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryRepo.delete(catId);
            return "redirect:all";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "categoriesPage";
        }
    }
}
