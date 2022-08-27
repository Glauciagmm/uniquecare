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
    public Contract createContractRequest (ContractRequest contractRequest) throws ContractException {
        User client = userService.getUserById(contractRequest.getClient_id());
        Facility facility = facilityService.findFacilityById(contractRequest.getFacility_id());
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
    public Boolean existsByClientAndFacilityAndStartAndFinish(ContractRequest contractRequest) {
        User client = userService.getUserById(contractRequest.getClient_id());
        Facility facility = facilityService.findFacilityById(contractRequest.getFacility_id());
        return contractRepository.existsByClientAndFacilityAndStartAndFinish(client, facility, contractRequest.getStart(), contractRequest.getFinish());
    }


    @Override
    public List<Contract> findContractByClientAndState(ContractRequest contractRequest) throws ContractException{
        User client = userService.getUserById(contractRequest.getClient_id());
        contractRequest.getState(Contract.State.OPEN);
        contractRequest.getState(Contract.State.ACCEPTED);
        contractRequest.getState(Contract.State.DECLINED);
        return contractRepository.findContractByClientAndState( client,contractRequest.getState());
    }


    @Override
    public Contract findContractByState(Contract.State state) {
        return contractRepository.findContractByState(state);
    }

    @Override
    public void deleteContractById(Long id) {
        contractRepository.deleteById(id);
    }



    @Override
    public List<Contract> findContractByFacilityId(ContractRequest contractRequest) throws ContractException {
        return contractRepository.findContractByFacilityId(contractRequest.getFacility_id());
    }

    @Override
    public List<Contract> findAllByFacilityAssistant(User assistant) {
        return contractRepository.findAllByFacilityAssistant(assistant);
    }

    @Override
    public List<Contract> findAllByClient(User client) {
        return contractRepository.findAllByClient(client);
    }

    @Override
    public Contract updateContract(ContractRequest contractRequest) throws ContractException{
            Contract contract = contractRepository.findById(contractRequest.getId()).orElseThrow(RuntimeException::new);
            contract.setStart(contractRequest.getStart());
            contract.setFinish(contractRequest.getFinish());
            return contractRepository.save(contract);
    }

    @Override
    public List<Contract> findByFacilityAndState(Long facilityId, Contract.State state) {
        return contractRepository.findByFacilityAndState(facilityId, state);
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

    /*@Override
    public Contract createContractRequest (ContractRequest contractRequest) throws ContractException {
        User client = userService.getUserById(contractRequest.getClient_id());
        Facility facility = facilityService.findFacilityById(contractRequest.getFacility_id());
        *//*client.getContract().stream().forEach(contract -> {
            if(contract.getStart().compareTo(contractRequest.getStart()) == 0) {
                try {
                    throw new ContractException("A pending request exists");
                } catch (ContractException e) {
                    e.printStackTrace();
                }
            };
        });*//*
        *//*if (client.getContract().contains(facility)) {
            throw new ContractException("Request are already accepted");
        } else if (!contractRepository
                .findByClientAndFacilityAndState(client, facility, Contract.State.OPEN).isEmpty()) {
            throw new ContractException("A pending request exists");
        } else if (!contractRepository
                .findByClientAndFacilityAndState(client, facility, Contract.State.OPEN).isEmpty()) {
            throw new ContractException("A pending request exists");
        }*//*

        Contract request = new Contract();
        request.setClient(client);
        request.setFacility(facility);
        request.setStart(contractRequest.getStart());
        request.setFinish(contractRequest.getFinish());
        request.setTotalPrice(contractRequest.getTotalPrice());
        request.setState(contractRequest.getState(Contract.State.OPEN));
        contractRepository.save(request);
        return request;
    }*/