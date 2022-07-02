package com.sandip.controller.postController;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
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

    @RequestMapping("/home/{page}")
    public String postHomePage(@PathVariable("page") Integer page,
            @RequestParam(required = false, name = "posterrorMessage") String posterrorMessage,
            @RequestParam(required = false, name = "categoryName") String categoryName,
            Model model) {

        // List<UserPost> findByCategoryData =
        // userPostImpl.findByCategoryData(categoryDaoImpl.gettingDataByName("public"));

        // pagination has started !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Pageable pageable = PageRequest.of(page, 10, Sort.by("postId").descending());

        Page<UserPost> findByCategoryDataPagination = userPostImpl.findByCategoryDataPagination(pageable,
                categoryDaoImpl.gettingDataByName(categoryName == null ? "public" : categoryName));

        model.addAttribute("postData", findByCategoryDataPagination);
        // System.out.println(findByCategoryDataPagination.getSize()+":OMG:"+findByCategoryDataPagination.getTotalPages()+":"+page);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", findByCategoryDataPagination.getTotalPages());

        model.addAttribute("navbarCategoryData", categoryDaoImpl.gettingCategory());
        model.addAttribute("posterrorMessage", posterrorMessage);
        // for getting post data on the basis of selected catyegory
        model.addAttribute("categoryName", categoryName == null ? "public" : categoryName);

        return "postSpace/postHome";
    }

    @PostMapping("/savePost/{page}")
    public String savePostData(@PathVariable("page") Integer page, @ModelAttribute UserPost userPost,
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false, name = "categoryName") String categoryName,
            RedirectAttributes redirectAttributes) {

        if (userPost.getPost().isEmpty() && file.isEmpty()) {
            redirectAttributes.addAttribute("posterrorMessage", "Invalid Data....");
            redirectAttributes.addAttribute("categoryName", categoryName);
            return "redirect:/post/home/" + page;
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
                String uniqueName = LocalTime.now().toString().replace(':', '_').concat("@Books");

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
        redirectAttributes.addAttribute("posterrorMessage", "You posted a new post !!");
        // we are changing url our category eill take default category(public) so i'm
        // setting category which is coming from the page
        redirectAttributes.addAttribute("categoryName", categoryName);

        return "redirect:/post/home/" + page;
    }

}
