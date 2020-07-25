package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Pet;
import com.akmal.sfpetclinic.model.PetType;
import com.akmal.sfpetclinic.services.OwnerService;
import com.akmal.sfpetclinic.services.PetService;
import com.akmal.sfpetclinic.services.PetTypeService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {

        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetType(){
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return this.ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder binder){
        binder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String createPet(Model model, Owner owner){

        Pet pet = new Pet();
        owner.addPet(pet);
        model.addAttribute("pet", pet);

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreatingForm(@Valid Pet pet, BindingResult result, Owner owner,
                                      Model model){

        if(result.hasErrors()){

            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        } else {
            owner.addPet(pet);
            this.petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model){

        model.addAttribute("pet", petService.findById(petId));

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model){

        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);

            return "pets/createOrUpdatePetForm";
        } else{
            owner.addPet(pet);
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }
}
