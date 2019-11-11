package com.rabobank.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.rabobank.model.RaboAppResponse;
import com.rabobank.service.RaboAppService;
import com.rabobank.util.RaboAppConstants;

/**
 * Created by Navin 
 * Rest Controller services for custom input
 */

@RestController
@RequestMapping("/rabobankservices")
public class RaboAppRestController {

	@Autowired
	private RaboAppService raboAppService;

	@RequestMapping(value = "/processStatement", method = RequestMethod.POST)
	public @ResponseBody RaboAppResponse processCustomerStatement(@RequestParam("file") MultipartFile multiPartFile)
			throws Exception {
		if (!multiPartFile.isEmpty()) {
			String label = "";
			if (multiPartFile.getContentType().equalsIgnoreCase(RaboAppConstants.FILE_TYPE_CSV))
				label = UUID.randomUUID().toString() + ".csv";
			else if (multiPartFile.getContentType().equalsIgnoreCase(RaboAppConstants.FILE_TYPE_XML))
				label = UUID.randomUUID().toString() + ".xml";
			else
				label = UUID.randomUUID().toString() + ".tmp";
			final String filepath = "/tmp/" + label;
			byte[] bytes = multiPartFile.getBytes();
			File fh = new File("/tmp/");
			if (!fh.exists()) {
				fh.mkdir();
			}
			FileOutputStream writer = new FileOutputStream(filepath);
			writer.write(bytes);
			writer.close();
			return raboAppService.processRecordsAndGenerateRestReport(filepath, multiPartFile.getContentType());
		} else {
			throw new Exception();
		}
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody RaboAppResponse handleException(HttpServletRequest request, Exception ex) {
		RaboAppResponse appResponse = new RaboAppResponse();
		appResponse.setStatus(RaboAppConstants.HTTP_CODE_ERROR);
		appResponse.setMessage(RaboAppConstants.SERVER_ERROR_MESSAGE);
		return appResponse;
	}

}
