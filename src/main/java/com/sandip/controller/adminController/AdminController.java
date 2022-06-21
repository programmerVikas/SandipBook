package com.sandip.controller.adminController;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandip.entity.Category;
import com.sandip.entity.Course;
import com.sandip.entity.CourseBranch;
import com.sandip.service.BranchDaoImpl;
import com.sandip.service.CategoryDaoImpl;
import com.sandip.service.CourseDaoImpl;
import com.sandip.sorting.SortingByCourseName;
// import com.sandip.service.CourseDaoImpl;
import com.sandip.staticData.ConstantData;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @Autowired
    private CourseDaoImpl courseDaoImpl;

    @Autowired
    private BranchDaoImpl branchDaoImpl;

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @RequestMapping("/home")
    public String goToAdmin(Model model) {
        model.addAttribute("select", ConstantData.SELECT);
        return "adminSpace/admin";
    }

    // course mapping is going on
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping("/course/{page}")
    public String addCourses(@PathVariable("page") Integer page,
            @RequestParam(required = false, name = "courseMess") String courseMess,
            @RequestParam(required = false, name = "coursecardtitle") String coursecardtitle,
            @ModelAttribute(name = "courseData") Course courseData, Model model) {

        model.addAttribute("coursecardtitle",
                (coursecardtitle == null) ? ConstantData.COURSE_CARD_TITLE : coursecardtitle);

        // for css select navigation side bar , for highlight current tab in side bar
        model.addAttribute("select2", ConstantData.SELECT);
        // printing error message on page which is coming from course_controller ->
        // savedata handling method
        model.addAttribute("courseMess", courseMess);

        // pagination has started !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Pageable pageable = PageRequest.of(page, ConstantData.PAGE_SIZE);
        Page<Course> pageCourseDataList = courseDaoImpl.gettingCourseData(pageable);
        // storing all course data in list from database
        List<Course> courseDataList = pageCourseDataList.getContent();
        model.addAttribute("courseDataList", courseDataList);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageCourseDataList.getTotalPages());
        // pagination has ended!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return "adminSpace/addCourses";
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // here we will use mapping for getting page no.
    @GetMapping("/category/{page}")
    public String addCategory(@PathVariable("page") Integer page,
            @RequestParam(required = false, name = "categoryMess") String categoryMess,
            @RequestParam(required = false, name = "catDataname") String catDataname,
            @RequestParam(required = false, name = "catcardtitle") String catcardtitle,
            Model model) {

        // setting the title of category card on the time of saving new data and
        // updating data
        model.addAttribute("catcardtitle", (catcardtitle == null) ? ConstantData.CATEGORY_CARD_TITLE : catcardtitle);
        model.addAttribute("select3", ConstantData.SELECT);
        model.addAttribute("categoryMess", categoryMess); // here requestparam getting message from the server which is
                                                          // in category-controller and showing on category page
        model.addAttribute("catDataname", catDataname); // setting value in input box for updation
        // pagination has started !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Pageable pageable = PageRequest.of(page, ConstantData.PAGE_SIZE);
        Page<Category> pagecategoryDataList = categoryDaoImpl.gettingCategoryData(pageable);
        // storing all ategory data in list from database
        List<Category> categoryDataList = pagecategoryDataList.getContent();

        model.addAttribute("categoryData", categoryDataList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagecategoryDataList.getTotalPages());
        // pagination has ended!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return "adminSpace/addCategory";
    }

    // here id for staying at same page after saving data table data wil not
    // changeOr will not showed from start!!!!!
    @RequestMapping("/branch/{id}")
    public String addBranch(@PathVariable("id") Long id,
            @RequestParam(required = false, name = "branchMess") String branchMess,
            @ModelAttribute(name = "branchData") CourseBranch BranchData,
            @RequestParam(required = false, name = "branchcardtitle") String branchcardtitle,
            Model model) {



         // setting the title of category card on the time of saving new data and
        // updating data
        model.addAttribute("branchcardtitle", (branchcardtitle == null) ? ConstantData.BRANCH_CARD_TITLE : branchcardtitle);

        model.addAttribute("select1", ConstantData.SELECT);

        List<Course> gettingCourseDataList = courseDaoImpl.gettingCourseDataList();
        // course sorting by corse name
        Collections.sort(gettingCourseDataList, new SortingByCourseName());
        model.addAttribute("courseDropDownList", gettingCourseDataList);

        model.addAttribute("branchMess", branchMess);

        // getting a course data by id for getting branch data by course
        Course course = courseDaoImpl.getCourseById((id == 0) ? gettingCourseDataList.get(0).getCourseId() : id);
        List<CourseBranch> gettingBranchByCourse = branchDaoImpl.gettingBranchByCourse(course);
        model.addAttribute("branchTableData", gettingBranchByCourse);
        model.addAttribute("courseName", course.getCourseName());
        // sending id on page for staying same location after having saving data
        model.addAttribute("stayId", (id == 0) ? gettingCourseDataList.get(0).getCourseId() : id);

        return "adminSpace/addBranch";
    }

}
