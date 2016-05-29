package com.cne.spring.security.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    
    public HelloController() {
        //Default Constructor
    }
    
    @RequestMapping(value = {"/", "/index**"}, method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
        
        System.out.println("Loading Index Page...");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null) {
            model.addAttribute("title", "Spring Security Demo");
            model.addAttribute("message", "This is a welcome page!");
            
            StringBuilder sb = new StringBuilder();
            sb.append("Username: " + authentication.getName());
            
            for(GrantedAuthority authority : authentication.getAuthorities()) {
                sb.append(" Role: " + authority.getAuthority());
            }
            
            System.out.println("UserInfo: " + sb.toString());
            
            model.addAttribute("userInfo", sb.toString());
            model.addAttribute("principal", authentication.getPrincipal());
            model.addAttribute("userName", authentication.getName());
        }
        
        /*UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);

        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);*/
        
        return "index";
    }
    
    
}
