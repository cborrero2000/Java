/*
 * Copyright (C)2004 Sprint Canada Inc.
 * 
 * All rights reserved.
 * 
 * The source code in this file is confidential and is the sole property of
 * Sprint Canada Inc. Its use is restricted to those readers who are authorized
 * by the corporation. Any reverse engineering or reproduction or divulgence of
 * the content of this report without the written consent from Sprint Canada
 * Inc. is strictly prohibited.
 */
package com.rogers.oss.test.cspgw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.sprint.common.util.XmlUtils;

/**
 * RT OSS Gateway Test
 * 
 * Accepts two optional parameters:
 * <ul>
 * <li>request file, default XML file is "LNPInstallPrimaryAndVirtual.xml"
 * <li>URL, default URL is "http://localhost/cspgw/submit.do"
 * </ul>
 * 
 * @author Michael Anisimov
 * @author Aleksandar Zdravkovic
 */
public class TestCspgwServer {

	private static final String defaultRequest_ = "01_LNP_Order_Submitted.xml";

	private static final String emptySoap_ = "CspSoapEmptyBody.xml";
	private static int maxConns_ = 1;
	private static int connTimeOut_ = 90000;
	private static int socketTimeOut_ = 90000;
	private static String defaultUrl_ = "http://10.13.55.34:8011/cspgw/ac/submit.do";
	private static final String requestBodyStartTag_ = "<RequestBody xsi:type=\"xsd:string\">";
	private static final String requestbodySuffixTag_ = "</RequestBody>";

	public static void main(String[] args) {
		String requestFile = args.length >= 1 ? args[0] : defaultRequest_;
		System.out.println("Processing file " + requestFile);

		final String url = (args.length >= 2 ? args[1] : defaultUrl_);
		System.out.println("Url = " + url);

		int threadCount = (args.length == 3 ? Integer.parseInt(args[2]) : 5);
		System.out.println("Threads = " + threadCount);

		try {
			StringBuffer emptySoapBuf = readFile(emptySoap_);
			StringBuffer requestXmlBuf = readFile(requestFile);
			String bodyPrefix = emptySoapBuf.substring(0, emptySoapBuf.indexOf(requestBodyStartTag_));
			String bodySuffix = emptySoapBuf.substring(emptySoapBuf.indexOf(requestbodySuffixTag_));
			final String requestXml = bodyPrefix + requestBodyStartTag_
					+ XmlUtils.toXMLString(requestXmlBuf.toString()) + bodySuffix;

			// Parameters
			HttpConnectionManagerParams parms = new HttpConnectionManagerParams();
			parms.setDefaultMaxConnectionsPerHost(maxConns_);
			parms.setConnectionTimeout(connTimeOut_);
			parms.setSoTimeout(socketTimeOut_);

			// Create connection manager
			final MultiThreadedHttpConnectionManager connMgr = new MultiThreadedHttpConnectionManager();
			for (int j = 0; j < 1; j++) {
				for (int i = 0; i < 1; i++) {
					Thread t = new Thread() {

						public void run() {
							sendRequest(url, requestXml, connMgr);
						}
					};
					t.start();
				//	Thread.sleep(100);
				}
				Thread.sleep(5000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendRequest(String url, String requestXml, MultiThreadedHttpConnectionManager connMgr) {
		try {
			// Create HTTP client
			final HttpClient httpClient = new HttpClient(connMgr);

			// Create POST method
			final PostMethod httpMethod = new PostMethod(url);
			httpMethod.setRequestEntity(new StringRequestEntity(requestXml));

			// Send request and receive response
			httpClient.executeMethod(httpMethod);
			String response = httpMethod.getResponseBodyAsString();
			System.out.println("Response from CSP gateway: " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static StringBuffer readFile(String resourceName) throws IOException {
		InputStream inputStream = TestCspgwServer.class.getResourceAsStream(resourceName);
		StringBuffer buffer = new StringBuffer();
		char cbuf[] = new char[32];
		int cnt = 0;
		BufferedReader bin = new BufferedReader(new InputStreamReader(inputStream));
		while ((cnt = bin.read(cbuf)) != -1) {
			buffer.append(cbuf, 0, cnt);
		}
		inputStream.close();
		return buffer;
	}
}

