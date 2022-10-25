package edu.miu.cs.cs425.fairfieldlibrarywebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.dto.CheckinDTO;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.exception.CustomNotFoundException;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.CheckinService;

@Controller
@RequestMapping(value = { "/fairfieldlibrary/checkin" })
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping(value = { "/find" })
    public ModelAndView displayCheckoutRecordForCheckin() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("secured/checkin/find");
        return modelAndView;
    }

    @GetMapping(value = { "/list" })
    public ModelAndView getCheckins(@RequestParam(defaultValue = "0") int pageNo) {
        var checkoutRecords = checkinService.getCheckinsPaged(pageNo);
        var modelAndView = new ModelAndView();
        modelAndView.addObject("checkoutRecords", checkoutRecords);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/checkin/list");
        return modelAndView;
    }

    @GetMapping(value = { "/search" })
    public ModelAndView displayCheckoutRecordForCheckinParam(@RequestParam String isbn) {
        var modelAndView = new ModelAndView();
        List<CheckoutRecord> checkoutRecords = checkinService.findCheckoutRecordsByIsbn(isbn);
        modelAndView.addObject("isbn", isbn);
        if (checkoutRecords == null) {
            modelAndView.addObject("notFound", String.format("Book with ISBN: %s is not found", isbn));
            modelAndView.setViewName("secured/checkin/find");
            return modelAndView;
        }
        modelAndView.addObject("checkoutRecords", checkoutRecords);
        modelAndView.setViewName("secured/checkin/foundlist");
        return modelAndView;
    }

    @GetMapping(value = { "/find/{checkoutRecordId}" })
    public ModelAndView displayCheckoutRecordForCheckinParam(@PathVariable Integer checkoutRecordId) {
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkinService.findCheckoutRecordById(checkoutRecordId);
        if (checkoutRecord == null) {
            modelAndView.addObject("notFound", String.format("Book with Id: %d is not found", checkoutRecordId));
            modelAndView.setViewName("secured/checkin/find");
            return modelAndView;
        }
        modelAndView.addObject("checkoutRecord", checkoutRecord);
        modelAndView.setViewName("secured/checkin/detail");
        return modelAndView;
    }

    @PostMapping(value = { "/checkinbook" })
    public ModelAndView checkin(@RequestParam Integer checkoutRecordId) {
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkinService.findCheckoutRecordById(checkoutRecordId);
        if (checkoutRecord == null) {
            modelAndView.addObject("notFound", "The record is not found");
            modelAndView.setViewName("secured/checkin/find");
            return modelAndView;
        }
        checkinService.checkin(checkoutRecord);
        modelAndView.setViewName("redirect:/fairfieldlibrary/checkin/list");
        return modelAndView;
    }

    @GetMapping(value = { "/edit/{checkoutRecordId}" })
    public ModelAndView displayEditCheckin(@PathVariable Integer checkoutRecordId) {
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkinService.findCheckoutRecordById(checkoutRecordId);
        if (checkoutRecord == null) {
            modelAndView.setViewName("redirect:/fairfieldlibrary/checkin/list");
            return modelAndView;
        }
        modelAndView.addObject("checkoutRecord", checkoutRecord);
        modelAndView.setViewName("secured/checkin/edit");
        return modelAndView;
    }

    @PostMapping(value = { "/update" })
    public ModelAndView updateCheckin(@Valid @ModelAttribute CheckinDTO checkinDTO,
            BindingResult bindingResult) throws CustomNotFoundException {
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkinService.findCheckoutRecordById(checkinDTO.getCheckoutRecordId());
        if (checkoutRecord == null) {
            modelAndView.setViewName("secured/checkin/list");
            return modelAndView;
        }
        checkinService.updateCheckin(checkinDTO);
        modelAndView.setViewName("redirect:/fairfieldlibrary/checkin/list");
        return modelAndView;
    }
}