package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import test.domain.Customers;
import test.service.ICustomersService;

import java.util.ArrayList;

@Controller
public class CustomersController {

    @Autowired
    private ICustomersService customersService;


    @RequestMapping(value = "/Customers")
    public String Customers(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "Customers";
    }

    @RequestMapping(value = "/Customers", method = RequestMethod.GET)
    public String allCustomers(Model model){
        model.addAttribute("customer", new Customers());
        model.addAttribute("allCustomers", (ArrayList<Customers>)customersService.getAllCustomers());
        return "Customers";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String addCustomerView(Model model){
        return "AddCustomer";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customers") Customers customers,
                               final RedirectAttributes redirectAttributes) {
        Customers customers1 = customersService.saveCustomer(customers);
        if (customers1 != null) {
            redirectAttributes.addFlashAttribute("saveCustomer", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveCustomer", "unsuccess");
        }

        return "redirect:/Customers";
    }


    @RequestMapping(value = "/Customers/Update/{id}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable("id") Long id,
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




    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable("id") Long id,
                              final RedirectAttributes redirectAttributes){

       if(customersService.deleteCustomer(id)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        return "redirect:/Customers";
    }





    @RequestMapping(value = "/customers/update", method = RequestMethod.POST)
    public String updateCustomer(@ModelAttribute("editCustomer") Customers editCustomer,
                                 final RedirectAttributes redirectAttributes) {
        if(customersService.editCustomer(editCustomer)!=null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
        return "redirect:/Customers";
    }
}