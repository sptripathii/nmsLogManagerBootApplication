package com.sudhanshu.springboot.nmsLogsManager.controller;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class EncryptUtils {

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec keySpec;
	private SecretKeyFactory secretKeyFactory;
	private Cipher cipher;
	byte[] arrayBytes;
	private String baseEncryptionKey;
	private String baseEncryptionScheme;
	SecretKey key;
	private static EncryptUtils utils;

	private EncryptUtils() throws Exception {
		baseEncryptionKey = "NMSSysLogManagerSudhanshu";
		baseEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
		arrayBytes = baseEncryptionKey.getBytes(UNICODE_FORMAT);
		keySpec = new DESedeKeySpec(arrayBytes);
		secretKeyFactory = SecretKeyFactory.getInstance(baseEncryptionScheme);
		cipher = Cipher.getInstance(baseEncryptionScheme);
		key = secretKeyFactory.generateSecret(keySpec);
	}

	public static EncryptUtils getInstance() throws Exception {
		if (utils == null) {
			utils = new EncryptUtils();
		}
		return utils;
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}

}
