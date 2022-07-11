package com.sandip.controller.postController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandip.entity.User;
import com.sandip.entity.UserPost;
import com.sandip.service.BranchDaoImpl;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.service.CourseDaoImpl;
import com.sandip.service.UserDaoImpl;
import com.sandip.service.UserPostImpl;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @Autowired
    private CourseDaoImpl courseDaoImpl;

    @Autowired
    private BranchDaoImpl branchDaoImpl;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserPostImpl userPostImpl;

    @RequestMapping("/userProfile")
    public String userProfile(Model model, Principal principal) {

         // getting current login user
         User logUser = userDaoImpl.userByEmail(principal.getName());
         model.addAttribute("logUser", logUser);

        model.addAttribute("navbarCategoryData", categoryDaoImpl.gettingCategory());
        model.addAttribute("courseData", courseDaoImpl.gettingCourseDataList());
        model.addAttribute("branchData", branchDaoImpl.gettingBranchAllData());


        // getting post data by user
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postId").descending());

        // making object and setting id in it for getting post data by user
        User user = new User();
        user.setUserId(logUser.getUserId());
        Page<UserPost> findByUserDataPagination = userPostImpl.findPostByUser(user, pageable);

        model.addAttribute("postData", findByUserDataPagination);

        return "postSpace/userProfile";
    }

    @PostMapping("/saveProfile")
    public String saveUserProfile(@ModelAttribute User user) {

        // System.out.println("Check================================");
        // System.out.println("Our User : "+user.getFirstName() + ":" + user.getLastName() + ":" + user.getEmail() + ":"
        //         + user.getPassword() + ":" + user.getAcademicYear() + ":" + user.getPrn() + ":" + user.getProfilePic()
        //         + ":" + user.getCourse().getCourseName() + ":" + user.getBranch().getBranchName());

        return "redirect:/profile/userProfile";
    }

}
