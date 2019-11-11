package com.rabobank.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.rabobank.model.CustomerRecord;
import com.rabobank.model.RaboAppResponse;
import com.rabobank.model.ResultRecord;
import com.rabobank.parser.FileParserFactory;
import com.rabobank.util.RaboAppConstants;

/**
 * Created by Navin 
 * Service to implement business logic
 */
@Service
public class RaboAppServiceImpl implements RaboAppService {

	List<CustomerRecord> customerRecordList = null;
	List<CustomerRecord> invalidRecordList = null;
	List<ResultRecord> finalReportList = null;

	@Override
	public RaboAppResponse processRecordsAndGenerateReport(String processType) {
		finalReportList = new ArrayList<>();
		try {
			customerRecordList = extractRecords(processType, null);
			finalReportList = validateRecords(customerRecordList);
		} catch (Exception e) {
			return new RaboAppResponse(RaboAppConstants.HTTP_CODE_ERROR, e.getMessage(), null);
		}
		return new RaboAppResponse(RaboAppConstants.HTTP_CODE_SUCCESS, "Success", finalReportList);
	}

	@Override
	public RaboAppResponse processRecordsAndGenerateRestReport(String filePath, String contentType) {
		finalReportList = new ArrayList<>();
		try {
			customerRecordList = extractRecords(contentType, filePath);
			finalReportList = validateRecords(customerRecordList);
		} catch (Exception e) {
			return new RaboAppResponse(RaboAppConstants.HTTP_CODE_ERROR, RaboAppConstants.SERVICE_ERROR_MESSAGE, null);
		}
		return new RaboAppResponse(RaboAppConstants.HTTP_CODE_SUCCESS, "Success", finalReportList);
	}

	public List<CustomerRecord> extractRecords(String processType, String filePath) throws Exception {
		customerRecordList = new ArrayList<>();

		if (processType.equalsIgnoreCase(RaboAppConstants.CONTENT_TYPE_BOTH)) {
			Resource csvResource = new ClassPathResource("records.csv");
			File csvFile = csvResource.getFile();
			Resource xmlResource = new ClassPathResource("records.xml");
			File xmlFile = xmlResource.getFile();
			customerRecordList.addAll(FileParserFactory.getParserFactory(csvFile).parse());
			customerRecordList.addAll(FileParserFactory.getParserFactory(xmlFile).parse());

		} else if (processType.equalsIgnoreCase(RaboAppConstants.CONTENT_TYPE_CSV)
				|| processType.equalsIgnoreCase(RaboAppConstants.FILE_TYPE_CSV)) {
			if (filePath != null) {
				customerRecordList.addAll(FileParserFactory.getParserFactory(new File(filePath)).parse());
			} else {
				Resource csvResource = new ClassPathResource("records.csv");
				File csvFile = csvResource.getFile();
				customerRecordList.addAll(FileParserFactory.getParserFactory(csvFile).parse());
			}
		}

		else if (processType.equalsIgnoreCase(RaboAppConstants.CONTENT_TYPE_XML)
				|| processType.equalsIgnoreCase(RaboAppConstants.FILE_TYPE_XML)) {
			if (filePath != null) {
				customerRecordList.addAll(FileParserFactory.getParserFactory(new File(filePath)).parse());
			} else {
				Resource xmlResource = new ClassPathResource("records.xml");
				File xmlFile = xmlResource.getFile();
				customerRecordList.addAll(FileParserFactory.getParserFactory(xmlFile).parse());
			}
		}

		else {
			throw new Exception("Invalid File Type to process");
		}

		return customerRecordList;
	}

	public List<ResultRecord> validateRecords(List<CustomerRecord> records) throws Exception {
		finalReportList = new ArrayList<>();
		finalReportList.addAll(findDuplicateTransactionRefRecords(records).stream().map(record -> {
			ResultRecord resultRecord = new ResultRecord();
			resultRecord.setTransactionRefNum(record.getTransactionRefNum());
			resultRecord.setTransactionDescription(record.getDescription());
			resultRecord.setRaboComments(RaboAppConstants.DUPLICATE_TXN_MESSAGE);
			return resultRecord;
		}).collect(Collectors.toList()));

		finalReportList.addAll(findInvalidMutationRecords(records).stream().map(record -> {
			ResultRecord resultRecord = new ResultRecord();
			resultRecord.setTransactionRefNum(record.getTransactionRefNum());
			resultRecord.setTransactionDescription(record.getDescription());
			resultRecord.setRaboComments(RaboAppConstants.INVALID_END_BALANCE);
			return resultRecord;
		}).collect(Collectors.toList()));

		return finalReportList;
	}

	/**
	 * @return : the list of duplicate transaction records
	 */
	public List<CustomerRecord> findDuplicateTransactionRefRecords(List<CustomerRecord> records) {
		Map<Integer, List<CustomerRecord>> recordsMap = new HashMap<Integer, List<CustomerRecord>>();
		List<CustomerRecord> customerRecords = null;
		for (CustomerRecord record : records) {
			if (recordsMap.containsKey(record.getTransactionRefNum())) {
				recordsMap.get(record.getTransactionRefNum()).add(record);
			} else {
				customerRecords = new ArrayList<>();
				customerRecords.add(record);
				recordsMap.put(record.getTransactionRefNum(), customerRecords);
			}
		}

		return recordsMap.entrySet().stream().filter(obj -> obj.getValue().size() > 1)
				.flatMap(record -> record.getValue().stream()).collect(Collectors.toList());
	}

	/**
	 * @return : the list of invalid mutation records
	 */
	public List<CustomerRecord> findInvalidMutationRecords(List<CustomerRecord> records) {
		List<CustomerRecord> invalidMutationsRecords = new ArrayList<>();
		for (CustomerRecord record : records) {
			if (record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()) != 0)
				invalidMutationsRecords.add(record);
		}
		return invalidMutationsRecords;
	}

}
