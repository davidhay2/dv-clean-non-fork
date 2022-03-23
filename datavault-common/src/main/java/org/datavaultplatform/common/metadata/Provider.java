package org.datavaultplatform.common.metadata;

import java.util.List;
import java.util.Map;
import org.datavaultplatform.common.model.Dataset;

/*
A generic interface for an external metadata provider (e.g. a CRIS system).
A provider is expected to return details of dataset metadata so that an
external metadata record can be linked to a deposit.
*/

public interface Provider {
    public List<Dataset> getDatasetsForUser(String userID);
    public Dataset getDataset(String id);
	public Map<String, String> getPureProjectIds();
	public String getPureProjectId(String datasetId);
}
