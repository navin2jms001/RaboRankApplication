package com.rabobank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rabobank.model.RaboAppResponse;
import com.rabobank.model.ResultRecord;
import com.rabobank.service.RaboAppService;
import com.rabobank.util.RaboAppConstants;

/**
 * Created by Navin Main Controller for generating report
 */

@Controller
@RequestMapping("/rabobank")
public class RaboAppController {

	@Autowired
	private RaboAppService raboAppService;

	@RequestMapping(value = "/processStatement", method = RequestMethod.GET)
	public String processCustomerStatement(Model model) throws Exception {
		model.addAttribute("invalidRecords",
				raboAppService.processRecordsAndGenerateReport(RaboAppConstants.CONTENT_TYPE_BOTH).getData());
		return "report";
	}

	@RequestMapping(value = "/processStatement/{processFileType}", method = RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public String processCustomerStatement(
			@PathVariable(value = "processFileType", required = false) String processFileType, Model model)
			throws Exception {

		List<ResultRecord> reportRecords = (List<ResultRecord>) raboAppService
				.processRecordsAndGenerateReport(processFileType).getData();
		if (reportRecords == null || reportRecords.isEmpty())
			return null;

		model.addAttribute("invalidRecords", reportRecords);
		return "report";

	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody RaboAppResponse handleException(HttpServletRequest request, Exception ex) {
		RaboAppResponse appResponse = new RaboAppResponse();
		appResponse.setStatus(RaboAppConstants.HTTP_CODE_ERROR);
		appResponse.setMessage(RaboAppConstants.SERVER_ERROR_MESSAGE);
		return appResponse;
	}

}
