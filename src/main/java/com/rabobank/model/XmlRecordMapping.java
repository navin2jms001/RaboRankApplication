package com.rabobank.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Navin 
 * XML Mapping Object
 */

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRecordMapping {
	
	@XmlElement(name = "record")
	private List<CustomerRecord> records = null;

	public List<CustomerRecord> getRecords() {
		return this.records;
	}

	public void setRecords(List<CustomerRecord> records) {
		this.records = records;
	}

}
