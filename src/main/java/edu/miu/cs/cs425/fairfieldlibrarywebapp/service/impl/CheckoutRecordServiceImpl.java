package edu.miu.cs.cs425.fairfieldlibrarywebapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.repository.CheckoutRecordRepository;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.CheckoutRecordService;

@Service
public class CheckoutRecordServiceImpl implements CheckoutRecordService {

    @Autowired
    private CheckoutRecordRepository checkoutRecordRepository;

    @Override
    public List<CheckoutRecord> getCheckoutRecords() {
        return checkoutRecordRepository.findAll();
    }

    @Override
    public Page<CheckoutRecord> getCheckoutRecordsPaged(int pageNo) {
        return checkoutRecordRepository.findAll(PageRequest.of(pageNo, 2, Direction.ASC, "checkoutDate"));
    }

    @Override
    public CheckoutRecord findCheckoutRecordById(Integer checkoutRecordId) {
        return checkoutRecordRepository.findById(checkoutRecordId).orElse(null);
    }

    @Override
    public CheckoutRecord saveNewCheckoutRecord(CheckoutRecord checkoutRecord) {
        return checkoutRecordRepository.save(checkoutRecord);
    }

    @Override
    public CheckoutRecord updateCheckoutRecord(CheckoutRecord checkoutRecord) {
        return checkoutRecordRepository.save(checkoutRecord);
    }

    @Override
    public void deleteCheckoutRecord(Integer checkoutRecordid) {
        checkoutRecordRepository.deleteById(checkoutRecordid);
    }

    @Override
    public List<CheckoutRecord> searchCheckoutRecords(String searchString) {
        return checkoutRecordRepository.findAllByCheckoutDateContainingOrDueDateContainingOrCheckinDateContaining(
                searchString, searchString, searchString);
    }

}
