package com.bankingapplication.encryption;

import java.util.HashMap;

import java.util.Random;

public abstract class UserEncryption {

	public HashMap encrypt(String password) {
		HashMap<Integer, String> encrypt = new HashMap<>();
		Random random = new Random();
		int key = random.nextInt(100);
		String result = "";
		for (int i = 0; i < password.length(); i++) {
			int n = password.charAt(i);
			n = n + key;
			result += (char) n;
		}
		encrypt.put(key, result);
		return encrypt;
	}

	public String decrypt(String passWord, int key) {
		String decrypt = "";
		for (int i = 0; i < passWord.length(); i++) {
			int n = passWord.charAt(i);
			n = n - key;
			decrypt += (char) n;
		}
		return decrypt;
	}
}
