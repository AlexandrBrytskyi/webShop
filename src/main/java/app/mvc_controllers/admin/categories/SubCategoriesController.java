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
@RequestMapping(value = "/shop/admin/subcategories/")
public class SubCategoriesController {

    @Autowired
    private CategoryRepo categoryRepo;


    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String showCategoriesPage(Model model) {
        Iterable<Categoriya> subcategories = categoryRepo.findAllByParentIsNotNull();
        Iterable<Categoriya> availableParents = categoryRepo.findAllByParentIsNull();
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("availableParents", availableParents);

        return "subCategoriesPage";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createCategory(Model model,
                                 @RequestParam(name = "subcategoryName") String categoryName,
                                 @RequestParam(name = "parentCategoryId") int parentCategoryId,
                                 RedirectAttributes redirectAttributes) {
        System.out.println("creating subcategory ");
        try {
            categoryRepo.save(new Categoriya(categoryRepo.findOne(parentCategoryId), categoryName));
            System.out.println("saved Subcategory");
            return "redirect:all";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "subCategoriesPage";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteCategory(Model model,
                                 @RequestParam(name = "subCategoryID") int catId,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryRepo.delete(catId);
            return "redirect:all";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "subCategoriesPage";
        }
    }
}
