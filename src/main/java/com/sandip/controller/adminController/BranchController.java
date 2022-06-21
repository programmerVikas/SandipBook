package com.sandip.controller.adminController;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sandip.entity.CourseBranch;
import com.sandip.service.BranchDaoImpl;
import com.sandip.staticData.ConstantData;

@Controller
@RequestMapping("/admin/branch")
public class BranchController {

    @Autowired
    private BranchDaoImpl branchDaoImpl;

    public long id = 0;

    // Saving brancg data
    // here stayId for staying at same page after saving data table data wil not
    // changeOr will not showed from start!!!!!
    @PostMapping("/saveBranch/{stayId}")
    public String saveBranchData(@PathVariable("stayId") Long stayId,
            @Valid @ModelAttribute("CourseBranch") CourseBranch cBranch,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (cBranch.getCourse() == null) {
            redirectAttributes.addAttribute("branchMess", "Choose your Branch course !!");
            redirectAttributes.addFlashAttribute("branchData", cBranch);
            return "redirect:/admin/branch/" + stayId;
        }

        if (bindingResult.hasErrors()) {
            // System.out.println(":::::::::::::::::::::::::::::"+cBranch.getCourse().getCourseId());
            redirectAttributes.addAttribute("branchMess", bindingResult.getFieldError().getDefaultMessage());
            redirectAttributes.addFlashAttribute("branchData", cBranch);
            return "redirect:/admin/branch/" + stayId;
        }

        // already data couldn't
        // save!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (branchDaoImpl.checkUsernameandCourse(cBranch.getBranchName().trim(), cBranch.getCourse())) {
            redirectAttributes.addAttribute("branchMess", ConstantData.ALREADY_AVAILABLE);
            redirectAttributes.addFlashAttribute("branchData", cBranch);
            return "redirect:/admin/branch/" + stayId;
        }

        // trimming branch name !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        cBranch.setBranchName(cBranch.getBranchName().trim());

        // new category set time and date------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        // setting date & time in course ---------------------------------------
        cBranch.setAddAt(formattedDate);

        // for updating if id is matching with any database id, otherwise new data will
        // save in database
        cBranch.setBranchId(id);

        // trimming branch description!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        cBranch.setBranchDesc(cBranch.getBranchDesc().trim());

        branchDaoImpl.saveBranchData(cBranch);
        redirectAttributes.addAttribute("branchMess", ConstantData.SAVED_SUCCESSFULLY);

        // setting id = 0 because if again we save new data then id should be '0'
        // otherwise our data will be update of that id.
        id = 0;

        return "redirect:/admin/branch/" + stayId;
    }

    @RequestMapping("/delete/{stayId}/{id}")
    public String deleteBranchData(@PathVariable("stayId") Long stayId, @PathVariable("id") Long id) {
        branchDaoImpl.deleteBranchData(id);
        return "redirect:/admin/branch/" + stayId;
    }

    @RequestMapping("/update/{stayId}")
    public String updateBranchData(@PathVariable("stayId") Long stayId,
            @ModelAttribute("BranchData") CourseBranch courseBranch, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("branchcardtitle", ConstantData.UPATE_BRANCH_CARD_TITLE);
        this.id = courseBranch.getBranchId();// setting course id for updation
        redirectAttributes.addFlashAttribute("branchData", courseBranch);
        redirectAttributes.addAttribute("branchMess", ConstantData.START_UPDATING_DATA);

        return "redirect:/admin/branch/" + stayId;
    }

}
