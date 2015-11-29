package com.dalthow.machinilorum.util;

import java.util.Random;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class MathHelper
{
	public static int randomNumber(int min, int max)
	{
		Random random = new Random();
		
		return random.nextInt(max - min + 1) + min;
	}
}
