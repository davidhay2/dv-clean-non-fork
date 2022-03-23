package org.datavaultplatform.common.model.dao;

import java.util.List;
import org.datavaultplatform.common.model.VaultReview;

public interface VaultReviewDAO {

    public void save(VaultReview vaultReview);
    
    public void update(VaultReview vaultReview);
    
    public List<VaultReview> list();

    public VaultReview findById(String Id);

    public List<VaultReview> search(String query);

    public int count();
}
