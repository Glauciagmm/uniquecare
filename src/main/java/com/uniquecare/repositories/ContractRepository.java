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
    List<Contract> findByFacilityAndState(Facility facility, Contract.State state);
    List<Contract> findByClientAndFacilityAndState(User client, Facility facility, Contract.State state);
    Boolean existsByClientAndFacilityAndStartAndFinish(User client, Facility facility, Date start, Date finish);
}
