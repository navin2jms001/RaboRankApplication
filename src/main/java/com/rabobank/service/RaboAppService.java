package com.rabobank.service;

import com.rabobank.model.RaboAppResponse;

/**
 * Created by Navin 
 * XML File Parser
 */
public interface RaboAppService {

	public RaboAppResponse processRecordsAndGenerateReport(String processType);
	
	public RaboAppResponse processRecordsAndGenerateRestReport(String filePath, String contentType);
	
}
