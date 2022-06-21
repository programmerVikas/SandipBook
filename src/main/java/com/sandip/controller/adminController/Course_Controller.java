package com.sandip.controller.adminController;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import com.sandip.entity.Course;
import com.sandip.service.CourseDaoImpl;
import com.sandip.staticData.ConstantData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/course")
public class Course_Controller {

    @Autowired
    private CourseDaoImpl courseDaoImpl;

    private long id = 0;

    @PostMapping("/saveCourse/{currentPage}")
    public String saveCourseData(@PathVariable("currentPage") Integer currentPage, @Valid @ModelAttribute Course course,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        // handling input field error/validation
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("courseMess", bindingResult.getFieldError().getDefaultMessage());
            redirectAttributes.addFlashAttribute("courseData", course);
            return "redirect:/admin/course/" + currentPage;
        }


        // trimming course name!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        course.setCourseName(course.getCourseName().trim());

        // new category set time and date------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        // setting date & time in course ---------------------------------------
        course.setAddAt(formattedDate);
        // for updating if id is matching with any database id, otherwise new data will save in database
        course.setCourseId(id);

        // trimming course description
        course.setCourseDesc(course.getCourseDesc().trim());

        try {
            courseDaoImpl.saveCourseData(course);
            // setting id = 0 because if again we save new data then id should be '0'
            // otherwise our data will be update of that id.
            id = 0;
            redirectAttributes.addAttribute("courseMess", ConstantData.SAVED_SUCCESSFULLY);

        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + e);
            redirectAttributes.addAttribute("courseMess", ConstantData.ALREADY_AVAILABLE);
            redirectAttributes.addFlashAttribute("courseData", course);
            return "redirect:/admin/course/" + currentPage;
        }

        return "redirect:/admin/course/" + currentPage;
    }

    // deleting course
    // data!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping("delete/{id}/{currentPage}")
    public String deleteCourseData(@PathVariable("id") Long id, @PathVariable("currentPage") Integer currentPage,
            RedirectAttributes redirectAttributes) {
        courseDaoImpl.deleteCourseData(id);
        redirectAttributes.addAttribute("courseMess", ConstantData.DELETED_SUCCESSFULLY);
        return "redirect:/admin/course/" + currentPage;
    }

    // updating course data !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @RequestMapping("update/{currentPage}")
    public String updateCourseData(@ModelAttribute("courseData") Course course,
            @PathVariable("currentPage") Integer currentPage, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("coursecardtitle", ConstantData.UPATE_COURSE_CARD_TITLE);
        this.id = course.getCourseId(); // setting category id for updation
        redirectAttributes.addAttribute("courseData", course);
        redirectAttributes.addAttribute("courseMess", ConstantData.START_UPDATING_DATA);

        return "redirect:/admin/course/" + currentPage;
    }

}
