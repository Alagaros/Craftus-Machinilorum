package com.dalthow.machinilorum.util;

import java.util.Random;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class MathHelper.java
 * 
 **/

public class MathHelper
{
	// TODO: Properly document these.
	
	public static float approachLinear(float var1, float var2, float var3)
	{
	    return var2 - var1 < var3 ? var2 : var1 > var2 ? var1 - var3 : var1 - var2 < var3 ? var2 : var1 + var3;
	}
	
	public static double approachLinear(double var1, double var2, double var3)
	{
	    return var2 - var1 < var3 ? var2 : var1 > var2 ? var1 - var3 : var1 - var2 < var3 ? var2 : var1 + var3;
	}
	
	public static float interpolate(float var1, float var2, float var3)
	{
	    return var1 + (var2 - var1) * var3;
	}
	
	public static double interpolate(double var1, double var2, double var3)
	{
	    return var1 + (var2 - var1) * var3;
	}
	
	public static double approachExp(double var1, double var2, double var3)
	{
		return var1 + (var2 - var1) * var3;
	}
	
	public static double approachExp(double var1, double var2, double var3, double var4)
	{
		double var5 = (var2 - var1) * var3;
	  
		if (Math.abs(var5) > var4)
		{
			var5 = Math.signum(var5) * var4;
		}
	  
		return var1 + var2;
	}
	
	public static double retreatExp(double var1, double var2, double var3, double var4, double var5)
	{
		double var6 = (Math.abs(var3 - var1) + var5) * var4;
		  
		if (var6 > Math.abs(var2 - var1)) 
		{
		    return var2;
		}
		  
		 return var1 + Math.signum(var2 - var1) * var6;
	}
	
	public static double clamp(double var1, double var2, double var3)
	{
		if (var1 > var3) 
		{
		    var1 = var3;
		}
		  
		if (var1 < var2) 
		{
		    var1 = var2;
		}
		    
		return var1;
	}
	
	public static boolean between(double var1, double var2, double var3)
	{
	    return (var1 <= var2) && (var2 <= var3);
	}
	
	public static int approachExpI(int var1, int var2, double var3)
	{
		int var4 = (int)Math.round(approachExp(var1, var2, var3));
		
		return var4 == var1 ? var2 : var4;
	}

	public static int retreatExpI(int var1, int var2, int var3, double var4, int var5)
	{
		int var6 = (int)Math.round(retreatExp(var1, var2, var3, var4, var5));
		return var6 == var1 ? var2 : var6;
	}

	public static int round(double var1)
	{
		return (int)(var1 + 0.5D);
	}

	public static int ceil(double var1)
	{
		return (int)(var1 + 0.9999D);
	}

	public static float minFloat(float var1, float var2)
	{
		return var1 < var2 ? var1 : var2;
	}

	public static float maxFloat(float var1, float var2)
	{
		return var1 > var2 ? var1 : var2;
	}

	public static int minInteger(int var1, int var2)
	{
		return var1 < var2 ? var1 : var2;
	}

	public static int maxInteger(int var1, int var2)
	{
		return var1 > var2 ? var1 : var2;
	}
	  
	public static int randomNumber(int min, int max)
	{
		Random random = new Random();
		
		return random.nextInt(max - min + 1) + min;
	}
}
