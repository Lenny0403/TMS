package com.farm.web.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.farm.doc.server.FarmDocManagerInter;
import com.farm.doc.server.FarmFileIndexManagerInter;
import com.farm.util.spring.BeanFactory;
import com.farm.wda.exception.ErrorTypeException;
import com.farm.web.rmi.WcpAppInter;

public class WcpAppImpl extends UnicastRemoteObject implements WcpAppInter {
	public WcpAppImpl() throws RemoteException {
		super();
	}

	private FarmFileIndexManagerInter farmFileIndexManagerImpl = (FarmFileIndexManagerInter) BeanFactory
			.getBean("farmFileIndexManagerImpl");
	private FarmDocManagerInter farmDocManagerImpl = (FarmDocManagerInter) BeanFactory.getBean("farmDocManagerImpl");

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	public void runLuceneIndex(String fileid, String docid, String text) throws ErrorTypeException, RemoteException {
		farmFileIndexManagerImpl.addFileLuceneIndex(fileid, farmDocManagerImpl.getDoc(docid), text);
	}
}
