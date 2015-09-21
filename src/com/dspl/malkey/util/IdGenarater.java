package com.dspl.malkey.util;
//import java.util.Random;

public class IdGenarater {

 //   private static Random rn = new Random();

    public static String getFormatedRefNo(int seq, int year,int month,String prefix)
    {   
/*    	//System.out.println("Year : "+Integer.toString(2010).substring(2));//Integer.toString(2010).substring(2);
    	year=Integer.parseInt(Integer.toString(year).substring(2));
    	String formattedSeq = String.format("%04d", seq);
    	String formattedYear = String.format("%02d", year);
    	String formattedMonth = String.format("%02d", month);    	
    	System.out.println("Number with leading zeros: " + prefix+"/"+formattedYear+formattedMonth+"/"+formattedSeq);
    	return (prefix+"/"+formattedYear+formattedMonth+"/"+formattedSeq);*/
    	
        return (prefix+"/"+String.format("%02d", Integer.parseInt(Integer.toString(year).substring(2)))+String.format("%02d", month)+"/"+String.format("%05d", seq));
        
        
    }
    
    public static String getFormatedRefNo(int seq,String prefix)
    {   
/*    	String formattedSeq = String.format("%09d", seq);  	
    	System.out.println("Number with leading zeros: " + prefix+"/"+formattedSeq);
        return (prefix+"/"+formattedSeq);*/
    	
    	return (prefix+"/"+String.format("%09d", seq));
    }
    
    public static String getFormatedRefNo(int seq,String prefix,int length)
    {   
    	return (prefix+String.format("%0"+length+"d", seq));
    }
 

    
}
