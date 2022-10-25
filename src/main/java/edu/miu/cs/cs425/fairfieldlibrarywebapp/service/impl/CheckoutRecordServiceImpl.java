package edu.miu.cs.cs425.fairfieldlibrarywebapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.dto.CheckoutRecordDTO;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.exception.CustomNotFoundException;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.repository.BookRepository;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.repository.CheckoutRecordRepository;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.repository.LibraryMemberRepository;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.CheckoutRecordService;

@Service
public class CheckoutRecordServiceImpl implements CheckoutRecordService {

    @Autowired
    private CheckoutRecordRepository checkoutRecordRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    @Override
    public List<CheckoutRecord> getCheckoutRecords() {
        return checkoutRecordRepository.findAllCheckouts();
    }

    @Override
    public Page<CheckoutRecord> getCheckoutRecordsPaged(int pageNo) {
        return checkoutRecordRepository.findAllCheckouts(PageRequest.of(pageNo, 2, Direction.ASC, "checkoutDate"));
    }

    @Override
    public CheckoutRecord findCheckoutRecordById(Integer checkoutRecordId) {
        return checkoutRecordRepository.findById(checkoutRecordId).orElse(null);
    }

    @Override
    public CheckoutRecord saveNewCheckoutRecord(CheckoutRecordDTO checkoutRecordDTO) throws CustomNotFoundException {
        var book = bookRepository.findByIsbn(checkoutRecordDTO.getIsbn())
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format("Book with ISBN: %s is not found", checkoutRecordDTO.getIsbn())));
        var libraryMember = libraryMemberRepository.findByMemberNumber(checkoutRecordDTO.getMemberNumber())
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format("Member with memberNumber: %s is not found",
                                checkoutRecordDTO.getMemberNumber())));
        var checkoutRecord = new CheckoutRecord(book, libraryMember);
        return checkoutRecordRepository.save(checkoutRecord);
    }

    @Override
    public CheckoutRecord updateCheckoutRecord(CheckoutRecordDTO checkoutRecordDTO) throws CustomNotFoundException {
        var checkoutRecord = checkoutRecordRepository.findById(checkoutRecordDTO.getCheckoutRecordId())
                .orElseThrow(() -> new CustomNotFoundException("Checkout record is not found"));
        var book = bookRepository.findByIsbn(checkoutRecordDTO.getIsbn())
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format("Book with ISBN: %s is not found", checkoutRecordDTO.getIsbn())));
        var libraryMember = libraryMemberRepository.findByMemberNumber(checkoutRecordDTO.getMemberNumber())
                .orElseThrow(() -> new CustomNotFoundException(
                        String.format("Member with memberNumber: %s is not found",
                                checkoutRecordDTO.getMemberNumber())));
        checkoutRecord.setBook(book);
        checkoutRecord.setLibraryMember(libraryMember);
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
