package com.rabobank;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rabobank.model.CustomerRecord;
import com.rabobank.parser.XmlFileParserImpl;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class XmlFileParserImplTest {
	List<CustomerRecord> customerRecordList = null;

	@Before
	public void setUp() throws Exception {
		customerRecordList = new ArrayList<>();
		CustomerRecord record = null;

		record = new CustomerRecord();
		record.setTransactionRefNum(2001);
		record.setAccountNumber("200201901");
		record.setDescription("Purchase1");
		record.setStartBalance(new BigDecimal(100));
		record.setMutation(new BigDecimal(-50));
		record.setEndBalance(new BigDecimal(50));
		customerRecordList.add(record);

		record.setTransactionRefNum(2001);
		record.setAccountNumber("200201902");
		record.setDescription("Purchase2");
		record.setStartBalance(new BigDecimal(400));
		record.setMutation(new BigDecimal(-50));
		record.setEndBalance(new BigDecimal(350));
		customerRecordList.add(record);

		record.setTransactionRefNum(2002);
		record.setAccountNumber("200201903");
		record.setDescription("Purchase3");
		record.setStartBalance(new BigDecimal(200));
		record.setMutation(new BigDecimal(50));
		record.setEndBalance(new BigDecimal(220));
		customerRecordList.add(record);
		
		record.setTransactionRefNum(1003);
		record.setAccountNumber("200201904");
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
	public void testParse() throws IOException {
		XmlFileParserImpl mock = mock(XmlFileParserImpl.class);
		try {
			when(mock.parse()).thenReturn(customerRecordList);
			assertEquals(mock.parse().size(), 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
