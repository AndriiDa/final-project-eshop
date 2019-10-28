package com.fs7.finalproject.eshop.model.convert;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//https://thoughts-on-java.org/how-to-use-jpa-type-converter-to/
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {
  @Override
  public String convertToDatabaseColumn(String ccNumber) {
    return null;
  }

  @Override
  public String convertToEntityAttribute(String ccNumber) {
    return null;
  }
//  private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
//  private static final byte[] KEY = "MySuperSecretKey".getBytes();
//
//  @Override
//  public String convertToDatabaseColumn(String ccNumber) {
//    // do some encryption
//    Key key = new SecretKeySpec(KEY, "AES");
//    try {
//      Cipher c = Cipher.getInstance(ALGORITHM);
//      c.init(Cipher.ENCRYPT_MODE, key);
//      return Base64.encode(c.doFinal(ccNumber.getBytes()));
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public String convertToEntityAttribute(String dbData) {
//    // do some decryption
//    Key key = new SecretKeySpec(KEY, "AES");
//    try {
//      Cipher c = Cipher.getInstance(ALGORITHM);
//      c.init(Cipher.DECRYPT_MODE, key);
//      return new String(c.doFinal(Base64.decode(dbData)));
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
}
