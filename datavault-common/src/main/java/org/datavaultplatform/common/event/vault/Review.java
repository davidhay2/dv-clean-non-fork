package org.datavaultplatform.common.event.vault;

import javax.persistence.Entity;
import org.datavaultplatform.common.event.Event;

@Entity
public class Review extends Event {

    Review() {};
    public Review(String vaultId) {
        super("Reviewed vault");
        this.eventClass = Review.class.getCanonicalName();
        this.vaultId = vaultId;
    }
}
