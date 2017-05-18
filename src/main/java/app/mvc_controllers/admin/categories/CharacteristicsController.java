package app.mvc_controllers.admin.categories;

import app.model.items.Categoriya;
import app.model.items.characteristics.CharacteristicType;
import app.model.items.characteristics.CharacteristicValue;
import app.model.items.characteristics.Charecteristic;
import app.repository.CategoryRepo;
import app.repository.CharacteristicRepo;
import app.repository.CharacteristicValuesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/shop/admin/characteristics/")
public class CharacteristicsController {


    @Autowired
    private CharacteristicRepo charecteristicRepo;

    @Autowired
    private CharacteristicValuesRepo characteristicValuesRepo;


    @Autowired
    private CategoryRepo categoryRepo;


    @RequestMapping(value = "{subcategoryId}", method = RequestMethod.GET)
    public String showCharacteristicsPage(Model model,
                                          @PathVariable(name = "subcategoryId", required = true) int subcategoryId) {

        Iterable<Charecteristic> charecteristics = charecteristicRepo.findAllByCategoriyaId(subcategoryId);

        model.addAttribute("categoryName", categoryRepo.findOne(subcategoryId).getName());
        model.addAttribute("characteristics", charecteristics);
        model.addAttribute("subcategoryId", subcategoryId);

        return "characteristicsPage";
    }

    @RequestMapping(value = "create/{subcategoryId}", method = RequestMethod.POST)
    public String createCategory(Model model,
                                 @PathVariable(name = "subcategoryId") int subCategoryId,
                                 @RequestParam(name = "characteristicName") String characteristicName,
                                 @RequestParam(name = "characteristicType") String characteristicType,
                                 @RequestParam(name = "showInFilterCharacteristic") String showInFilterCharacteristic,
                                 RedirectAttributes redirectAttributes) {
        System.out.println("creating characteristic ");
        try {
            charecteristicRepo.save(new Charecteristic(characteristicName, CharacteristicType.valueOf(characteristicType),
                    categoryRepo.findOne(subCategoryId), Boolean.valueOf(showInFilterCharacteristic)));
            System.out.println("saved characteristic");
            return showCharacteristicsPage(model, subCategoryId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "characteristicsPage";
        }
    }

    @RequestMapping(value = "delete/{subcategoryId}", method = RequestMethod.POST)
    public String deleteCharacteristic(Model model,
                                       @PathVariable(name = "subcategoryId") int subCategoryId,
                                       @RequestParam(name = "characteristicId") int characteristicId,
                                       RedirectAttributes redirectAttributes) {
        try {
            charecteristicRepo.delete(characteristicId);
            return showCharacteristicsPage(model, subCategoryId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "characteristicsPage";
        }
    }


    @RequestMapping(value = "values/{subcategoryId}/{charakteristicId}", method = RequestMethod.GET)
    public String showCharacteristicsValuesPage(Model model,
                                                @PathVariable(name = "subcategoryId", required = true) int subcategoryId,
                                                @PathVariable(name = "charakteristicId", required = true) int characteristicId) {

        Charecteristic charecteristic = charecteristicRepo.findOne(characteristicId);
        model.addAttribute("parent", charecteristic);
        Categoriya subcategory = categoryRepo.findOne(subcategoryId);
        Categoriya category = subcategory.getParent();
        model.addAttribute("category", category);
        model.addAttribute("subcategory", subcategory);

        Iterable<CharacteristicValue> characteristicValues =
                characteristicValuesRepo.findAllByCharecteristicIdOrderByCharecteristicNameAsc(characteristicId);

        model.addAttribute("characteristicValues", characteristicValues);

        return "characteristicsValuesPage";
    }

    @RequestMapping(value = "values/create/{subcategoryId}/{characteristicId}", method = RequestMethod.POST)
    public String createCharacteristicValue(Model model,
                                            @PathVariable(name = "subcategoryId", required = true) int subcategoryId,
                                            @PathVariable(name = "characteristicId", required = true) int characteristicId,
                                            @RequestParam(name = "value") String value,
                                            RedirectAttributes redirectAttributes) {

        System.out.println("creating CharacteristicValue ");
        try {
            characteristicValuesRepo.save(
                    new CharacteristicValue(
                            charecteristicRepo.findOne(characteristicId),
                            categoryRepo.findOne(subcategoryId),
                            value));
            System.out.println("saved CharacteristicValue");
            return showCharacteristicsValuesPage(model, subcategoryId, characteristicId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "characteristicsValuesPage";
        }
    }

    @RequestMapping(value = "values/delete/{subcategoryId}/{characteristicId}/{characteristicValueId}", method = RequestMethod.POST)
    public String deleteCharacteristicValue(Model model,
                                            @PathVariable(name = "subcategoryId", required = true) int subcategoryId,
                                            @PathVariable(name = "characteristicId", required = true) int characteristicId,
                                            @PathVariable(name = "characteristicValueId", required = true) int characteristicValueId,
                                            RedirectAttributes redirectAttributes) {

        try {
            characteristicValuesRepo.delete(characteristicValueId);
            return showCharacteristicsValuesPage(model, subcategoryId, characteristicId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "characteristicsValuesPage";
        }
    }
}
