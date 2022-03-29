package org.datavaultplatform.webapp.controllers;

import java.util.List;
import org.datavaultplatform.common.request.CreateClientEvent;
import org.datavaultplatform.webapp.services.NotifyLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {
    
    private final NotifyLogoutService service;

    @Autowired
    public LogoutListener(NotifyLogoutService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {        
        List<SecurityContext> contexts = event.getSecurityContexts();
        
        for (SecurityContext context : contexts)
        {
            Authentication auth = context.getAuthentication();
            
            // Get some details from the context
            WebAuthenticationDetails details = (WebAuthenticationDetails)auth.getDetails();
            String remoteAddress = details.getRemoteAddress();
            CreateClientEvent clientEvent = new CreateClientEvent(remoteAddress, null);
            
            // Log the event with the broker
            try {
                service.notifyLogout(clientEvent);
            }catch (Exception e){
                System.err.println("Error when notifying Logout to Broker!");
            }
        }
    }
}
