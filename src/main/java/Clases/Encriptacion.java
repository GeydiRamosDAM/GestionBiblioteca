/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Clases;

import java.nio.charset.Charset;

/**
 *
 * @author Geydi
 */
public interface Encriptacion {
    
  static String javaDecode(String encodedStr)
  {
    return new String(java.util.Base64.getDecoder().decode(encodedStr), Charset.forName("UTF-8"));
  }

  static String javaEncode(String decodedStr)
  {
    byte[] decodedByteArr = decodedStr.getBytes(Charset.forName("UTF-8"));
    return java.util.Base64.getEncoder().encodeToString(decodedByteArr);
  }
}
    
