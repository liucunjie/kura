/**
 * Copyright (c) 2011, 2014 Eurotech and/or its affiliates
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Eurotech
 */
package org.eclipse.kura.net;

/**
 * This class represents an Internet Protocol version 6 (IPv6) address.
 */
public class IP6Address extends IPAddress
{
	IP6Address(byte[] addr, java.net.InetAddress jnAddress) {
		super(addr, jnAddress);
	}

	/**
	 * Utility routine to check if the InetAddress is an IPv4 compatible IPv6 address.
	 * @return a boolean indicating if the InetAddress is an IPv4 compatible IPv6 address; or false if address is IPv4 address.
	 */
	public boolean isIPv4CompatibleAddress() {
		return ((java.net.Inet6Address) javaNetAddress).isIPv4CompatibleAddress();
	}
}
