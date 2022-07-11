package com.sandip.controller.ExceptionHandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errorHandler")
public class AccessDeniedController {
    

    @RequestMapping("/access_denied")
    public String accessDeniedHandler() {
        return "access_denied";
    }


}
