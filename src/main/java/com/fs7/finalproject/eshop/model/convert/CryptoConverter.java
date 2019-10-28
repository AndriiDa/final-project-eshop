package com.fs7.finalproject.eshop.model.convert;

import com.fs7.finalproject.eshop.model.Gender;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.security.Key;

//https://thoughts-on-java.org/how-to-use-jpa-type-converter-to/
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {
  private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
  private static final byte[] KEY = "MySuperSecretKey".getBytes();

  @Override
  public String convertToDatabaseColumn(String ccNumber) {
    // do some encryption
    Key key = new SecretKeySpec(KEY, "AES");
    try {
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.ENCRYPT_MODE, key);
      return Base64.encode(c.doFinal(ccNumber.getBytes()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    // do some decryption
    Key key = new SecretKeySpec(KEY, "AES");
    try {
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.DECRYPT_MODE, key);
      return new String(c.doFinal(Base64.decode(dbData)));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
