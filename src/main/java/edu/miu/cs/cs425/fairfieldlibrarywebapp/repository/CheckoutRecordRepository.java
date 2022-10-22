package edu.miu.cs.cs425.fairfieldlibrarywebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;

@Repository
public interface CheckoutRecordRepository extends JpaRepository<CheckoutRecord, Integer> {
    List<CheckoutRecord> findAllByCheckoutDateContainingOrDueDateContainingOrCheckinDateContaining(String checkoutDate,
            String dueDate, String checkinDate);
}
