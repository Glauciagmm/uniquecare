package com.uniquecare.controllers;

import com.uniquecare.models.Categories;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.FacilityRequest;
import com.uniquecare.repositories.CategoriesRepository;
import com.uniquecare.repositories.RoleRepository;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IFacilityService;
import com.uniquecare.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpSession;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/api/facility")
@CrossOrigin(origins="*")
public class FacilityController {

    private final IFacilityService facilityService;
    private final CategoriesRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public FacilityController(IFacilityService facilityService, CategoriesRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.facilityService = facilityService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
    }

    /**Encuentra un servicio cuando le pasas su ID - Todos los roles tienen permiso para hacerlo*/
    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @GetMapping("/single/{id}")
    public Facility findFacilityById(@PathVariable("id") Long id){
        return facilityService.findFacilityById(id);
    }

    /**Lista todos los servicios de la base de datos - works! */
    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<Facility>>getFacility(Authentication authentication, HttpSession session){
        if (authentication == null){
            System.out.println("Es necesario que hagas el login");
        }else{
            String username = authentication.getPrincipal().toString();
            System.out.println(username);
        }return ResponseEntity.ok().body(facilityService.getAllFacilities());
    }


    /**Crea un nuevo servicio y le pasa el user que lo ha creado (user logueado)- works! */
    @PreAuthorize("hasRole('FACILITY')")
    @PostMapping("/create")
<<<<<<< HEAD
    public ResponseEntity<?> addFacility(Authentication authentication, @RequestBody FacilityRequest facilityRequest) {
=======
    public ResponseEntity<Facility> addFacility(Authentication authentication, @RequestBody Facility facility, Categories category) {
>>>>>>> feature/ContractRequest
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/create").toUriString());
        Facility facility;

        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body("Es necesario que hagas el login");
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println(userDetails.getUsername());
            User user = userRepository.getByUsername(userDetails.getUsername());
<<<<<<< HEAD

            Categories category = categoryRepository.findById(facilityRequest.getCategoryId()).orElseThrow(RuntimeException::new);

            facility = new Facility();
            facility.setTitle(facilityRequest.getTitle());
            facility.setDescription(facilityRequest.getDescription());
            facility.setPricePerHour(facilityRequest.getPricePerHour());
=======
            Categories categories = categoryRepository.getCategoryByName(category.getName());
>>>>>>> feature/ContractRequest
            facility.setAssistant(user);
            facility.getCategories().add(category);
        }
        return ResponseEntity.created(uri).body(facilityService.addNewFacility(facility));
    }

    /**Edita un servicio de la base de datos - works! */
    @PreAuthorize("hasRole('FACILITY') or hasRole('ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<Facility> editFacility(@RequestBody Facility facility){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/create").toUriString());
        return ResponseEntity.created(uri).body(facilityService.updateFacility(facility));
    }

    /**Borra un servicio de la base de datos - works! */
    @PreAuthorize("hasRole('FACILITY') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFacilityById(@PathVariable Long id){
        facilityService.deleteFacilityById(id);
        return ResponseEntity.ok().build();
    }
}


