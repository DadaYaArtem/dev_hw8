package com.goit.spring.conroller;

import com.goit.spring.model.Manufacturer;
import com.goit.spring.service.ManufacturerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService service;

    @GetMapping("/manufacturer")
    public String userMainPage(Model model) {
        return "manuf/manuf_main";
    }

    @GetMapping("/manufacturer/manufacturer_info")
    public String showUserById(Model model, HttpServletRequest req) {
        try {
            model.addAttribute("manufacturer", service.get(Long.valueOf(req.getParameter("manufacturerId"))));
            return "manuf/getManufById";
        } catch (Exception e) {
            return "redirect:/manufacturer/list";
        }
    }

    @GetMapping("/manufacturer/list")
    public String showManufacturersList(Model model){
        List<Manufacturer> listManufacturers = service.listAll();
        model.addAttribute("listManufacturers", listManufacturers);
        model.addAttribute("listSize", listManufacturers.size());
        return "manuf/getAllMunufs";
    }

    @PostMapping("/manufacturer/new")
    public String showNewForm(Model model){
        model.addAttribute("manufacturer", new Manufacturer());
        return "manuf/manuf_form";
    }

    @PostMapping("/manufacturer/save")
    public String saveManufacturer(Manufacturer manufacturer){
        service.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturer/edit/{id}")
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

    @GetMapping("/manufacturer/delete/{id}")
    public String deleteManufacturer(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/manufacturers";
    }

}