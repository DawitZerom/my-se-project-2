package edu.miu.cs.cs425.fairfieldlibrarywebapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;

public interface CheckoutRecordService {

    List<CheckoutRecord> getCheckoutRecords();

    Page<CheckoutRecord> getCheckoutRecordsPaged(int pageNo);

    CheckoutRecord findCheckoutRecordById(Integer checkoutRecordId);

    CheckoutRecord saveNewCheckoutRecord(CheckoutRecord checkoutRecord);

    CheckoutRecord updateCheckoutRecord(CheckoutRecord checkoutRecord);

    void deleteCheckoutRecord(Integer checkoutRecordid);

    List<CheckoutRecord> searchCheckoutRecords(String searchString);
}
