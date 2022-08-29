package com.uniquecare.repositories;

import com.uniquecare.models.Contract;
import com.uniquecare.models.Facility;
import com.uniquecare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByFacilityAndState(Long facilityId, Contract.State state);
    List <Contract> findAllByFacilityAssistant(User assistant);
    List <Contract> findAllByClient(User client);
    Boolean existsByClientAndFacilityAndStartAndFinish(User client, Facility facility, String start, String finish);
    Contract findContractByState(Contract.State state);
    List<Contract> findContractByFacilityId(Long facility_id);
    List<Contract> findContractByClientAndState(User client, Contract.State state);

}
