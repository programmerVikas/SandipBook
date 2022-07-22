package com.sandip.controller.postController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandip.entity.Event;
import com.sandip.entity.User;
import com.sandip.service.EventDaoImpl;
import com.sandip.service.UserDaoImpl;

@Controller
@RequestMapping("/event")
public class EventController {
    

    @Autowired
    private EventDaoImpl eventDaoImpl;

    @Autowired
    private UserDaoImpl userDaoImpl;


    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute("event") Event event, Principal principal){


        User logUser = userDaoImpl.userByEmail(principal.getName());

        // setting login user who is making event
        event.setUser(logUser);

        eventDaoImpl.saveEvent(event);

        return "redirect:/profile/userProfile/0";
    }


    @RequestMapping("/deleteEvent/{id}")
    public String deleteEventById(@PathVariable("id") Long id, @RequestParam(value = "null", defaultValue = "null") String eventCard) {
        
        eventDaoImpl.deleteEvent(id);


        if(!eventCard.equals("null")){
            return "redirect:/profile/userProfile/0";
        }

        return "redirect:/post/home/0";
    }



}
