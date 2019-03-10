package com.sudhanshu.springboot.nmsLogsManager.controller;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {

	private static String issuer = "NmsSyslog";
	private static int ttlInMilli = 840000;
	public static String DEVICES_CLAIM = "deviceList";
	private static String secretKey = "secret";

	public static String generateHMACToken(String id, String user, String[] devices) throws JWTCreationException {
		String token = null;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		long expMillis = nowMillis + ttlInMilli;
		Date expiresAt = new Date(expMillis);
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		token = JWT.create().withIssuedAt(now).withIssuer(issuer).withArrayClaim(DEVICES_CLAIM, devices).withJWTId(id)
				.withSubject(user).withExpiresAt(expiresAt).sign(algorithm);
		return token;
	}

	public static DecodedJWT verifyHMACToken(String token) throws JWTVerificationException {
		DecodedJWT jwt = null;
		if (token == null) {
		}
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
		jwt = verifier.verify(token);

		return jwt;
	}
}
