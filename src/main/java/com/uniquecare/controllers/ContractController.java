package com.uniquecare.controllers;

import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.ContractRequest;
import com.uniquecare.repositories.FacilityRepository;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.IContractService;
import com.uniquecare.services.IFacilityService;
import com.uniquecare.services.IUserService;
import com.uniquecare.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping ("/api")
@CrossOrigin(origins="*")
public class ContractController {

    private final IContractService contractService;
    private final IUserService userService;
    private final IFacilityService facilityService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FacilityRepository facilityRepository;

    @Autowired
    public ContractController(IContractService contractService, IUserService userService, IFacilityService facilityService) {
        this.contractService = contractService;
        this.userService = userService;
        this.facilityService = facilityService;
    }

    /**Lista todos los contractos de la base de datos, sus datos como fechas, assistente y cliente - works! */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contract")
    public ResponseEntity<List<Contract>> getContract() {
        return ResponseEntity.ok().body(contractService.findAllContracts());
    }


    /**Encuentra un contracto cuando le pasas su ID -  works! */
    @GetMapping("/contract/{id}")
    public Contract findContractById(@PathVariable("id") Long id) {
        return contractService.findContractById(id);
    }

    @GetMapping("/contract/list")
    public ResponseEntity<List<Contract>> getContract(Authentication authentication, HttpSession session) {
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
        } else {
            String username = authentication.getPrincipal().toString();
            System.out.println(username);
        }
        return ResponseEntity.ok().body(contractService.findAllContracts());
    }

    @PostMapping("/contract/add")
    public ResponseEntity<Contract> addContract(Authentication authentication, @RequestBody ContractRequest contractRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contract/add").toUriString());
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println(userDetails.getUsername());
            User user = userRepository.getByUsername(userDetails.getUsername());
            Facility facility = facilityService.findFacilityById(contractRequest.getFacility_id());
            Contract contract = new Contract();
            contract.setStart(contractRequest.getStart());
            contract.setFinish(contractRequest.getFinish());
            contract.setTotalPrice(contractRequest.getTotalPrice());
            contract.setFacility(facility);
            contract.setClient(user);
            return ResponseEntity.created(uri).body(contractService.addContract(contract));
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/contract/edit")
    public ResponseEntity<Contract> editContract(@RequestBody Contract contract){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contract/add").toUriString());
        return ResponseEntity.created(uri).body(contractService.updateContract(contract));
    }

    /**Borra un servicio de la base de datos - works! */
    @DeleteMapping("/contract/delete/{id}")
    public ResponseEntity<Void> deleteContractById(@PathVariable Long id){
        contractService.deleteContractById(id);
        return ResponseEntity.noContent().build();
    }

   /* @PostMapping(path = "/requestContract")
    public String requestFriendship(@RequestParam Long userId, Principal principal) {
        List<Contract> requests = contractRepository.findBySenderAndReceiverAndState(sessionUser, user, FriendshipRequest.State.OPEN);
        if (!requests.isEmpty()) {
            model.addAttribute("request", requests.get(0));
        } else {
            requests = friendshipRequestRepository.findBySenderAndReceiverAndState(user, sessionUser, FriendshipRequest.State.OPEN);
            if (!requests.isEmpty()) {
                model.addAttribute("request", requests.get(0));
            } else {
                model.addAttribute("request", null);
            }
    }*/

}
