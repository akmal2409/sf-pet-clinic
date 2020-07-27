package com.akmal.sfpetclinic.controllers;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Pet;
import com.akmal.sfpetclinic.model.Visit;
import com.akmal.sfpetclinic.services.OwnerService;
import com.akmal.sfpetclinic.services.PetService;
import com.akmal.sfpetclinic.services.VisitService;
import com.sun.istack.Interned;
import net.bytebuddy.description.modifier.Ownership;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebInitParam;
import javax.validation.Valid;

@Controller
@RequestMapping("owners/{ownerId}/pets/{petId}")
public class VisitController {

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @ModelAttribute("visit")
    public Visit loadVisits(@PathVariable Long petId, Model model){

        Pet requestedPet = petService.findById(petId);
        model.addAttribute("pet", requestedPet);
        Visit visit = new Visit();
        requestedPet.getVisits().add(visit);
        visit.setPet(requestedPet);

        return visit;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/visits/new")
    public String createVisitForm(@PathVariable Long petId, Model model){

        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/visits/new")
    public String processCreateForm(@Valid Visit visit, BindingResult result,
                                    @PathVariable Long ownerId, @PathVariable Long petId){

        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        } else{
            Visit savedVisit = visitService.save(visit);

            return "redirect:/owners/" + ownerId;
        }
    }

    @GetMapping("/visits/{visitId}/edit")
    public String updateVisitForm(@PathVariable Long visitId, Model model){

        model.addAttribute("visit", visitService.findById(visitId));

        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/visits/{visitId}/edit")
    public String processUpdateForm(@Valid Visit visit, BindingResult result, Owner owner){

        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        } else{

            visitService.save(visit);

            return "redirect:/owners/" + owner.getId();
        }
    }
}
