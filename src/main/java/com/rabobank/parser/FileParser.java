package com.rabobank.parser;

import java.io.IOException;
import java.util.List;
import com.rabobank.model.CustomerRecord;

public interface FileParser {
	public List<CustomerRecord> parse() throws Exception; 
}
