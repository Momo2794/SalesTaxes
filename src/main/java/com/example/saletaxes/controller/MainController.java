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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    private List<Product> chosenProducts = new ArrayList<>();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String showAllProducts(Model model) {
        chosenProducts.clear();

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
        Double saletax = 0.00;
        Double saletaxtotal = 0.00;
        Double total = 0.00;

        DecimalFormat df = new DecimalFormat(".00");

        chosenProducts.add(product);

        for (Product p : chosenProducts) {
            if ((p.getImporttax() == false) && (p.getGeneraltax() == false)) {
                saletax = 0.00;

                amount = p.getProductprice();
                amount = (Math.round(amount*100)/100.00);
            } else if ((p.getImporttax() == false) && (p.getGeneraltax() == true)) {
                saletax = (p.getProductprice()/100*10);
                saletax = (Math.round(saletax*100)/100.00);

                amount = p.getProductprice() + saletax;
                amount = (Math.round(amount*100)/100.00);
            } else if ((p.getImporttax() == true) && (p.getGeneraltax() == false)) {
                saletax = (p.getProductprice()/100*5);
                saletax = (Math.round(saletax*100)/100.00);

                amount = p.getProductprice() + saletax;
                amount = (Math.round(amount*100)/100.00);
            } else if ((p.getImporttax() == true) && (p.getGeneraltax() == true)) {
                saletax = (p.getProductprice()/100*5);
                saletax = (Math.round(saletax*100)/100.00);

                amount = p.getProductprice() + saletax;
                amount = (Math.round(amount*100)/100.00);

                saletaxtotal += saletax;

                saletax = (amount/100*10);
                saletax = (Math.round(saletax*100)/100.00);

                amount = amount + saletax;
                amount = (Math.round(amount*100)/100.00);
            }

            total += amount;
            saletaxtotal += saletax;
            output.add("1 " + p.getProductname() + ": " + amount);
        }

        saletaxtotal = (Math.round(saletaxtotal*100)/100.00);
        total = (Math.round(total*100)/100.00);

        output.add("Sales Taxes: " + saletaxtotal);
        output.add("Total: " + total);

        modelAndView.addObject("productList", productService.getAllProducts());
        modelAndView.addObject("chosedPrList", output);
        return modelAndView;
    }


}
