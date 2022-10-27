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

import edu.miu.cs.cs425.fairfieldlibrarywebapp.model.LibraryMemberType;
import edu.miu.cs.cs425.fairfieldlibrarywebapp.service.LibraryMemberTypeService;

@Controller
@RequestMapping(value = { "/library/secured/librarymembertype", "/fairfieldlibrary/librarymembertype",
        "/library/secured/membertype", "/library/secured/membershiptype" })
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

    @GetMapping(value = { "/new" })
    public ModelAndView displayNewMembershipTypeForm() {
        var modelAndView = new ModelAndView();
        modelAndView.addObject("libraryMemberType", new LibraryMemberType());
        modelAndView.setViewName("secured/librarymembertype/new");
        return modelAndView;
    }

    @PostMapping(value = { "/new" })
    public ModelAndView addNewMembershipType(@Valid @ModelAttribute LibraryMemberType libraryMemberType,
            BindingResult bindingResult) {
        var modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("libraryMemberType", libraryMemberType);
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("secured/librarymembertype/new");
            return modelAndView;
        }
        libraryMemberTypeService.saveNewLibraryMemberType(libraryMemberType);
        modelAndView.setViewName("redirect:/fairfieldlibrary/librarymembertype/list");
        return modelAndView;
    }

    @GetMapping(value = { "/edit/{libraryMemberTypeId}" })
    public ModelAndView displayEditMembershipTypeForm(@PathVariable Integer libraryMemberTypeId) {
        var modelAndView = new ModelAndView();
        var libraryMemberType = libraryMemberTypeService.findLibraryMemberTypeById(libraryMemberTypeId);
        modelAndView.addObject("libraryMemberType", libraryMemberType);
        modelAndView.setViewName("secured/librarymembertype/edit");
        return modelAndView;
    }

    @PostMapping(value = { "/update" })
    public ModelAndView updateMembershipType(@Valid @ModelAttribute LibraryMemberType libraryMemberType,
            BindingResult bindingResult) {
        var modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("libraryMemberType", libraryMemberType);
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("secured/librarymembertype/edit");
            return modelAndView;
        }
        libraryMemberTypeService.updateLibraryMemberType(libraryMemberType);
        modelAndView.setViewName("redirect:/fairfieldlibrary/librarymembertype/list");
        return modelAndView;
    }

    @GetMapping(value = { "/delete/{libraryMemberTypeId}" })
    public ModelAndView updateMembershipType(@PathVariable Integer libraryMemberTypeId) {
        var modelAndView = new ModelAndView();
        libraryMemberTypeService.deleteLibraryMemberType(libraryMemberTypeId);
        modelAndView.setViewName("redirect:/fairfieldlibrary/librarymembertype/list");
        return modelAndView;
    }

    @GetMapping(value = { "/search" })
    public ModelAndView searchMembershipType(@RequestParam String searchString) {
        if (searchString.isBlank()) {
            return new ModelAndView("redirect:/fairfieldlibrary/librarymembertype/list");
        }
        var modelAndView = new ModelAndView();
        var libraryMemberTypes = libraryMemberTypeService.searchLibraryMemberTypes(searchString);
        modelAndView.addObject("libraryMemberTypes", libraryMemberTypes);
        modelAndView.addObject("searchString", searchString);
        modelAndView.setViewName("secured/librarymembertype/searchResult");
        return modelAndView;
    }
}
