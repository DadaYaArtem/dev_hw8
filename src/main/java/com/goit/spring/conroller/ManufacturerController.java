package com.goit.spring.conroller;

import com.goit.spring.model.Manufacturer;
import com.goit.spring.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManufacturerController {

    @Autowired private ManufacturerService service;

    @GetMapping("/manufacturers")
    public String showManufacturersList(Model model){
        List<Manufacturer> listManufacturers = service.listAll();
        model.addAttribute("listManufacturers", listManufacturers);
        return "manufacturers";
    }

    @GetMapping("/manufacturers/new")
    public String showNewForm(Model model){
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturers_form";
    }

    @PostMapping("/manufacturers/save")
    public String saveManufacturer(Manufacturer manufacturer){
        service.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturers/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        try {
            Manufacturer manufacturer = service.get(id);
            model.addAttribute("manufacturer", manufacturer);
            return "manufacturers_form.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/manufacturers";
        }
    }

    @GetMapping("/manufacturers/delete/{id}")
    public String deleteManufacturer(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/manufacturers";
    }

}