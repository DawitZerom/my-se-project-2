package edu.miu.cs.cs425.fairfieldlibrarywebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;

@Repository
public interface CheckoutRecordRepository extends JpaRepository<CheckoutRecord, Integer> {
    List<CheckoutRecord> findAllByCheckoutDateContainingOrDueDateContainingOrCheckinDateContaining(String checkoutDate,
            String dueDate, String checkinDate);

    @Query("SELECT c FROM CheckoutRecord c WHERE c.book.isbn = ?1 AND c.isCheckedIn = 'No'")
    CheckoutRecord findByBookIsbn(String isbn);

}
