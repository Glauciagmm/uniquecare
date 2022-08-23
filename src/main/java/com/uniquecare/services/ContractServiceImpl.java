package com.uniquecare.services;

import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.repositories.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
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
    public Contract createContract(User client, Facility facility) throws ContractException {
        if (client.getContract().contains(facility)) {
            throw new ContractException("Request are already accepted");
        } else if (!contractRepository
                .findByClientFacilityAndState(client, facility, Contract.State.OPEN).isEmpty()) {
            throw new ContractException("A pending request exists");
        } else if (!contractRepository
                .findByClientFacilityAndState(client, facility, Contract.State.OPEN).isEmpty()) {
            throw new ContractException("A pending request exists");
        }
        Contract request = new Contract();
        request.setClient(client);
        request.setFacility(facility);
        request.setStart(new Date());
        request.setState(Contract.State.OPEN);
        contractRepository.save(request);
        return request;
    }

    @Override
    public void acceptContractRequest(Contract request, Facility facility) throws ContractException {

    }

    @Override
    public void declineContractRequest(Contract request, Facility facility) throws ContractException {

    }


}
