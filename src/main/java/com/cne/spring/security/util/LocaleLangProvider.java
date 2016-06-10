package com.cne.spring.security.util;

import java.util.Locale;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 
 * @author lenovo
 *
 */
public class LocaleLangProvider {
    
    /**
     * 
     * @param localeKey
     */
    public static void setLocaleLanguage(String localeKey) {
        
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("springDispatcher-servlet.xml");
        SessionLocaleResolver locale = context.getBean("localeResolver", SessionLocaleResolver.class);
        
        if (localeKey != null) {
            String ln; //language
            String cn = null; //country
            
            if (localeKey.contains("_")) {
                ln = localeKey.substring(0, localeKey.indexOf("_"));
                cn = localeKey.substring(localeKey.indexOf("_") + 1);
            } else {
                ln = localeKey;
            }
            
            if (cn != null) {
                locale.setDefaultLocale(new Locale(cn, ln));
            } else {
                locale.setDefaultLocale(new Locale(ln));
            }
        }
        context.close();
    }
}
