package com.mason.tacocloud02.controller;


import com.mason.tacocloud02.entry.Ingredient;
import com.mason.tacocloud02.entry.Taco;
import com.mason.tacocloud02.service.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mason
 * @Description TODO
 * @date 2022/6/28 21:30
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
//为了防止顾客输入非法字符，在控制器类中声明进行校验：具体来说，也就是在processController()方法和
//OrderController的processOrder()方法中
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

//    @ModelAttribute
//    public void addIngredientsToModel(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
//        Ingredient.Type[] types = Ingredient.Type.values();
//        for (Ingredient.Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//
//
//        }
//
//
//    }

    //使用数据库进行持久化
    @GetMapping
    public String showDesignForm(Model model){
      //  model.addAttribute("design",new Taco());
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i ->ingredients.add(i));
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));

        }
        return "design";

    }

    @PostMapping  //@Valid注解会告诉SpringMVC要对提交的Taco对象进行校验，而校验时机是在它绑定表单数据之后。调用processDesign()之前
    public String processDesign(@Valid Taco design, Errors errors){ //如果存在错误，那么这些错误的细节将会捕获到一个Errors对象中并传递给processDesign();
        if (errors.hasErrors())
            return "design";
        log.info("Process design: " + design);
        //进行重定向
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
