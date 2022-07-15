package com.sandip.controller.adminController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sandip.entity.User;
import com.sandip.entity.UserPost;
import com.sandip.service.BranchDaoImpl;
import com.sandip.service.CourseDaoImpl;
import com.sandip.service.UserDaoImpl;
import com.sandip.service.UserPostImpl;
import com.sandip.service.UserRoleImpl;
import com.sandip.staticData.ConstantData;

@Controller
@RequestMapping("/admin")
public class SearchPostByUser {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserPostImpl userPostImpl;

    @Autowired
    private UserRoleImpl userRoleImpl;

    @Autowired
    private BranchDaoImpl branchDaoImpl;

    @Autowired
    private CourseDaoImpl courseDaoImpl;

    @ModelAttribute
    public void commonData(Model model, Principal principal) {
        User logUser = userDaoImpl.userByEmail(principal.getName());
        model.addAttribute("logUser", logUser);
    }

    @RequestMapping(value = "/searchPostByMail/{page}", method = { RequestMethod.POST, RequestMethod.GET })
    public String searchUserPostByMail(@PathVariable("page") int page, @RequestParam("email") String email,
            Model model) {

        User userByEmail = userDaoImpl.userByEmail(email);

        model.addAttribute("userByEmail", userByEmail);

        Pageable pageable = PageRequest.of(page, ConstantData.PAGE_SIZE, Sort.by("postId").descending());

        Page<UserPost> findPostByUser = userPostImpl.findPostByUser(userByEmail, pageable);

        model.addAttribute("userPostData", findPostByUser);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", findPostByUser.getTotalPages());

        model.addAttribute("select4", ConstantData.SELECT);

        model.addAttribute("courseData", courseDaoImpl.gettingCourseDataList());
        model.addAttribute("branchData", branchDaoImpl.gettingBranchAllData());
        model.addAttribute("userRole", userRoleImpl.gettingAllUserRole());

        return "adminSpace/searchUserPost";
    }

    @RequestMapping("/deletePostByUser/{id}/{email}")
    public String deletePost(@PathVariable("id") Long id, @PathVariable("email") String email,
            RedirectAttributes redirectAttributes) {

        userPostImpl.deletePostById(id);
        redirectAttributes.addAttribute("email", email);
        return "redirect:/admin/searchPostByMail/0";
    }


    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        
        User userByEmail = userDaoImpl.userByEmail(user.getEmail());

        user.setUserId(userByEmail.getUserId());
        user.setPassword(userByEmail.getPassword());
        user.setProfilePic((userByEmail.getProfilePic() != null)?userByEmail.getProfilePic():null);
        user.setRegistereddAt(userByEmail.getRegistereddAt());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();

        user.setUpdatedAt(formattedDate);
        redirectAttributes.addAttribute("email", userByEmail.getEmail());

        userDaoImpl.saveUser(user);

        return "redirect:/admin/searchPostByMail/0";
    }


}
