package edu.miu.cs.cs425.fairfieldlibrarywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(value = { "/search" })
    public ModelAndView displayCheckoutRecordForCheckinParam(@RequestParam String isbn) {
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkinService.findCheckoutRecordByIsbn(isbn);
        modelAndView.addObject("isbn", isbn);
        if (checkoutRecord == null) {
            modelAndView.addObject("notFound", String.format("Book with ISBN: %s is not found", isbn));
            modelAndView.setViewName("secured/checkin/find");
            return modelAndView;
        }
        modelAndView.addObject("checkoutRecord", checkoutRecord);
        modelAndView.setViewName("secured/checkin/detail");
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
}
