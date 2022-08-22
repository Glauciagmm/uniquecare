package com.uniquecare.repositories;

import com.uniquecare.models.Contract;
import com.uniquecare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByClientAssistantAndState(User client, User assistant, Contract.State state);
    List<Contract> findByAssistantAndState(User receiver, Contract.State state);
}
