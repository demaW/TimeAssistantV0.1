package com.java.task11.webapp.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.service.ProjectInvoiceService;
import com.java.task11.model.ProjectInvoice;

/**
 * Servlet implementation class InvoiceStats
 */
@WebServlet("/manager/invoicestats")
public class InvoiceStats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InvoiceStats() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Integer> realTime = new ArrayList<>();
		ArrayList<Integer> estTime = new ArrayList<>();
		int reTime = 0;
		int esTime = 0;
		int project_id = Integer.parseInt(request.getParameter("project_id"));
		List<ProjectInvoice> invoices = null;
		invoices = new ProjectInvoiceService().getInvoiceFinished(project_id);
		LinkedHashSet<String> users = new LinkedHashSet<>();
		for (ProjectInvoice inv : invoices) {
			users.add(inv.getFirstName() + " " + inv.getLastName());
		}
		ArrayList<String> uniqueUsers = new ArrayList<String>(users);

		for (String string : uniqueUsers) {
		//	int index = 0;
			reTime = 0;
			esTime = 0;
			for (ProjectInvoice inv : invoices) {
				if (string.equalsIgnoreCase(inv.getFirstName() + " "
						+ inv.getLastName())) {
					reTime += inv.getWorkedTime();
					esTime += inv.getPlanedTime();
				}
			}
			realTime.add( reTime);
			estTime.add( esTime);
		//	index++;
		}
		request.setAttribute("uniqueusers", uniqueUsers);
		request.setAttribute("esttime", estTime);
		request.setAttribute("realtime", realTime);

		request.getRequestDispatcher("/pages/manager/invoicestats.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
