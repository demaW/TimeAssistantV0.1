package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.implement.ProjectInvoiceDao;
import com.java.task11.model.ProjectInvoice;


public class ProjectInvoiceService {

	public List<ProjectInvoice> getInvoiceAll(int projectId){
		return new ProjectInvoiceDao().getByPojectId(projectId,ProjectInvoiceDao.SELECTINVOICE);
	}
	public List<ProjectInvoice> getInvoiceFinished(int projectId){
		return new ProjectInvoiceDao().getByPojectId(projectId,ProjectInvoiceDao.SELECTINVOICEFINISHED);
	}
	public List<ProjectInvoice> getInvoiceInProgress(int projectId){
		return new ProjectInvoiceDao().getByPojectId(projectId,ProjectInvoiceDao.SELECTINVOICEINPROGRESS);
	}
}
