package com.sandip.controller.postController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandip.entity.User;
import com.sandip.entity.UserPost;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.service.UserDaoImpl;
import com.sandip.service.UserPostImpl;

@Controller
@RequestMapping("/profile")
public class UnknownUserProfileController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserPostImpl userPostImpl;

    @RequestMapping("/unknownUserProfile/{id}/{page}")
    public String gettingUnknownUserData(@PathVariable("id") Long id, @PathVariable("page") int page, Model model,
            Principal principal) {

        model.addAttribute("navbarCategoryData", categoryDaoImpl.gettingCategory());
        // getting current login user
        User logUser = userDaoImpl.userByEmail(principal.getName());
        model.addAttribute("logUser", logUser);

        User unknownUser = userDaoImpl.userById(id);
        model.addAttribute("unknownUser", unknownUser);

        // getting post data by user
        Pageable pageable = PageRequest.of(page, 5, Sort.by("postId").descending());

        // making object and setting id in it for getting post data by user
        User user = new User();
        user.setUserId(unknownUser.getUserId());
        Page<UserPost> findByUserDataPagination = userPostImpl.findPostByUser(user, pageable);

        model.addAttribute("postData", findByUserDataPagination);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", findByUserDataPagination.getTotalPages());

        return "postSpace/unknownUserProfile";
    }

}
