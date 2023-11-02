package com.techgentsia.utils;

import com.techgentsia.exception.HmacAlgorithmException;
import com.techgentsia.exception.InvalidRequestSignatureException;
import com.techgentsia.net.ApiResource;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

public class HmacSHA256 {
  static final long FIVE_MINUTE_ZONE = 60 * 5;

  static public String generate(final String secretKey, final String message) {
    return generate(secretKey, message.getBytes(ApiResource.CHARSET));
  }

  static public String generate(final String secretKey, final byte[] messageBytes) {
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
      mac.init(secretKeySpec);
      byte[] hmacSha256 = mac.doFinal(messageBytes);
      return Base64.getEncoder().encodeToString(hmacSha256);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new HmacAlgorithmException(e);
    }
  }

  static public String generateCurrentSignature(final String secretKey, final String message) {
    return generateCurrentSignature(secretKey, message.getBytes(ApiResource.CHARSET));
  }

  static public String generateCurrentSignature(final String secretKey, final byte[] messageBytes) {
    long utz = Instant.now().getEpochSecond();
    final long currentTime = utz / FIVE_MINUTE_ZONE;
    final String newKey = currentTime + ":" + secretKey;
    return generate(newKey, messageBytes);
  }

  static public void validateSignature(final String sign, final String secretKey, final String message) {
    long utz = Instant.now().getEpochSecond();
    final long currentTime = utz / FIVE_MINUTE_ZONE;

    {
      final String newKey = currentTime + ":" + secretKey;
      final String signature = generate(newKey, message);
      if (sign.equals(signature)) {
        return;
      }
    }

    {
      final long currentTimePlusOne = currentTime + 1;
      final String newKey = currentTimePlusOne + ":" + secretKey;
      final String signature = generate(newKey, message);
      if (sign.equals(signature)) {
        return;
      }
    }

    {
      final long currentTimeMinusOne = currentTime - 1;
      final String newKey = currentTimeMinusOne + ":" + secretKey;
      final String signature = generate(newKey, message);
      if (sign.equals(signature)) {
        return;
      }
    }

    throw new InvalidRequestSignatureException("Unable to validate Signature.");
  }
}
