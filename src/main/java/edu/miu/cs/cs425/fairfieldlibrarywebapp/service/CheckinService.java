package edu.miu.cs.cs425.fairfieldlibrarywebapp.service;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;

public interface CheckinService {

    CheckoutRecord findCheckoutRecordById(Integer checkoutRecordId);

    CheckoutRecord findCheckoutRecordByIsbn(String isbn);

    CheckoutRecord updateCheckoutRecord(CheckoutRecord checkoutRecord);
}
