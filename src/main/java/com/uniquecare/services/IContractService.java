package com.uniquecare.services;

import com.uniquecare.Exceptions.ContractException;
import com.uniquecare.models.Contract;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.ContractRequest;

import java.util.List;

public interface IContractService {
    List<Contract> findByFacilityAndState(Long facility, Contract.State state);
    Boolean existsByClientAndFacilityAndStartAndFinish(ContractRequest contractRequest);
    Contract createContractRequest(ContractRequest contractRequest) throws ContractException;


    Contract addContract (Contract contract);
    /** works*/
    List<Contract> findAllContracts();
    Contract findContractById(Long id);
    Contract findContractByState(Contract.State state);
    void deleteContractById(Long id);
    Contract updateContract (ContractRequest contractRequest) throws ContractException;

    List<Contract> findContractByClientAndState(ContractRequest contractRequest) throws ContractException;
    List<Contract> findContractByFacilityId(ContractRequest contractRequest) throws ContractException;
    List <Contract> findAllByFacilityAssistant(User assistant);
    List <Contract> findAllByClient(User client);
}
