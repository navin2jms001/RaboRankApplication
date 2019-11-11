package com.rabobank;


import java.math.BigDecimal;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rabobank.model.CustomerRecord;
import com.rabobank.service.RaboAppServiceImpl;

public class RaboAppServiceImplTest {
	List<CustomerRecord> customerRecordList = null;
	RaboAppServiceImpl raboAppService = null;

	@Before
	public void setUp() throws Exception {
		customerRecordList = new ArrayList<>();
		CustomerRecord record = null;

		record = new CustomerRecord();
		record.setTransactionRefNum(1001);
		record.setAccountNumber("100201901");
		record.setDescription("Purchase1");
		record.setStartBalance(new BigDecimal(100));
		record.setMutation(new BigDecimal(-50));
		record.setEndBalance(new BigDecimal(50));
		customerRecordList.add(record);

		record = new CustomerRecord();
		record.setTransactionRefNum(1001);
		record.setAccountNumber("100201902");
		record.setDescription("Purchase2");
		record.setStartBalance(new BigDecimal(400));
		record.setMutation(new BigDecimal(-50));
		record.setEndBalance(new BigDecimal(350));
		customerRecordList.add(record);

		record = new CustomerRecord();
		record.setTransactionRefNum(1002);
		record.setAccountNumber("100201903");
		record.setDescription("Purchase3");
		record.setStartBalance(new BigDecimal(200));
		record.setMutation(new BigDecimal(50));
		record.setEndBalance(new BigDecimal(220));
		customerRecordList.add(record);
		
		record = new CustomerRecord();
		record.setTransactionRefNum(1003);
		record.setAccountNumber("100201904");
		record.setDescription("Purchase4");
		record.setStartBalance(new BigDecimal(1000));
		record.setMutation(new BigDecimal(500));
		record.setEndBalance(new BigDecimal(1500));
		customerRecordList.add(record);

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testFindDuplicateTransactionRefRecords() {
		raboAppService = new RaboAppServiceImpl();
		List<CustomerRecord> invalidRecords = raboAppService.findDuplicateTransactionRefRecords(customerRecordList);
		assertEquals(invalidRecords.size(), 2);
	}

	@Test
	public void testFindInvalidMutations() {
		raboAppService = new RaboAppServiceImpl();
		List<CustomerRecord> invalidRecords = raboAppService.findInvalidMutationRecords(customerRecordList);
		assertEquals(invalidRecords.size(), 1);
	}

}
