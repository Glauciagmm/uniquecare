package com.uniquecare.services;

import com.uniquecare.Exceptions.ContractException;
import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.ContractRequest;
import com.uniquecare.repositories.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ContractServiceImpl implements IContractService {

    private final ContractRepository contractRepository;
    private final IUserService userService;
    private final IFacilityService facilityService;

    @Autowired
    public ContractServiceImpl (IUserService userService, IFacilityService facilityService, ContractRepository contractRepository){
        this.userService = userService;
        this.facilityService = facilityService;
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> findAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Contrato no encontrado"));
    }

    @Override
    public void deleteContractById(Long id) {
        contractRepository.deleteById(id);
    }


    @Override
    public Contract createContractRequest (ContractRequest contractRequest) throws ContractException {
        User client = userService.getUserById(contractRequest.getClient_id());
        Facility facility = facilityService.findFacilityById(contractRequest.getFacility_id());
        if (client.getContract().contains(facility)) {
            throw new ContractException("Request are already accepted");
        } else if (!contractRepository
                .findByClientAndFacilityAndState(client, facility, Contract.State.OPEN).isEmpty()) {
            throw new ContractException("A pending request exists");
        } else if (!contractRepository
                .findByClientAndFacilityAndState(client, facility, Contract.State.OPEN).isEmpty()) {
            throw new ContractException("A pending request exists");
        }
        Contract request = new Contract();
        request.setClient(client);
        request.setFacility(facility);
        request.setStart(contractRequest.getStart());
        request.setFinish(contractRequest.getFinish());
        request.setTotalPrice(contractRequest.getTotalPrice());
        request.setState(contractRequest.getState(Contract.State.OPEN));
        contractRepository.save(request);
        return request;
    }

    @Override
    public Contract updateContract(ContractRequest contractRequest) throws ContractException{
        User client = userService.getUserById(contractRequest.getClient_id());
        Facility facility = facilityService.findFacilityById(contractRequest.getFacility_id());
        if (client.getContract().contains(facility)) {
            throw new ContractException("Request are already accepted");
        } else if (!contractRepository
                .findByClientAndFacilityAndState(client, facility, Contract.State.DECLINED).isEmpty()) {
            throw new ContractException("This request was already refused");
        }
        return null;
                //contractRepository.save(contractRequest);
    }

    @Override
    public List<Contract> getContractByUser(Long userId) {
        return userService.getContractByUserId(userId);
    }


    @Override
    public List<Contract> getContractByAssistant(Long assistantId) {
        return userService.getContractByAssistantId(assistantId);
    }

     @Override
    public List<Contract> getAllRequest(User client, Facility facility, Contract.State state) throws ContractException {
        List<Contract> request = contractRepository.findByClientAndFacilityAndState(client, facility,state);
        if (!request.isEmpty()){
            throw new ContractException("No contracts to show");
        } else {
            return contractRepository.findAll();
        }
    }

    @Override
    public List<Contract> getOpenRequest(Contract.State state) throws ContractException {
        return null;
    }

    @Override
    public List<Contract> findByFacilityAndState(Facility facility, Contract.State state) {
        return contractRepository.findByFacilityAndState(facility, state);
    }
}



















 /* @Override
    public Contract acceptContractRequest(ContractRequest contractRequest) throws ContractException {
        Optional<Contract> contract = contractRepository.findById(41L);
        contract.get().setState(Contract.State.ACCEPTED);
        return contractRepository.save(contract.get());
    }

    @Override
    public Contract declineContractRequest(ContractRequest contractRequest) throws ContractException {
        Optional<Contract> contract = contractRepository.findById(contractRequest.getId());

        contract.get().setState(Contract.State.DECLINED);
        return contractRepository.save(contract.get());
    }*/