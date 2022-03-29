package org.datavaultplatform.webapp.controllers;

import java.util.List;
import org.datavaultplatform.common.request.CreateClientEvent;
import org.datavaultplatform.webapp.services.NotifyLogoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    Logger LOG = LoggerFactory.getLogger(LogoutListener.class);

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
                //TODO - this method returns a string which we are ignoring
                service.notifyLogout(clientEvent);
            }catch (RuntimeException ex){
                LOG.error("Error when notifying Logout to Broker!",ex);
            }
        }
    }
}
