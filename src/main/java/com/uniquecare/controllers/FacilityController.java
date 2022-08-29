package com.uniquecare.controllers;

import com.uniquecare.Exceptions.ResourceNotFoundException;
import com.uniquecare.models.Categories;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.FacilityRequest;
import com.uniquecare.repositories.CategoriesRepository;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IFacilityService;
import com.uniquecare.services.IUserService;
import com.uniquecare.services.UserDetailsImpl;
import org.springframework.http.HttpStatus;
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
    private final IUserService userService;


    public FacilityController(IFacilityService facilityService, CategoriesRepository categoryRepository, UserRepository userRepository, IUserService userService) {
        this.facilityService = facilityService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    /**Encuentra un servicio cuando le pasas su ID - Todos los roles tienen permiso para hacerlo*/
    //@PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @GetMapping("/single/{id}")
    public Facility findFacilityById(@PathVariable("id") Long id){
        return facilityService.findFacilityById(id);
    }

    /**Lista todos los servicios de la base de datos - works! */

    //@PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")

    @GetMapping("/list")
    public ResponseEntity<List<Facility>>getFacility(){
          return ResponseEntity.ok().body(facilityService.getAllFacilities());
    }

  /*  //Filtra x ubicacion!
    @GetMapping ("/ubication/{city}")
    public ResponseEntity<List<Facility>> findFacilitiesByCity(Authentication authentication,@PathVariable String city) {
        if (!userRepository.existsByCity(city)) {
            throw new ResourceNotFoundException("Not found facilities  with this assistant city = " + city);
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.getByUserCity(userDetails.getCity());
        if(user.getCity() =="Barcelona") {
            return ResponseEntity.ok().body(facilityService.getAllFacilitiesByAssistantCity(city));
        }else  if (city=="Valencia"){
            return ResponseEntity.ok().body(facilityService.getAllFacilitiesByAssistantCity(city));
        }else  if (city=="Madrid"){
            return ResponseEntity.ok().body(facilityService.getAllFacilitiesByAssistantCity(city));
        }
        List<Facility> facilities =facilityService.getAllFacilities();
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }*/
    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @GetMapping("/{assistantId}")
    public ResponseEntity<List<Facility>> getAllFacilitiesByAssistantId(@PathVariable(value = "assistantId") Long assistantId) {
        if (!userRepository.existsById(assistantId)) {
            throw new ResourceNotFoundException("Not found facilities  with this assistant id = " + assistantId);
        }
        List<Facility> facilities =facilityService.getAllFacilitiesByAssistantId(assistantId);
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }




    /**Crea un nuevo servicio y le pasa el user que lo ha creado (user logueado)- works!

    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")

    @PreAuthorize("hasRole('FACILITY')")

    @PostMapping("/create")
    public ResponseEntity<Facility> addFacility(Authentication authentication, @RequestBody FacilityRequest facilityRequest,
                                                Categories category,Facility facility) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/create").toUriString());
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body(facility);
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println(userDetails.getUsername());
            User user = userRepository.getByUsername(userDetails.getUsername());
            category = categoryRepository.findById(facilityRequest.getCategoryId()).orElseThrow(RuntimeException::new);

            facility = new Facility();
            facility.setTitle(facilityRequest.getTitle());
            facility.setDescription(facilityRequest.getDescription());
            facility.setPricePerHour(facilityRequest.getPricePerHour());
            facility.getCategories().add(category);
            facility.setAssistant(user);
           /*facility.getCategories().add()
            //System.out.println(username);
        }
        return ResponseEntity.created(uri).body(facilityService.addNewFacility(facility));
    }
    */
    @PreAuthorize("hasRole('FACILITY')")
    @PostMapping("/create")
    public ResponseEntity<Facility> addFacility(Authentication authentication, @RequestBody FacilityRequest facilityRequest,
                                                Categories category,Facility facility) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/create").toUriString());
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body(facility);
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userRepository.getByUsername(userDetails.getUsername());
            category = categoryRepository.findById(facilityRequest.getCategoryId()).orElseThrow(RuntimeException::new);
            facility = new Facility();
            facility.setTitle(facilityRequest.getTitle());
            facility.setDescription(facilityRequest.getDescription());
            facility.setPricePerHour(facilityRequest.getPricePerHour());
            facility.setAssistant(user);
            facility.getCategories().add(category);
        }
        return ResponseEntity.created(uri).body(facilityService.addNewFacility(facility));
    }



    /**Edita un servicio de la base de datos - works! */
    @PreAuthorize("hasRole('FACILITY') or hasRole('ADMIN')")
    @PutMapping("/edit")
    public ResponseEntity<Facility> editFacility(@RequestBody Facility facility){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/facility/edit").toUriString());
        return ResponseEntity.created(uri).body(facilityService.updateFacility(facility));
    }

    /**Borra un servicio de la base de datos - works! */
    @PreAuthorize("hasRole('USER') or hasRole('FACILITY') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFacilityById(@PathVariable Long id){
        facilityService.deleteFacilityById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/assistant/")
    public List<Facility> userFacilities (Authentication authentication){
        User user = userService.getByUsername(authentication.getName());
        return facilityService.findAllByAssistant(user);
    }

}


