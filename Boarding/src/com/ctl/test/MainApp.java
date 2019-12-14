package com.ctl.test;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ctl.activiti.form.EmployeeDetails;
import com.ctl.activiti.form.Employeedata;
import com.ctl.activiti.form.ProcessStatusForm;
import com.ctl.activiti.form.ResourcDetailsForm;
import com.ctl.activiti.helper.ExcelForOnBoarding;


public class MainApp {
	
static int pow(int a, int b)
	{
	  if (b == 0)
	    return 1;
	  int answer = a;
	  int increment = a;
	  int i, j;
	  for(i = 1; i < b; i++)
	  {
	     for(j = 1; j < a; j++)
	     {
	        answer += increment;
	     }
	     increment = answer;
	  }
	  return answer;
	}
  
   private static boolean isAnagram(String str1, String str2) {
	   
       // If string lengths are not same, the strings are not anagrams.
       if (str1.length() != str2.length()) {
           return false;
       }

       // Sort characters of both the strings.
       str1 = sortCharacters(str1);
       str2 = sortCharacters(str2);

       // After sorting if strings are equal then they are anagrams.
       if (str1.equals(str2)) {
           return true;
       }
       return false;
   }

   private static String sortCharacters(String str) {
       char[] charArray = str.toLowerCase().toCharArray();
       Arrays.sort(charArray);
       return String.valueOf(charArray);
   }

   public static void main(String[] args) {
       String str1 = "Robin";
       String str2 = "binro";
       System.out.println(isPalindrome2("bab"));
     
   }
   
   static int powerofnumber(int a)
   {
	  if(a==0)
	  {
		  
		 return 0; 
	  }
	  int temp=a;
	  int increment=a;
	  for(int i=1;i<a;i++)
		  
	  {
		  for(int j=1;j<a;j++)
		  {
			  temp+=increment;
			  
		  }
		 increment=temp;
	  }
	  return temp;

	   
   }
   public static boolean isPalindrome(String str){

		String reverse = new StringBuffer(str).reverse().toString();
		
		if (reverse.equals(str))
			return true;
		
		return false;
	}
	
	public static boolean isPalindrome2(String str){

		int start = 0;
		int end = str.length() - 1;
		int half = end/2;
		
		for(int i = 0; i < half+1; i++, start++, end-- ){
			if(str.charAt(start) != str.charAt(end))
				return false;
		}
		
		return true;
	}
	 public static boolean isPalindrome(int number) {
	        int palindrome = number; // copied number into variable
	        int reverse = 0;

	        while (palindrome != 0) {
	            int remainder = palindrome % 10;
	            reverse = reverse * 10 + remainder;
	            palindrome = palindrome / 10;
	        }

	        // if original and reverse of number is equal means
	        // number is palindrome in Java
	        if (number == reverse) {
	            return true;
	        }
	        return false;
	    }
	 
	 public static void verifyFibonaci()
	 {
		 
		 int febCount = 15;
         int[] feb = new int[febCount];
         feb[0] = 0;
         feb[1] = 1;
         for(int i=2; i < febCount; i++){
             feb[i] = feb[i-1] + feb[i-2];
         }
 
         for(int i=0; i< febCount; i++){
                 System.out.print(feb[i] + " ");
         }

	 }

	}

