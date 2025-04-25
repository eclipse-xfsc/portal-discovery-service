package utils;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Log4j2
public class RequestHandling {
  public static void logRequest(final HttpServletRequest request) {
    log.info("request.getQueryString(): {}", request.getQueryString());
    final Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      final String headerName = headerNames.nextElement();
      log.info("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
    }

    final Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
      final String paramName = params.nextElement();
      log.info("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
    }

//    final Enumeration<String> attributes = request.getAttributeNames();
//    while (attributes.hasMoreElements()) {
//      final String attributeName = attributes.nextElement();
//      log.info("Attribute Name - " + attributeName + ", Value - " + request.getAttribute(attributeName));
//    }
  }
}
