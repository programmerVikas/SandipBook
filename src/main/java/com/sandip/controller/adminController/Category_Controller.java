package com.sandip.controller.adminController;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import com.sandip.entity.Category;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.staticData.ConstantData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/category")
public class Category_Controller {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    // this is is is coming from update category method for updating category
    private long id = 0;

    @PostMapping("/saveCategory/{currentPage}")
    public ModelAndView savingCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
            @PathVariable("currentPage") int currentPage,
            ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("categoryMess", result.getFieldError().getDefaultMessage());
            return new ModelAndView("redirect:/admin/category/" + currentPage, model);
        }

        // trimming category name
        category.setCategoryName(category.getCategoryName().trim());

        // new category set time and date------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        // setting date & time in category ---------------------------------------
        category.setAddAt(formattedDate);
        // if id will be already here then data will be update otherwise data will be
        // save beacuse updatng method is setting the id
        category.setCId(id);
       

        // Saving new category--------------------------------------------

        try {
            categoryDaoImpl.saveNewCategory(category);
            id = 0;
            model.addAttribute("categoryMess", ConstantData.SAVED_SUCCESSFULLY);
        } catch (Exception e) {
            model.addAttribute("categoryMess", ConstantData.ALREADY_AVAILABLE);
            return new ModelAndView("redirect:/admin/category/" + currentPage, model);
        }
        return new ModelAndView("redirect:/admin/category/" + currentPage, model);
    }

    // creating a delete mapping methods that deletes a specified category
    @RequestMapping("/delete/{id}/{currentPage}")
    private ModelAndView deleteCategory(@PathVariable long id, @PathVariable("currentPage") int currentPage,
            ModelMap model) {
        categoryDaoImpl.delete(id);
        model.addAttribute("categoryMess", ConstantData.DELETED_SUCCESSFULLY);
        return new ModelAndView("redirect:/admin/category/" + currentPage, model);
    }

    // creating a updating mapping methods that update a specified category

    @RequestMapping("/update/{id}/{categoryName}/{currentPage}")
    private ModelAndView updateCategory(@PathVariable("id") long id, @PathVariable("currentPage") int currentPage,
            @PathVariable("categoryName") String categoryName, ModelMap model) {
        model.addAttribute("catcardtitle", ConstantData.UPATE_CATEGORY_CARD_TITLE);
        // model.addAttribute("select3", ConstantData.SELECT);
        // categoryDaoImpl.
        model.addAttribute("categoryMess", ConstantData.START_UPDATING_DATA);
        model.addAttribute("catDataname", categoryName); // setting value in input box
        this.id = id; // setting category id for updation
        return new ModelAndView("redirect:/admin/category/" + currentPage, model);
    }

}