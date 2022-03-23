package org.datavaultplatform.common.model.dao;

import java.util.List;
import org.datavaultplatform.common.model.Audit;

public interface AuditDAO {
    void save(Audit audit);

    void update(Audit audit);

    List<Audit> list();

    Audit findById(String Id);

    int count();
}
