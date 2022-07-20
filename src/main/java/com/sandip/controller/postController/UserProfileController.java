package com.sandip.controller.postController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sandip.entity.Event;
import com.sandip.entity.User;
import com.sandip.entity.UserPost;
import com.sandip.entity.UserRole;
import com.sandip.service.BranchDaoImpl;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.service.CourseDaoImpl;
import com.sandip.service.EventDaoImpl;
import com.sandip.service.UserDaoImpl;
import com.sandip.service.UserPostImpl;
import com.sandip.service.UserRoleImpl;
import com.sandip.staticData.ProfileUploadHelper;

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

    @Autowired
    private UserRoleImpl userRoleImpl;

    @Autowired
    private ProfileUploadHelper profileUploadHelper;

    @Autowired
    private EventDaoImpl eventDaoImpl;

    @RequestMapping("/userProfile/{page}")
    public String userProfile(@PathVariable("page") int page, Model model, Principal principal) {

        // setting role
        List<UserRole> userRoleList = userRoleImpl.gettingAllUserRole().stream()
                .filter(e -> (e.getRole().equals("teacher")) || (e.getRole().equals("student")))
                .collect(Collectors.toList());

        model.addAttribute("userRole", userRoleList);

        // getting current login user
        User logUser = userDaoImpl.userByEmail(principal.getName());
        model.addAttribute("logUser", logUser);

        model.addAttribute("navbarCategoryData", categoryDaoImpl.gettingCategory());
        model.addAttribute("courseData", courseDaoImpl.gettingCourseDataList());
        model.addAttribute("branchData", branchDaoImpl.gettingBranchAllData());

        // getting post data by user
        Pageable pageable = PageRequest.of(page, 5, Sort.by("postId").descending());

        // making object and setting id in it for getting post data by user
        User user = new User();
        user.setUserId(logUser.getUserId());
        Page<UserPost> findByUserDataPagination = userPostImpl.findPostByUser(user, pageable);

        model.addAttribute("postData", findByUserDataPagination);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", findByUserDataPagination.getTotalPages());

        // Getting event data
        Pageable pageable2 = PageRequest.of(0, 10, Sort.by("eventId").descending());
        Page<Event> findEventByUser = eventDaoImpl.findEventByUser(logUser, pageable2);
        model.addAttribute("eventData", findEventByUser);
        // Getting event data ending 

        return "postSpace/userProfile";
    }

    @PostMapping("/saveProfile")
    public String saveUserProfile(@ModelAttribute User user, Principal principal) {

        // getting current login user
        User logUser = userDaoImpl.userByEmail(principal.getName());

        // set log id
        user.setUserId(logUser.getUserId());
        // user registration set time and date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        user.setUpdatedAt(formattedDate);

        // set role
        user.setUser_role(user.getUser_role() == null ? logUser.getUser_role() : user.getUser_role());

        // set registered time
        user.setRegistereddAt(logUser.getRegistereddAt());

        // set profile pictuire
        user.setProfilePic(logUser.getProfilePic());

        userDaoImpl.saveUser(user);

        return "redirect:/profile/userProfile/0";
    }

    @PostMapping("/saveProfilePic")
    public String updateUserProfile(@RequestParam("profilePics") MultipartFile file, Principal principal) {

        User logUser = userDaoImpl.userByEmail(principal.getName());

        User user = logUser;

        // user.setProfilePic(multipartFile.getOriginalFilename());

        if (!file.isEmpty()) {
            try {
                String uniqueName = LocalTime.now().toString().replace(':', '_').concat("@Books");

                // setting file in userpost!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                user.setProfilePic(uniqueName.concat(file.getOriginalFilename()));

                boolean uploadFile = profileUploadHelper.uploadFile(file, uniqueName);

                if (uploadFile) {
                    System.out.println("Done");
                } else {
                    System.out.println("Error");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // updating updated time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        user.setUpdatedAt(formattedDate);

        userDaoImpl.saveUser(user);

        return "redirect:/profile/userProfile/0";
    }

    @RequestMapping("/deletePost/{id}")
    public String deletePostById(@PathVariable("id") Long id) {

        userPostImpl.deletePostById(id);

        return "redirect:/profile/userProfile/0";
    }


}
