package com.uniquecare.controllers;

import com.uniquecare.Exceptions.ContractException;
import com.uniquecare.models.Contract;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.ContractRequest;
import com.uniquecare.repositories.ContractRepository;
import com.uniquecare.repositories.FacilityRepository;
import com.uniquecare.repositories.UserRepository;
import com.uniquecare.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
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
    ContractRepository contractRepository;

    @Autowired
    FacilityRepository facilityRepository;

    @Autowired
    public ContractController(IContractService contractService, IUserService userService, IFacilityService facilityService) {
        this.contractService = contractService;
        this.userService = userService;
        this.facilityService = facilityService;
    }


    @PostMapping( "/requestcontract")
    public ResponseEntity<?> requestContract(Authentication authentication, @RequestBody ContractRequest contractRequest) throws ContractException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/requestcontract").toUriString());
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body("Es necesario que hagas el login");
        }

        if(contractService.existsByClientAndFacilityAndStartAndFinish(contractRequest)) {
            return ResponseEntity.badRequest().body("The request already exists");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        return ResponseEntity.created(uri).body(contractService.createContractRequest(contractRequest));
    }

    @PutMapping("/acceptcontract/{id}")
    public ResponseEntity<?> acceptContract (Authentication authentication,@PathVariable Long id) throws ContractException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/acceptedcontract").toUriString());
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body("Es necesario que hagas el login");
        }
        Contract contract = contractService.findContractById(id);
        contract.setState(Contract.State.ACCEPTED);
        return ResponseEntity.created(uri).body(contractService.addContract(contract));
    }

    @PutMapping("/declinecontract/{id}")
    public ResponseEntity<?> declineContract(Authentication authentication,@PathVariable Long id) throws ContractException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/acceptedcontract").toUriString());
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body("Es necesario que hagas el login");
        }
        Contract contract = contractService.findContractById(id);
        contract.setState(Contract.State.DECLINED);
        return ResponseEntity.created(uri).body(contractService.addContract(contract));
    }

    /**Lista todos los contractos de la base de datos, sus datos como fechas, assistente y cliente - works! */
    @GetMapping("/contract/list")
    public ResponseEntity<?> getContract(Authentication authentication) throws ContractException{
        if (authentication == null) {
            System.out.println("Es necesario que hagas el login");
            return ResponseEntity.badRequest().body("Es necesario que hagas el login");
        } else {
            String username = authentication.getPrincipal().toString();
            System.out.println(username);
        }
        return ResponseEntity.ok().body(contractService.findAllContracts());
    }

    /**Encuentra un contracto cuando le pasas su ID -  works! */
    @GetMapping("/contract/{id}")
    public Contract findContractById(@PathVariable("id") Long id) {
        return contractService.findContractById(id);
    }

    @PutMapping("/contract/edit/{id}")
    public ResponseEntity<Contract> editContract(@RequestBody ContractRequest contractRequest) throws ContractException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contract/edit/{id}").toUriString());
        return ResponseEntity.created(uri).body(contractService.updateContract(contractRequest));
    }

    /**Borra un servicio de la base de datos - works! */
    @DeleteMapping("/contract/delete/{id}")
    public ResponseEntity<Void> deleteContractById(@PathVariable Long id){
        contractService.deleteContractById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contract/request/")
    public List<Contract> userRequests (Authentication authentication){
        User user = userService.getByUsername(authentication.getName());
        return contractService.findAllByFacilityAssistant(user);
    }

    @GetMapping("/contract/clientrequest/")
    public List<Contract> clientRequests (Authentication authentication){
        User user = userService.getByUsername(authentication.getName());
        return contractService.findAllByClient(user);
    }
}



















/*@PostMapping("/contract/add")
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
    }*/




