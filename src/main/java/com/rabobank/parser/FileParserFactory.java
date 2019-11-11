package com.rabobank.parser;

import java.io.File;

public class FileParserFactory {

	public static FileParser getParserFactory(File file) throws ParserNotSupportedException {
		FileParser parser = null;
		String fileName = file.getName();
		if (fileName.endsWith(".xml")) {
			parser = new XmlFileParserImpl(file);
		} else if (fileName.endsWith(".csv")) {
			parser = new CsvFileParserImpl(file);
		} else {
			throw new ParserNotSupportedException("Parser not supported. Please check the file type. ");
		}
		return parser;
	}
}
