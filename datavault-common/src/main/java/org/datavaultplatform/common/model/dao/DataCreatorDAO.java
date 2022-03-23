package org.datavaultplatform.common.model.dao;

import java.util.List;
import org.datavaultplatform.common.model.DataCreator;

public interface DataCreatorDAO {
    void save(List<DataCreator> dataCreators);

    DataCreator findById(String Id);

    void update(DataCreator dataCreator);

    void delete(String id);
}
