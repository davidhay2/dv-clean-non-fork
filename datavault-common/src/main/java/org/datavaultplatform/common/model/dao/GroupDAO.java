package org.datavaultplatform.common.model.dao;

import java.util.List;
import org.datavaultplatform.common.model.Group;

public interface GroupDAO {

    public void save(Group group);
    
    public void update(Group group);

    public void delete(Group group);

    public List<Group> list();

    public List<Group> list(String userId);

    public Group findById(String Id);

    public int count(String userId);
}
