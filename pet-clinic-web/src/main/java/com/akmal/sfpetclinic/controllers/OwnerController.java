package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Visit;
import com.akmal.sfpetclinic.services.OwnerService;
import com.akmal.sfpetclinic.services.PetService;
import com.akmal.sfpetclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;
    private final PetService petService;

    public OwnerController(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwnersForm(Model model){

        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping
    public String processOwnerForm(Owner owner, BindingResult result, Model model){

        // if person sends empty last name then list of all owners will be displayed
        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        /*
        if(owner.getLastName().isEmpty()){
            model.addAttribute("owners", ownerService.findAll());

            return "owners/ownersList";
        }
         */

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if(results.isEmpty()){
            //Single Owner
            result.rejectValue("lastName", "notFound", "not found");

            return "owners/findOwners";
        } else if(results.size() == 1){

            Owner foundOwner = results.get(0);

            return "redirect:/owners/" + foundOwner.getId();
        } else {
            //Multiple owners

            model.addAttribute("owners", results);

            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){

        ModelAndView nav = new ModelAndView("owners/ownerDetails");
        nav.addObject(ownerService.findById(ownerId));
        return nav;
    }

    @GetMapping("/new")
    public String createOwner(Model model){

        model.addAttribute("owner", new Owner());

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreatingForm(@Valid Owner owner, BindingResult result){

        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        } else{

            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String getUpdateForm(@PathVariable Long ownerId, Model model){

        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@PathVariable Long ownerId, @Valid Owner owner, BindingResult result){

        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        } else{

            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }

    }


}
