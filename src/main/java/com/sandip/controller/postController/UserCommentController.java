package com.sandip.controller.postController;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sandip.entity.User;
import com.sandip.entity.UserComment;
import com.sandip.entity.UserPost;
import com.sandip.service.UserCommentDaoImpl;

@Controller
@RequestMapping("/comment")
public class UserCommentController {

    @Autowired
    private UserCommentDaoImpl userCommentDaoImpl;

    @PostMapping("/save/userComment/{postId}/{page}")
    public String saveUserCommentData(@PathVariable("postId") Long postId, @PathVariable("page") Long page,
            @RequestParam(required = false, name = "categoryName") String categoryName,
            @ModelAttribute("UserComment") UserComment userComment, RedirectAttributes redirectAttributes) {

        // setting postId in userPost
        UserPost userPost = new UserPost();
        userPost.setPostId(postId);
        // set userpost in comment for you are commenting on what post
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        userComment.setPost(userPost);

        // setting userId in user for setting user in comment temporary
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        User user = new User();
        user.setUserId(13);
        // set user in comment: who commented
        userComment.setUser(user);

        // save time in database
        // new post set time and date------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();

        userComment.setCommentAt(formattedDate);

        userCommentDaoImpl.saveComment(userComment);
        redirectAttributes.addAttribute("posterrorMessage", "Commented !!");
        // for staying same location after commenting and reloading
        redirectAttributes.addAttribute("categoryName", categoryName);

        return "redirect:/post/home/" + page;
    }

}
