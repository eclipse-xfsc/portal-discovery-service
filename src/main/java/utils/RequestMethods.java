package utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;

public class RequestMethods {
  public static String extractRequestBody(HttpServletRequest request) {
    Scanner s = null;
    try {
      s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return s.hasNext() ? s.next() : "";
  }
}
