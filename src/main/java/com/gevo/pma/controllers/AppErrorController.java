package com.gevo.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String retString = "error.html";

		if (errorCode != null) {
			int errorCodeInt = Integer.parseInt(errorCode.toString());

			if (errorCodeInt == HttpStatus.FORBIDDEN.value()) {
				retString = "error-403";
			}

			if (errorCodeInt == HttpStatus.NOT_FOUND.value()) {
				retString = "error-404";
			}
			if (errorCodeInt == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				retString = "error-500";
			}
		}
		return "errorpages/" + retString;
	}

	public String getErrorPath() {
		return "/error";
	}

}
