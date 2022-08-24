package com.uniquecare.services;

import com.uniquecare.Exceptions.ContractException;
import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import com.uniquecare.payload.request.ContractRequest;

import java.util.List;

public interface IContractService {
    Contract addContract (Contract contract);
    /** works*/
    List<Contract> findAllContracts();
    Contract findContractById(Long id);
    void deleteContractById(Long id);
    Contract updateContract (ContractRequest contractRequest) throws ContractException;
    List<Contract> getContractByUser(Long userId);
    List<Contract> getContractByAssistant(Long assistantId);
    Contract createContractRequest(ContractRequest contractRequest) throws ContractException;
    List<Contract> getAllRequest (User client, Facility facility, Contract.State state) throws ContractException;
    List<Contract> getOpenRequest (Contract.State state) throws ContractException;
    List<Contract> findByFacilityAndState(Facility facility, Contract.State state);
}
