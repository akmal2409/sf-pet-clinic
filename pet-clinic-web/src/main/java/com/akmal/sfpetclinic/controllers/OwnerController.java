package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @GetMapping("/find")
    public String findOwnersForm(Model model){

        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @PostMapping("/find")
    public String findOwnersPost(@ModelAttribute Owner owner, Model model){

        Owner foundOwner = ownerService.findByLastName(owner.getLastName());

        model.addAttribute("owner", foundOwner);

        return "redirect:/owners/" + foundOwner.getId();
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){

        ModelAndView nav = new ModelAndView("owners/ownerDetails");
        nav.addObject(ownerService.findById(ownerId));
        return nav;
    }
}
