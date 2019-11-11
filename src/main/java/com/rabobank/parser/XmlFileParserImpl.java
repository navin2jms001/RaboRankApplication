package com.rabobank.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import com.rabobank.model.CustomerRecord;
import com.rabobank.model.XmlRecordMapping;

/**
 * Created by Navin 
 * XML File Parser
 */
public class XmlFileParserImpl implements FileParser {

	File file;
	static Logger logger = Logger.getLogger(XmlFileParserImpl.class.getName());

	public XmlFileParserImpl(File file) {
		this.file = file;
	}

	@Override
	public List<CustomerRecord> parse() throws Exception {
		logger.info("Entry XML Parser");
		List<CustomerRecord> records = new ArrayList<>();
		JAXBContext jaxbContext = JAXBContext.newInstance(XmlRecordMapping.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		XmlRecordMapping xmlRecordList = (XmlRecordMapping) jaxbUnmarshaller.unmarshal(this.file);
		records = xmlRecordList.getRecords();
		logger.info("Exit XML Parser");
		return records;
	}

}
