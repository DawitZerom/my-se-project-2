package edu.miu.cs.cs425.fairfieldlibrarywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.LibraryMemberTypeService;

@Controller
@RequestMapping(value = { "/fairfieldlibrary/librarymembertype", "/library/secured/membertype" })
public class LibraryMemberTypeController {
    @Autowired
    private LibraryMemberTypeService libraryMemberTypeService;

    @GetMapping(value = { "/list" })
    public ModelAndView listLibrarymembers(@RequestParam(defaultValue = "0") int pageNo) {
        var libraryMemberTypes = libraryMemberTypeService.getLibraryMemberTypesPaged(pageNo);
        var modelAndView = new ModelAndView();
        modelAndView.addObject("libraryMemberTypes", libraryMemberTypes);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/librarymembertype/list");
        return modelAndView;
    }
}
