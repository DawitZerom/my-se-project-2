package edu.miu.cs.cs425.fairfieldlibrarywebapp.controller;

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

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.CheckoutRecord;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.CheckoutRecordService;

@Controller
@RequestMapping(value = { "/fairfieldlibrary/checkoutrecord" })
public class CheckoutRecordController {
    @Autowired
    private CheckoutRecordService checkoutRecordService;

    @GetMapping(value = { "/list" })
    public ModelAndView listCheckoutRecords(@RequestParam(defaultValue = "0") int pageNo) {
        var checkoutRecords = checkoutRecordService.getCheckoutRecordsPaged(pageNo);
        var modelAndView = new ModelAndView();
        modelAndView.addObject("checkoutRecords", checkoutRecords);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/checkoutrecord/list");
        return modelAndView;
    }

    @GetMapping(value = { "/new" })
    public ModelAndView displayNewCheckoutRecordForm() {
        var modelAndView = new ModelAndView();
        modelAndView.addObject("checkoutRecord", new CheckoutRecord());
        modelAndView.setViewName("secured/checkoutrecord/new");
        return modelAndView;
    }

    @PostMapping(value = { "/new" })
    public ModelAndView addNewCheckoutRecord(@Valid @ModelAttribute CheckoutRecord checkoutRecord,
            BindingResult bindingResult) {
        var modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("checkoutRecord", checkoutRecord);
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("secured/checkoutrecord/new");
            return modelAndView;
        }
        checkoutRecordService.saveNewCheckoutRecord(checkoutRecord);
        modelAndView.setViewName("redirect:/fairfieldlibrary/checkoutrecord/list");
        return modelAndView;
    }

    @GetMapping(value = { "/edit/{checkoutRecordId}" })
    public ModelAndView displayEditCheckoutRecord(@PathVariable Integer checkoutRecordId) {
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkoutRecordService.findCheckoutRecordById(checkoutRecordId);
        if (checkoutRecord == null) {
            modelAndView.setViewName("redirect:/fairfieldlibrary/checkoutrecord/list");
            return modelAndView;
        }
        modelAndView.addObject("checkoutRecord", checkoutRecord);
        modelAndView.setViewName("secured/checkoutrecord/edit");
        return modelAndView;
    }

    @PostMapping(value = { "/update" })
    public ModelAndView updateCheckoutRecord(@Valid @ModelAttribute("checkoutRecord") CheckoutRecord checkoutRecord,
            BindingResult bindingResult) {
        var modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("checkoutRecord", checkoutRecord);
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("secured/checkoutrecord/edit");
            return modelAndView;
        }
        checkoutRecordService.updateCheckoutRecord(checkoutRecord);
        modelAndView.setViewName("redirect:/fairfieldlibrary/checkoutrecord/list");
        return modelAndView;
    }

    @GetMapping(value = { "/delete/{checkoutRecordId}" })
    public ModelAndView deleteCheckoutRecord(@PathVariable Integer checkoutRecordId) {
        var modelAndView = new ModelAndView();
        checkoutRecordService.deleteCheckoutRecord(checkoutRecordId);
        modelAndView.setViewName("redirect:/fairfieldlibrary/checkoutrecord/list");
        return modelAndView;
    }

    @GetMapping(value = { "/search" })
    public ModelAndView searchCheckoutRecords(@RequestParam String searchString) {
        if (searchString.isBlank()) {
            return new ModelAndView("redirect:/fairfieldlibrary/checkoutrecord/list");
        }
        var modelAndView = new ModelAndView();
        var checkoutRecord = checkoutRecordService.searchCheckoutRecords(searchString);
        modelAndView.addObject("checkoutRecord", checkoutRecord);
        modelAndView.addObject("searchString", searchString);
        modelAndView.setViewName("secured/checkoutrecord/searchResult");
        return modelAndView;
    }
}
