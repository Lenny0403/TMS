package com.farm.web.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.farm.wda.exception.ErrorTypeException;

public interface WcpAppInter extends Remote {

	/**
	 * @param docid
	 * @param text
	 * @throws ErrorTypeException
	 * @throws RemoteException
	 */
	public void runLuceneIndex(String fileid, String docid, String text) throws ErrorTypeException, RemoteException;

}
