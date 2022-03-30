package org.datavaultplatform.webapp.services;

import org.datavaultplatform.common.request.CreateClientEvent;

public interface NotifyLogoutService {
  String notifyLogout(CreateClientEvent clientEvent);
}
