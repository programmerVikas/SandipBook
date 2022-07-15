package com.sandip.controller.postController;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;

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
import com.sandip.mailConfiguration.UserCommentMailConfiguration;
import com.sandip.service.UserCommentDaoImpl;
import com.sandip.service.UserDaoImpl;
import com.sandip.service.UserPostImpl;

@Controller
@RequestMapping("/comment")
public class UserCommentController {

    @Autowired
    private UserCommentDaoImpl userCommentDaoImpl;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserCommentMailConfiguration userCommentMailConfiguration;

    @Autowired
    private UserPostImpl userPostImpl;

    @PostMapping("/save/userComment/{postId}/{page}")
    public String saveUserCommentData(@PathVariable("postId") Long postId, @PathVariable("page") Long page,
            @RequestParam(required = false, name = "categoryName") String categoryName,
            @RequestParam(required = false, name = "profileComment") String profileComment,
            @RequestParam(required = false, name = "userId") String unknownUserId,
            @ModelAttribute("UserComment") UserComment userComment, RedirectAttributes redirectAttributes,
            Principal principal) {

        // getting current login user
        User logUser = userDaoImpl.userByEmail(principal.getName());

        // setting postId in userPost
        UserPost userPost = userPostImpl.getPostById(postId);
        // set userpost in comment for you are commenting on what post
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        userComment.setPost(userPost);

        // setting userId in user for setting user in comment temporary
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        User user = new User();
        user.setUserId(logUser.getUserId());
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

        if (profileComment != null) {
            if (profileComment.equals("profileComment")) {
                return "redirect:/profile/userProfile/0";
            }
        }

        try {
            userCommentMailConfiguration.sendCommentMail(
                    userComment.getComment(), "SandipBook-Comments",
                    userPost.getUser().getEmail(), logUser.getEmail(),
                    logUser.getFirstName() + " " +
                            logUser.getLastName(),
                    userPost.getPost() == null || userPost.getPost().equals("")
                            ? userPost.getPhoto().substring(userPost.getPhoto().indexOf("@Books") + 6,
                                    userPost.getPhoto().length())
                            : userPost.getPost());
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (profileComment != null) {

            if (profileComment.equals("unknownprofileComment")) {
                return "redirect:/profile/unknownUserProfile/" + unknownUserId + "/" + 0;
            }

        }

        return "redirect:/post/home/" + page;
    }

}
