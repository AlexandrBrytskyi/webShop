package app.mvc_controllers.item;

import app.model.items.*;
import app.model.items.characteristics.CharacteristicValue;
import app.model.items.characteristics.Charecteristic;
import app.model.users.Customer;
import app.model.users.User;
import app.mvc_controllers.CustomerBasketsSessionsContext;
import app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shop/item/")
public class ItemManagingController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CharacteristicValuesRepo characteristicValuesRepo;


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private CustomerBasketsSessionsContext customerBasketsSessionsContext;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showItem(Model model,
                           @RequestParam(name = "itemId") int itemId,
                           HttpServletRequest request) {


        Item item = itemRepository.findOne(itemId);
        model.addAttribute("item", item);
        app.model.users.User user = getUserFromRequest(request);
        System.out.println(user);
        boolean o = request.isUserInRole("ADMIN");
        model.addAttribute("isAdmin", o);
        if (o) {
            model.addAttribute("canComment", true);
        } else {
            Customer customer = customerBasketsSessionsContext.getCustomer(request);
            boolean canCom = false;
            if (customer != null) {
                canCom = purchaseRepo.findByCustomerBoughtPhoneAndStateAndId(customer.getPhone(),
                        PurchaseState.SUBMITTED, itemId) != null;
            }
            model.addAttribute("canComment", canCom);
        }


        model.addAttribute("user", user);

        return "itemPage";
    }


    @RequestMapping(value = "picture", method = RequestMethod.GET, produces = {MediaType.ALL_VALUE})
    public
    @ResponseBody
    byte[] loadPicture(@RequestParam(name = "itemId") int itemId) {
        System.out.println("asked for picture");
        return itemRepository.findOne(itemId).getPicture();
    }

    private app.model.users.User getUserFromRequest(HttpServletRequest request) {
        try {
            return userRepo.findOne(request.getUserPrincipal().getName());
        } catch (Exception e) {
            return null;
        }
    }


    @RequestMapping(value = "createComment", method = RequestMethod.POST)
    public String addComment(Model model,
                             @RequestParam(name = "itemId") int itemId,
                             @RequestParam(name = "comment") String comment,
                             HttpServletRequest request) {

        Item item = itemRepository.findOne(itemId);

        app.model.users.User user = getUserFromRequest(request);

        Comment comm = new Comment();
        comm.setContent(comment);
        comm.setDate(new Date());
        comm.setItem(item);
        comm.setOwner(user);


        item.getComments().add(comm);

        commentRepo.save(comm);
        userRepo.save(user);
        Item itemm = itemRepository.save(item);

        System.out.println("saved comment");
        System.out.println(itemm.getComments());


        return "redirect:?itemId=" + item.getId();
    }


    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String showCreateItemView(Model model,
                                     @RequestParam(name = "subCategoryId") int subCategoryId,
                                     RedirectAttributes redirectAttributes,
                                     HttpServletRequest request) {

        Categoriya subcategory = categoryRepo.findOne(subCategoryId);
        Categoriya category = subcategory.getParent();

        model.addAttribute("subcategory", subcategory);
        model.addAttribute("category", category);
        model.addAttribute("isCreating", true);

        List<CharacteristicWithAvalableValues> characterVsValues = new ArrayList<>();
        for (Charecteristic charecteristic : subcategory.getCharecteristics()) {
            characterVsValues.add(new CharacteristicWithAvalableValues(
                    charecteristic,
                    characteristicValuesRepo.findAllByCharecteristicIdOrderByCharecteristicNameAsc(charecteristic.getId())
            ));
        }

        model.addAttribute("characterVsValues", characterVsValues);

        return "createChangeItemPage";
    }


    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createItem(Model model,
                             @RequestParam(name = "subCategoryId") int subCategoryId,
                             @RequestParam(name = "picture") MultipartFile picture,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "price") String price,
                             @RequestParam(name = "amountAvailable") int amountAvailable,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        try {
            Item item = new Item(
                    categoryRepo.findOne(subCategoryId),
                    picture.getBytes(),
                    description,
                    new BigDecimal(price),
                    amountAvailable
            );
            item.setCharacteristicValues(new ArrayList<>());

            Map<String, String[]> params = request.getParameterMap();
            for (Map.Entry<String, String[]> stringEntry : params.entrySet()) {
                String key = stringEntry.getKey();
                if (key.endsWith("__select")) {
                    int characteristicId = Integer.valueOf(key.replace("__select", ""));
                    int characteristicValueId = Integer.valueOf(stringEntry.getValue()[0].replace("__charact_val_id", ""));
                    CharacteristicValue charct = characteristicValuesRepo.findOne(characteristicValueId);
                    charct.getItems().add(item);
                    item.getCharacteristicValues().add(charct);
                    characteristicValuesRepo.save(charct);
                }
            }

            Basket basket = new Basket();

            int id = itemRepository.save(item).getId();

            System.out.println("creating item ");
//            adminService.createCategory(0, categoryName);
            System.out.println("saved item");
            redirectAttributes.addAttribute("itemId", id);
            return "redirect:";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "createChangeItemPage";
        }
    }

    @RequestMapping(value = "delete/{itemId}", method = RequestMethod.POST)
    public String deleteItem(Model model,
                             @PathVariable(name = "itemId") int itemId,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        try {
            app.model.users.User user = getUserFromRequest(request);
            itemRepository.delete(itemId);
            return "redirect:../main";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "createChangeItemPage";
        }
    }


    @RequestMapping(value = "update/{itemId}", method = RequestMethod.GET)
    public String showUpdateView(Model model,
                                 @PathVariable(name = "itemId") int itemId,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
        try {
            app.model.users.User user = getUserFromRequest(request);
            model.addAttribute("user", user);
            if (!request.isUserInRole("ADMIN")) return "redirect:../" + itemId;

            Item item = itemRepository.findOne(itemId);
            model.addAttribute("item", item);


            Categoriya subcategory = item.getCategoriya();
            Categoriya category = subcategory.getParent();
            model.addAttribute("category", category);
            model.addAttribute("subcategory", subcategory);


            List<CharacteristicWithAvalableValues> characterVsValues = new ArrayList<>();
            for (Charecteristic charecteristic : subcategory.getCharecteristics()) {
                characterVsValues.add(new CharacteristicWithAvalableValues(
                        charecteristic,
                        characteristicValuesRepo.findAllByCharecteristicIdOrderByCharecteristicNameAsc(charecteristic.getId())
                ));
            }

            model.addAttribute("characterVsValues", characterVsValues);
            model.addAttribute("isChanging", true);

            return "createChangeItemPage";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "createChangeItemPage";
        }
    }


    @RequestMapping(value = "update/{itemId}", method = RequestMethod.POST)
    public String updateItem(Model model,
                             @PathVariable(name = "itemId") int itemId,
                             @RequestParam(name = "subCategoryId") int subCategoryId,
                             @RequestParam(name = "picture") MultipartFile picture,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "price") String price,
                             @RequestParam(name = "amountAvailable") int amountAvailable,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        try {
            Item item = itemRepository.findOne(itemId);
            byte[] bytes = picture.getBytes();
            if (bytes != null) item.setPicture(bytes);
            item.setDescription(description);
            item.setPrice(new BigDecimal(price));
            item.setAmountAvailable(amountAvailable);

            item.setCharacteristicValues(new ArrayList<>());

            Map<String, String[]> params = request.getParameterMap();
            for (Map.Entry<String, String[]> stringEntry : params.entrySet()) {
                String key = stringEntry.getKey();
                if (key.endsWith("__select")) {
                    int characteristicId = Integer.valueOf(key.replace("__select", ""));
                    int characteristicValueId = Integer.valueOf(stringEntry.getValue()[0].replace("__charact_val_id", ""));
                    CharacteristicValue charct = characteristicValuesRepo.findOne(characteristicValueId);
                    charct.getItems().add(item);
                    item.getCharacteristicValues().add(charct);
                    characteristicValuesRepo.save(charct);
                }
            }

            int id = itemRepository.save(item).getId();
            redirectAttributes.addAttribute("itemId", id);
            return "redirect:../";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "createChangeItemPage";
        }
    }


    @RequestMapping(value = "filtered", method = RequestMethod.GET)
    public String showFilteredItems(
            @RequestParam(name = "categoryId") int categoryId,
            @RequestParam(name = "priceAsc") boolean priceAsc,
            @RequestParam(name = "page") int page,
            Model model,
            HttpServletRequest request) {

        model.addAttribute("priceAsc", priceAsc);
        model.addAttribute("page", page);

        List<CharacteristicValue> characteristicValues = new ArrayList<>();

        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : params.entrySet()) {
            String key = stringEntry.getKey();
            if (key.endsWith("__select")) {
                int characteristicId = Integer.valueOf(key.replace("__select", ""));
                int characteristicValueId = Integer.valueOf(stringEntry.getValue()[0].replace("__charact_val_id", ""));
                model.addAttribute(characteristicValueId + "__charact_val_id", true);
                if (characteristicValueId != -1) {
                    CharacteristicValue cv = characteristicValuesRepo.findOne(characteristicValueId);
                    characteristicValues.add(cv);
                }
            }
        }


        Iterable<Item> results = null;

        if (!characteristicValues.isEmpty()) {
            System.out.println("finding items for Characteristic values " + characteristicValues);
            if (priceAsc) {
                results = itemRepository.findAllByAmountAvailableGreaterThanAndCharacteristicValuesInOrderByPriceAsc(0, characteristicValues, new PageRequest(page, 20));
            } else {
                results = itemRepository.findAllByAmountAvailableGreaterThanAndCharacteristicValuesInOrderByPriceDesc(0, characteristicValues, new PageRequest(page, 20));
            }

            System.out.println("Found " + results);

        } else {
            if (priceAsc) results =
                    itemRepository.findAllByCategoriyaIdAndAmountAvailableGreaterThanOrderByPriceAsc(categoryId, 0, new PageRequest(page, 20));
            else results =
                    itemRepository.findAllByCategoriyaIdAndAmountAvailableGreaterThanOrderByPriceDesc(categoryId, 0, new PageRequest(page, 20));
        }
        model.addAttribute("results", results);
        model.addAttribute("category", categoryRepo.findOne(categoryId));

        User user = getUserFromRequest(request);
        boolean isAdmin = request.isUserInRole("ADMIN");
        model.addAttribute("isAdmin", isAdmin);

        model.addAttribute("user", user);


        return "filteredItems";
    }


}
