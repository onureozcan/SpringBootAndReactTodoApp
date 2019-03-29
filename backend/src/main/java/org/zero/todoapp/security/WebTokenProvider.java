package org.zero.todoapp.security;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.List;

import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.zero.todoapp.exceptions.TokenValidationException;

// https://stackoverflow.com/questions/23808460/jwt-json-web-token-library-for-java
public class WebTokenProvider {

    private static final String AUDIENCE = "NotReallyImportant";

    private static final String ISSUER = "Zero";

    private static final String SIGNING_KEY = "ImpossibleToGuess";

    private static final Long DEFAULT_EXPIRATION_DURATION = 10L;

    public static String createJsonWebToken(String userId) {
        //Current time and signing algorithm
        Calendar cal = Calendar.getInstance();
        HmacSHA256Signer signer;
        try {
            signer = new HmacSHA256Signer(ISSUER, null, SIGNING_KEY.getBytes());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        //Configure JSON token
        JsonToken token = new net.oauth.jsontoken.JsonToken(signer);
        token.setAudience(AUDIENCE);
        token.setIssuedAt(new org.joda.time.Instant(cal.getTimeInMillis()));
        token.setExpiration(new org.joda.time.Instant(
                cal.getTimeInMillis() + 1000L * 60L * 60L * 24L * DEFAULT_EXPIRATION_DURATION)
        );

        //Configure request object, which provides information of the item
        JsonObject request = new JsonObject();
        request.addProperty("userId", userId);

        JsonObject payload = token.getPayloadAsJsonObject();
        payload.add("info", request);

        try {
            return token.serializeAndSign();
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    public static String verifyToken(String token) throws TokenValidationException {
        try {
            final Verifier hmacVerifier = new HmacSHA256Verifier(SIGNING_KEY.getBytes());

            VerifierProvider hmacLocator = new VerifierProvider() {

                @Override
                public List<Verifier> findVerifier(String id, String key) {
                    return Lists.newArrayList(hmacVerifier);
                }
            };
            VerifierProviders locators = new VerifierProviders();
            locators.setVerifierProvider(SignatureAlgorithm.HS256, hmacLocator);
            net.oauth.jsontoken.Checker checker = new net.oauth.jsontoken.Checker() {

                @Override
                public void check(JsonObject payload) throws SignatureException {
                    // don't throw - allow anything
                }

            };
            //Ignore Audience does not mean that the Signature is ignored
            JsonTokenParser parser = new JsonTokenParser(locators,
                    checker);
            JsonToken jt;
            try {
                jt = parser.verifyAndDeserialize(token);
            } catch (SignatureException e) {
                throw new RuntimeException(e);
            }
            JsonObject payload = jt.getPayloadAsJsonObject();
            String issuer = payload.getAsJsonPrimitive("iss").getAsString();
            String userIdString = payload.getAsJsonObject("info").getAsJsonPrimitive("userId").getAsString();
            if (issuer.equals(ISSUER) && !StringUtils.isBlank(userIdString)) {
                return userIdString;
            }
            return null;
        } catch (InvalidKeyException e1) {
            throw new TokenValidationException(e1);
        }
    }


}
