package com.akmal.sfpetclinic.bootstrap;

import com.akmal.sfpetclinic.model.Owner;
import com.akmal.sfpetclinic.model.Vet;
import com.akmal.sfpetclinic.services.OwnerService;
import com.akmal.sfpetclinic.services.VetService;
import com.akmal.sfpetclinic.services.map.OwnerMapService;
import com.akmal.sfpetclinic.services.map.VetMapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerMapService();
        vetService = new VetMapService();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Shrek");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Pinkman");

        vetService.save(vet1);

        System.out.println("Loaded Vets....");
    }
}
