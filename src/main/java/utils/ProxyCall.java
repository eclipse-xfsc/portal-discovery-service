package utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Log4j2
public class ProxyCall {
  public static <T> ResponseEntity<T> doProxyCall(final WebClient srv, final HttpServletRequest request) {
    final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    request.getParameterMap().forEach((s, strings) -> queryParams.addAll(s, List.of(strings)));

    final WebClient.RequestHeadersSpec<?> callBuilder = srv
            .get()
            .uri(builder ->
                    builder.path(request.getRequestURI())
                           .queryParams(queryParams).build());

    final Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      final String hn = headerNames.nextElement();
      callBuilder.header(hn, request.getHeader(hn));
    }

    final String body = RequestMethods.extractRequestBody(request);
    if (!body.isEmpty()) {
      log.error("In GET request body is supplied: {}", body);
    }

    return callBuilder.retrieve()
                      .toEntity(new ParameterizedTypeReference<T>() {
                      }).block();

  }
}
