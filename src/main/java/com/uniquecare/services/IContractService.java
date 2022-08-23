package com.uniquecare.services;

import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;

import java.util.List;

public interface IContractService {
    Contract addContract (Contract contract);
    /** works*/
    List<Contract> findAllContracts();
    Contract findContractById(Long id);
    void deleteContractById(Long id);
    Contract updateContract (Contract contract);
    List<Contract> getContractByUser(Long userId);
    List<Contract> getContractByAssistant(Long assistantId);
    Contract createContract(User client, Facility facility)
            throws ContractException;

    void acceptContractRequest(Contract request, Facility facility)
            throws ContractException;

    void declineContractRequest(Contract request, Facility facility)
            throws ContractException;
}
