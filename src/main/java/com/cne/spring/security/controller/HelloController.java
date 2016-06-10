package com.cne.spring.security.controller;

import java.util.Locale;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cne.spring.security.util.LocaleLangProvider;

@Controller
public class HelloController {
    
    @Autowired
    private MessageSource messageSource;
    
    public HelloController() {
        //Default Constructor
    }
    
    @RequestMapping(value = {"/", "/index**"}, method = RequestMethod.GET)
    public String welcomePage(@RequestParam String locale, ModelMap model) {
        
        System.out.println("Loading Index Page...");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null) {
           
            String title = messageSource.getMessage("lbl.title", null, new Locale(locale));
            
            if (locale.equalsIgnoreCase("zh")) {
                title = messageSource.getMessage("lbl.title", null, new Locale("zh", "CH"));
            } 
            
            model.addAttribute("title", title);
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
    
    @RequestMapping(value = "/testlocale", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody JSONObject testResponsePayload() {
        JSONObject responsePayload = new JSONObject();
        responsePayload.put("title", messageSource.getMessage("lbl.title", null, new Locale("zh", "CH")));
        
        return responsePayload;
    }
    
}
