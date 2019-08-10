package com.Action;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		String latlng= "((36.87995646660264, 126.56819641531055), (37.36925856049193, 127.62677906959311))";
		
		latlng= latlng.replace("(", "");
		latlng= latlng.replace(")", "");
		latlng= latlng.replace(" ", "");	
		
		String arr[] = latlng.split(",");
		System.out.println(arr.toString());
		System.out.println(latlng.toString());
		System.out.println("arr[0] = "+arr[0]); //lngmin
		System.out.println("arr[1] = "+arr[1]); //latmin
		System.out.println("arr[2] = "+arr[2]); //lngmax
		System.out.println("arr[3] = "+arr[3]); //lngmax
		
		System.out.println("123");
		Scanner scan = new Scanner(System.in);
		scan.next();

	}

}
