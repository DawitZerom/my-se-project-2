package edu.miu.cs.cs425.fairfieldlibrarywebapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.repository.CheckoutRecordRepository;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.CheckinService;

@Service
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckoutRecordRepository checkoutRecordRepository;

    @Override
    public CheckoutRecord findCheckoutRecordById(Integer checkoutRecordId) {
        return checkoutRecordRepository.findById(checkoutRecordId).orElse(null);
    }

    @Override
    public CheckoutRecord findCheckoutRecordByIsbn(String isbn) {
        return checkoutRecordRepository.findByBookIsbn(isbn);
    }

    @Override
    public CheckoutRecord updateCheckoutRecord(CheckoutRecord checkoutRecord) {
        // TODO Auto-generated method stub
        return null;
    }

}
