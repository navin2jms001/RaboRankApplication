package com.rabobank.parser;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import com.rabobank.model.CustomerRecord;

/**
 * Created by Navin CSV File Parser
 */
public class CsvFileParserImpl implements FileParser {

	File file;
	static Logger logger = Logger.getLogger(CsvFileParserImpl.class.getName());

	public CsvFileParserImpl(File file) {
		this.file = file;
	}

	public List<CustomerRecord> parse() throws Exception {
        logger.info("Entry CSV Parser");
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		List<CustomerRecord> records = new ArrayList<>();
		CSVParser parser = null;
		try {
			parser = new CSVParser(new FileReader(this.file), format);

			for (CSVRecord entry : parser) {
				CustomerRecord record = new CustomerRecord();
				record.setTransactionRefNum(Integer.parseInt(entry.get("Reference")));
				record.setAccountNumber(entry.get("AccountNumber"));
				record.setDescription(entry.get("Description"));
				record.setStartBalance(new BigDecimal(entry.get("Start Balance")));
				record.setMutation(new BigDecimal(entry.get("Mutation")));
				record.setEndBalance(new BigDecimal(entry.get("End Balance")));
				records.add(record);
			}
			
			logger.info("Exit CSV Parser");
		}

		finally {
			if (parser != null)
				parser.close();
		}

		return records;
	}

}
