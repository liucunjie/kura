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
package org.eclipse.kura.protocol.modbus;

/* 
 * Copyright � 2009 Eurotech Inc. All rights reserved.
 */

/**
 * Used to calculate the CRC-16 (cyclical redundancy check) for an array of bytes.
 */
public class Crc16 {
	private Crc16(){};

	/**
	 * calculate the crc for the passed buffer
	 * 
	 * @param buff	byte array to calculate CRC of
	 * @param buffLen  number of bytes in array to calculate against
	 * @param crcSeed  starting seed for CRC calculation
	 * @return	CRC16 as calculated for buff
	 */
	public static int getCrc16( byte[] buff, int buffLen, int crcSeed)
	{
		int hi,lo,tmp;
		  
		lo = crcSeed & 0x0ff;
		hi = (crcSeed >> 8) & 0x0ff;
		  
		for(int i=0;i<buffLen;i++)
		{
			tmp = (lo ^ buff[i]) & 0x0ff;
		    lo = hi ^ abCrcTbl2[tmp];
		    hi = abCrcTbl1[tmp];
		}
		return (lo + (hi << 8)); 
	}

	private final static int[] abCrcTbl1 =	{
		0x000, 0x0C0, 0x0C1, 0x001, 0x0C3, 0x003, 0x002, 0x0C2,
		0x0C6, 0x006, 0x007, 0x0C7, 0x005, 0x0C5, 0x0C4, 0x004,
		0x0CC, 0x00C, 0x00D, 0x0CD, 0x00F, 0x0CF, 0x0CE, 0x00E,
		0x00A, 0x0CA, 0x0CB, 0x00B, 0x0C9, 0x009, 0x008, 0x0C8,
		0x0D8, 0x018, 0x019, 0x0D9, 0x01B, 0x0DB, 0x0DA, 0x01A,
		0x01E, 0x0DE, 0x0DF, 0x01F, 0x0DD, 0x01D, 0x01C, 0x0DC,
		0x014, 0x0D4, 0x0D5, 0x015, 0x0D7, 0x017, 0x016, 0x0D6,
		0x0D2, 0x012, 0x013, 0x0D3, 0x011, 0x0D1, 0x0D0, 0x010,
		0x0F0, 0x030, 0x031, 0x0F1, 0x033, 0x0F3, 0x0F2, 0x032,
		0x036, 0x0F6, 0x0F7, 0x037, 0x0F5, 0x035, 0x034, 0x0F4,
		0x03C, 0x0FC, 0x0FD, 0x03D, 0x0FF, 0x03F, 0x03E, 0x0FE,
		0x0FA, 0x03A, 0x03B, 0x0FB, 0x039, 0x0F9, 0x0F8, 0x038,
		0x028, 0x0E8, 0x0E9, 0x029, 0x0EB, 0x02B, 0x02A, 0x0EA,
		0x0EE, 0x02E, 0x02F, 0x0EF, 0x02D, 0x0ED, 0x0EC, 0x02C,
		0x0E4, 0x024, 0x025, 0x0E5, 0x027, 0x0E7, 0x0E6, 0x026,
		0x022, 0x0E2, 0x0E3, 0x023, 0x0E1, 0x021, 0x020, 0x0E0,
		0x0A0, 0x060, 0x061, 0x0A1, 0x063, 0x0A3, 0x0A2, 0x062,
		0x066, 0x0A6, 0x0A7, 0x067, 0x0A5, 0x065, 0x064, 0x0A4,
		0x06C, 0x0AC, 0x0AD, 0x06D, 0x0AF, 0x06F, 0x06E, 0x0AE,
		0x0AA, 0x06A, 0x06B, 0x0AB, 0x069, 0x0A9, 0x0A8, 0x068,
		0x078, 0x0B8, 0x0B9, 0x079, 0x0BB, 0x07B, 0x07A, 0x0BA,
		0x0BE, 0x07E, 0x07F, 0x0BF, 0x07D, 0x0BD, 0x0BC, 0x07C,
		0x0B4, 0x074, 0x075, 0x0B5, 0x077, 0x0B7, 0x0B6, 0x076,
		0x072, 0x0B2, 0x0B3, 0x073, 0x0B1, 0x071, 0x070, 0x0B0,
		0x050, 0x090, 0x091, 0x051, 0x093, 0x053, 0x052, 0x092,
		0x096, 0x056, 0x057, 0x097, 0x055, 0x095, 0x094, 0x054,
		0x09C, 0x05C, 0x05D, 0x09D, 0x05F, 0x09F, 0x09E, 0x05E,
		0x05A, 0x09A, 0x09B, 0x05B, 0x099, 0x059, 0x058, 0x098,
		0x088, 0x048, 0x049, 0x089, 0x04B, 0x08B, 0x08A, 0x04A,
		0x04E, 0x08E, 0x08F, 0x04F, 0x08D, 0x04D, 0x04C, 0x08C,
		0x044, 0x084, 0x085, 0x045, 0x087, 0x047, 0x046, 0x086,
		0x082, 0x042, 0x043, 0x083, 0x041, 0x081, 0x080, 0x040
	};

	private final static int[] abCrcTbl2 =	{
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x00, 0x0C1, 0x081, 0x40, 0x01, 0x0C0, 0x080, 0x41,
		0x01, 0x0C0, 0x080, 0x41, 0x00, 0x0C1, 0x081, 0x40
	};
}
