package edu.miu.cs.cs425.fairfieldlibrarywebapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.dto.CheckoutRecordDTO;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.exception.CustomNotFoundException;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;

public interface CheckoutRecordService {

    List<CheckoutRecord> getCheckoutRecords();

    Page<CheckoutRecord> getCheckoutRecordsPaged(int pageNo);

    CheckoutRecord findCheckoutRecordById(Integer checkoutRecordId);

    CheckoutRecord saveNewCheckoutRecord(CheckoutRecordDTO checkoutRecordDTO) throws CustomNotFoundException;

    CheckoutRecord updateCheckoutRecord(CheckoutRecordDTO checkoutRecordDTO) throws CustomNotFoundException;

    void deleteCheckoutRecord(Integer checkoutRecordid);

    Page<CheckoutRecord> searchCheckoutRecords(String searchString, int pageNo);
}
