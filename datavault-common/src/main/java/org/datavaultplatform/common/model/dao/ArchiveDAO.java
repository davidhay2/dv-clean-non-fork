package org.datavaultplatform.common.model.dao;


import java.util.List;
import org.datavaultplatform.common.model.Archive;

public interface ArchiveDAO {

    public void save(Archive archive);

    public void update(Archive archive);

    public List<Archive> list();

    public Archive findById(String Id);

}
