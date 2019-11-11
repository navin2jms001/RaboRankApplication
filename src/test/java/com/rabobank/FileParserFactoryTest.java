package com.rabobank;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.rabobank.parser.CsvFileParserImpl;
import com.rabobank.parser.FileParser;
import com.rabobank.parser.FileParserFactory;
import com.rabobank.parser.ParserNotSupportedException;
import com.rabobank.parser.XmlFileParserImpl;


public class FileParserFactoryTest {
	File file = null;

	@Before
	public void setUp() throws Exception {
		file = new File("test.xml");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testXmlParserFactory() throws Exception {
		FileParser parser = FileParserFactory.getParserFactory(file);
		assertSame(parser.getClass(), new XmlFileParserImpl(file).getClass());
	}
	
	@Test
	public void testCsvParserFactory() throws Exception {
		file = new File("test.csv");
		FileParser parser = FileParserFactory.getParserFactory(file);
		assertSame(parser.getClass(), new CsvFileParserImpl(file).getClass());
	}
	
	@Test(expected = ParserNotSupportedException.class)
	public void testInvalidParserFactory() throws ParserNotSupportedException {
		file = new File("test.txt");
		FileParserFactory.getParserFactory(file);
	}

}
