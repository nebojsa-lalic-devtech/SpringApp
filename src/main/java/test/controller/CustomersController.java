package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import test.domain.Customers;
import test.service.ICustomersService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class CustomersController {

    @Autowired
    private ICustomersService customersService;


    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String allCustomers(Model model){
        model.addAttribute("customer",new Customers());
        model.addAttribute("allCustomers",(ArrayList<Customers>)customersService.getAllCustomers());
        return "customers";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String addCustomerView(Model model){
        model.addAttribute("customers", new Customers());
        return "addCustomer";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String addCustomer(@Valid @ModelAttribute("customers") Customers customers, BindingResult bindingResult,
                              final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        }
        Customers customers1 = customersService.saveCustomer(customers);
        if (customers1 != null) {
            redirectAttributes.addFlashAttribute("saveCustomer", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveCustomer", "unsuccess");
        }

        return "redirect:/customers";
    }

    @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.GET)
    public String updateCustomerView(@PathVariable("id") Long id,
                                   final RedirectAttributes redirectAttributes,
                                   Model model){

        Customers editCustomer = customersService.findCustomer(id);
        if(editCustomer!=null){
            model.addAttribute("editCustomer", editCustomer);
            return "editCustomer";
        }else {
            redirectAttributes.addFlashAttribute("status", "notfound");
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.POST)
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("editCustomer") Customers editCustomer,
                                 final RedirectAttributes redirectAttributes) {
        if(customersService.editCustomer(id,editCustomer)!=null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable("id") Long id,
                                 final RedirectAttributes redirectAttributes){
        if(customersService.deleteCustomer(id)) {
            redirectAttributes.addFlashAttribute("deletion", "success");
        } else {
            redirectAttributes.addFlashAttribute("deletion", "unsuccess");
        }
        return "redirect:/customers";
    }
}