package com.example.saletaxes.controller;

import com.example.saletaxes.entity.Product;
import com.example.saletaxes.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    private List<Product> chosenProducts = new ArrayList<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String showAllProducts(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("chosedPrList", chosenProducts);
        return "index";
    }

    @RequestMapping(value = "/chosen/{id}", method = RequestMethod.GET)
    private ModelAndView showChosenProduct(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        Product product = productService.getProductByID(id);
        List<String> output = new ArrayList<>();

        Double amount = 0.00;

        chosenProducts.add(product);

        for (Product p : chosenProducts) {
            output.add("1 " + p.getProductname() + ": " + p.getProductprice());
        }

        modelAndView.addObject("productList", productService.getAllProducts());
        modelAndView.addObject("chosedPrList", output);
        return modelAndView;
    }


}
