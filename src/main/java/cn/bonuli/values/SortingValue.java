/**
 * STARPOST CONFIDENTIAL
 * _____________________
 * 
 * [2014] - [2018] StarPost Supply Chain Management Co. (Shenzhen) Ltd. 
 * All Rights Reserved.
 * 
 * NOTICE: All information contained herein is, and remains the property of
 * StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from StarPost Supply Chain Management Co. (Shenzhen)
 * Ltd.
 *
 */
package cn.bonuli.values;

/**
 * 
 * @author lijun
 *
 */
public class SortingValue {

	final int port;
	final String orderId;
	final int receivedCarton;
	final int totalCarton;

	public SortingValue(int port, String orderId, int receivedCarton, int totalCarton) {
		super();
		this.port = port;
		this.orderId = orderId;
		this.receivedCarton = receivedCarton;
		this.totalCarton = totalCarton;
	}

	public int getPort() {
		return port;
	}

	public String getOrderId() {
		return orderId;
	}

	public int getReceivedCarton() {
		return receivedCarton;
	}

	public int getTotalCarton() {
		return totalCarton;
	}

}
