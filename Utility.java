package com.tag.mancalaSelf;

import java.util.Scanner;

public class Utility {

	static Scanner sc = new Scanner(System.in);

	static int getInt(String message) {
		System.out.println(message);
		return sc.nextInt();
	}

	static String getString(String message) {
		System.out.println(message);
		return sc.next();
	}

	static void print(String s) {
		System.out.println(s);
	}
}
