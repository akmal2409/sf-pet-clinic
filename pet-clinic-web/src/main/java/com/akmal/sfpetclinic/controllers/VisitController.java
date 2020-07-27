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


}
