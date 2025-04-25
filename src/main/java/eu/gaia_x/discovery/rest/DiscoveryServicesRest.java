package eu.gaia_x.discovery.rest;

import eu.gaia_x.discovery.dto.*;
import eu.gaia_x.discovery.dto.chips.ChipsDTO;
import eu.gaia_x.discovery.dto.chips.ChipsEntry;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import utils.ProxyCall;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Log4j2
@RestController
@RequestMapping("/api/discovery")
@ApiOperation("Discovery Service")
public class DiscoveryServicesRest {

  @Autowired
  @Qualifier("demoSrv")
  WebClient demoSrv;

  @ApiOperation("Receiving Contacts for Service")
  @GetMapping(value = {"/services/{id}/contacts", "/data/{id}/contacts", "/ppr/{id}/contacts"})
  @ResponseBody
  public List<ContactInfo> getContactsForService(
          @PathVariable final String id,
          final HttpServletRequest request
  ) {
    return Objects.requireNonNull(ProxyCall.<List<Map<String, String>>>doProxyCall(demoSrv, request)
                                           .getBody()).stream().map(ContactInfo::from).collect(Collectors.toList());

  }

  @ApiOperation("Receiving Screenots of Service")
  @GetMapping(value = {"/services/{id}/screenshots"})
  @ResponseBody
  public List<ScreenshotInfo> getScreenshotsForService(
          @PathVariable final String id,
          final HttpServletRequest request
  ) {
    return Objects.requireNonNull(ProxyCall.<List<Map<String, String>>>doProxyCall(demoSrv, request)
                                           .getBody()).stream().map(ScreenshotInfo::from).collect(Collectors.toList());
  }

  @ApiOperation("Receiving Price for Service")
  @GetMapping(value = {"/services/{id}/price", "/data/{id}/price"})
  @ResponseBody
  public List<PriceInfo> getDataPriceForService(
          @PathVariable final String id,
          final HttpServletRequest request
  ) {
    return Objects.requireNonNull(ProxyCall.<List<PriceInfo>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Receiving PPR Details")
  @GetMapping(value = {"/ppr/{pprId}/details"})
  @ResponseBody
  public PprDetails getPprDetails(
          @PathVariable final String pprId,
          final HttpServletRequest request
  ) {
    Map<String, ?> map = Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
    return new PprDetails(
            (String) map.get("description"),
            ((List<String>) map.get("certificates")).toArray(new String[0]),
            (String) map.get("location"),
            (String) map.get("location_flag"),
            (String) map.get("member_since"),
            (String) map.get("last_updated")
    );
  }

  @ApiOperation("Get Sample Records")
  @GetMapping(value = {"/data/{dataId}/sample-records"})
  @ResponseBody
  public List<SampleRecord> getSampleRecords(
          @PathVariable final String dataId,
          final HttpServletRequest request
  ) {
    return Objects.requireNonNull(ProxyCall.<List<SampleRecord>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Get Data Details")
  @GetMapping(value = {"/data/{dataId}/details"})
  @ResponseBody
  public DataDetails getDataDetails(
          @PathVariable final String dataId,
          final HttpServletRequest request
  ) {
    Map<String, ?> map = Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
    return new DataDetails(
            (String) map.get("id"),
            (String) map.get("name"),
            (String) map.get("img_preview_url"),
            (String) map.get("ppr_name"),
            (String) map.get("ppr_url"),
            (String) map.get("description"),
            (String) map.get("source"),
            (String) map.get("cloud_service"),
            (String) map.get("location"),
            (String) map.get("location_flag"),
            (String) map.get("frequency_of_update"),
            (String) map.get("last_updated"),
            (List<String>) map.get("tags"),
            (String) map.get("category")
    );
  }

  @ApiOperation("Receiving PPR Data Details")
  @GetMapping(value = {"/ppr/{pprId}/data"})
  @ResponseBody
  public List<DataDetails> getPprDataDetails(
          @PathVariable final String pprId,
          final HttpServletRequest request
  ) {
    return Objects.requireNonNull(ProxyCall.<List<DataDetails>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Receiving Service Details")
  @GetMapping(value = {"/services/{id}/details"})
  @ResponseBody
  public ServiceDetails getServiceDetails(
          @PathVariable final String id,
          final HttpServletRequest request
  ) {

    Map<String, ?> map = Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
    return new ServiceDetails(
            (String) map.get("id"),
            (String) map.get("name"),
            (String) map.get("img_preview_url"),
            (String) map.get("logo"),
            (String) map.get("ppr_name"),
            (String) map.get("ppr_url"),
            (String) map.get("description"),
            (String) map.get("features"),
            (String) map.get("stack"),
            (String) map.get("security"),
            (String) map.get("location"),
            (String) map.get("location_flag"),
            (String) map.get("last_updated"),
            (String) map.get("category"),
            (List<String>) map.get("tags"),
            (String) map.get("terms_of_use"),
            (List<ServiceDetails>) map.get("dependent_services")
    );
  }

  @ApiOperation("Receiving PPR Services")
  @GetMapping(value = {"/ppr/{id}/services"})
  @ResponseBody
  public List<ServiceDetails> getPprServices(
          @PathVariable final String id,
          final HttpServletRequest request
  ) {
    return Objects.requireNonNull(ProxyCall.<List<ServiceDetails>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Get Filter Criteria")
  @GetMapping(value = {"/data/filter-criterias"})
  @ResponseBody
  public Map<String, ?> dataFilter(final HttpServletRequest request) {
    return Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Chips")
  @GetMapping(value = {"/data/suggestions", "/ppr/suggestions", "/services/suggestions"})
  @ResponseBody
  public ChipsDTO chipsSuggestions(final HttpServletRequest request) {
    return new ChipsDTO(Objects.requireNonNull(ProxyCall.<List<ChipsEntry>>doProxyCall(demoSrv, request).getBody()));
  }

  @ApiOperation("Get Filter Criteria for Services")
  @GetMapping(value = {"/services/filter-criterias", "/services/{serviceId}/{slotId}/filter-criterias"})
  @ResponseBody
  public Map<String, ?> servicesFilter(final HttpServletRequest request) {
    return Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Get PPR Filter Criteria")
  @GetMapping(value = {"/ppr/filter-criterias"})
  @ResponseBody
  public Map<String, ?> pprFilter(final HttpServletRequest request) {
    return Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Data Filter")
  @GetMapping(value = {"/data/search"})
  @ResponseBody
  public Map<String, ?> dataSearch(
          final HttpServletRequest request,
          @RequestParam MultiValueMap<String, String> params
  ) {
    return Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("PPR Filter")
  @GetMapping(value = {"/ppr/search"})
  @ResponseBody
  public Map<String, ?> pprSearch(
          final HttpServletRequest request,
          @RequestParam MultiValueMap<String, String> params
  ) {
    return Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
  }

  @ApiOperation("Search for Services")
  @GetMapping(value = {"/services/search", "/services/{serviceId}/{slotId}/search"})
  @ResponseBody
  public Map<String, ?> srvSearch(
          final HttpServletRequest request,
          @RequestParam MultiValueMap<String, String> params
  ) {
    return Objects.requireNonNull(ProxyCall.<Map<String, ?>>doProxyCall(demoSrv, request).getBody());
  }
}
