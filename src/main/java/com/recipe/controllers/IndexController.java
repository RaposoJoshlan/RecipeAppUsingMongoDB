package com.recipe.controllers;

import com.recipe.domain.Category;
import com.recipe.domain.UnitOfMeasure;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.UnitOfMeasureRepository;
import com.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index Page");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
