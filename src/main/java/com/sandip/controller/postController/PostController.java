package com.sandip.controller.postController;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sandip.entity.User;
import com.sandip.entity.UserPost;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.service.UserPostImpl;
import com.sandip.staticData.FileUploadHelper;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @Autowired
    private UserPostImpl userPostImpl;

    // @Autowired
    // private User user;

    @RequestMapping("/home")
    public String postHomePage(@RequestParam(required = false, name = "posterrorMessage") String posterrorMessage,
            Model model) {

        model.addAttribute("navbarCategoryData", categoryDaoImpl.gettingCategory());

        model.addAttribute("posterrorMessage", posterrorMessage);

        return "postSpace/postHome";
    }

    @PostMapping("/savePost")
    public String savePostData(@ModelAttribute UserPost userPost, @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (userPost.getPost().isEmpty() && file.isEmpty()) {
            redirectAttributes.addAttribute("posterrorMessage", "Invalid Data....");
            return "redirect:/post/home";
        }

        // new post set time and date------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        // set time when user post
        userPost.setPostOn(formattedDate);

        // setting id in user temporary
        User um = new User();
        um.setUserId(273);
        userPost.setUser(um);

        if (!file.isEmpty()) {
            try {
                String uniqueName = LocalTime.now().toString().replace(':', '_');

                // setting file in userpost!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                userPost.setPhoto(uniqueName.concat(file.getOriginalFilename()));

                boolean uploadFile = fileUploadHelper.uploadFile(file, uniqueName);

                if (uploadFile) {
                    System.out.println("Done");
                } else {
                    System.out.println("Error");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        userPostImpl.savePost(userPost);

        return "redirect:/post/home";
    }

}
