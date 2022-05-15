package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SalaryModelAPI")
public class SalaryModelAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	SalaryModel itemObj = new SalaryModel();
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = itemObj.insertSalary(request.getParameter("empid"), request.getParameter("salid"),
				request.getParameter("Amount"), request.getParameter("allowance"), request.getParameter("fm"));
		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = itemObj.updateSalary(paras.get("empid").toString(), paras.get("salid").toString(),
				paras.get("Amount").toString(), paras.get("allowance").toString(), paras.get("fm").toString());
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = itemObj.deleteSalary(paras.get("empId").toString());
		response.getWriter().write(output);
	}

}
