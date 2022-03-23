package org.datavaultplatform.common.event.roles;

import javax.persistence.Entity;
import org.datavaultplatform.common.event.Event;
import org.datavaultplatform.common.model.RoleAssignment;

@Entity
public class UpdateRoleAssignment extends Event {

    UpdateRoleAssignment() {};
    public UpdateRoleAssignment(RoleAssignment roleAssignment, String userId) {
        super(roleAssignment.getUserId()+" role has been change to "+roleAssignment.getRole().getName()+ " by "+userId);
        this.eventClass = UpdateRoleAssignment.class.getCanonicalName();
    }
}
