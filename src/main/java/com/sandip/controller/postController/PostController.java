package com.sandip.controller.postController;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sandip.entity.UserPost;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.staticData.FileUploadHelper;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @RequestMapping("/home")
    public String postHomePage(Model model) {

        model.addAttribute("navbarCategoryData", categoryDaoImpl.gettingCategory());

        return "postSpace/postHome";
    }

    @PostMapping("/savePost")
    public String savePostData(@ModelAttribute UserPost userPost, @RequestParam("file") MultipartFile file) {

        // new post set time and date------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
        // set time when user post
        userPost.setPostOn(formattedDate);

        // file handling code

        try {
            boolean uploadFile = fileUploadHelper.uploadFile(file);
            if (uploadFile) {
                System.out.println("Done");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        // save file code here

        return "redirect:/post/home";
    }

}
