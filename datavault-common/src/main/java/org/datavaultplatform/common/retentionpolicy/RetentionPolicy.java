package org.datavaultplatform.common.retentionpolicy;

import java.util.Date;
import org.datavaultplatform.common.model.Vault;

public interface RetentionPolicy {

    /**
     * Execute the policy on a Vault
     */
    public int run(Vault v);

    /**
     * Get the current review date of the policy
     */
    public Date getReviewDate(Vault v);
}
