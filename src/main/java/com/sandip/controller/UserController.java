package com.sandip.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
// import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailAuthenticationException;
// import java.text.SimpleDateFormat;
// import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sandip.entity.User;
import com.sandip.entity.UserRole;
import com.sandip.mailConfiguration.MailConfiguration;
import com.sandip.service.UserDaoImpl;
import com.sandip.service.UserRoleImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    @Lazy
    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private UserRoleImpl userRoleImpl;

    // mail sending wiring************************************
    @Autowired
    private MailConfiguration mailConfiguration;
    // mail sending wiring end************************************

    String name = "";
    String email = "";
    String password = "";

    @RequestMapping(value = "/otpVerify", method = RequestMethod.POST)
    public String mailVerify(@RequestParam("firstName") String username, @RequestParam("email") String email,
            @RequestParam("password") String password, HttpServletRequest request, Model model) {


        this.name = username;
        this.email = email;
        this.password = password;

        // varifying user data----------------------------------------------------
        if (email == "") {
            model.addAttribute("errorMsg2", "Not valid !! ");
            return "register";
        } else if(username == ""){
            model.addAttribute("errorMsg4", "Not valid !! ");
            return "register";
        }
        else if (password == "") {
            model.addAttribute("errorMsg3", "Not valid !! ");
            return "register";
        } 
        else if (password.length() < 6) {
            model.addAttribute("errorMsg3", "Password should be strong. password should be atleast 6 character long");
            return "register";
        }
        // varifying user data----------------------------------------------------

        try {
            if (userDaoImpl.userByEmail(email) == null) {

                // OTP generating ****************************************************
                Random rand = new Random();
                String otp = String.format("%04d", rand.nextInt(10000));

                // sending OTP on mail
                mailConfiguration.sendOTPMail(otp, "mail verification", email);
                // sending OTP on mail end

                // setting session*********************************************************
                HttpSession session = request.getSession();
                session.setAttribute("OTP", otp);
                // OTP complete ******************************************************

                model.addAttribute("mail", email.replaceAll("(^[^@]{3}|(?!^)\\G)[^@]", "$1*"));

                return "mailVerification";

            } else {
                model.addAttribute("errorMsg", "Already registered");
                // return "register";
            }

        } catch (MailAuthenticationException e) {
            model.addAttribute("errorMsg", "admin : mail AuthenticationFailed, check your less secure app ");
        } catch (Exception e) {
            System.out.println("Lol "+e);
            model.addAttribute("errorMsg", "Already registered");
            // return "register";
        }
        return "register";

    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request, @RequestParam("otp") String otp, Model model,
            RedirectAttributes redirectAttributes) {

        // getting data from session**************************************
        HttpSession session = request.getSession();
        String secureOtp = (String) session.getAttribute("OTP");
        // otp verification********************************************
        if (!otp.equals(secureOtp)) {
            model.addAttribute("WrongOtp", "Invalid OTP ");
            return "mailVerification";
        }

        // saving data *****************************************************************

        User user = new User();
        user.setFirstName(name);
        user.setEmail(email);
        user.setPassword(password);

        // user registration set time and date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy 'at' hh:mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();

        user.setRegistereddAt(formattedDate);

        // getting user data and setting data in user role object
        UserRole userRole = new UserRole();
        userRole.setRoleId(userRoleImpl.gettingUserRole("normal").getRoleId());

        // setting user role data in user object
        user.setUser_role(userRole);

        // saving signup data in
        // database*************************************************
        userDaoImpl.saveUser(user);

        redirectAttributes.addAttribute("signUpSuccess", "Sign up successfully");
        // going back on signin/login page
        return "redirect:/login";

    }

}

// checking coming registration data is valid or not
// making mail varifivation page

// OTP module creating date is : 02-03-2022