package eu.gaia_x.discovery.rest;

import eu.gaia_x.discovery.dto.*;
import eu.gaia_x.discovery.dto.chips.ChipsDTO;
import eu.gaia_x.discovery.dto.chips.ChipsEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("unused")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class DiscoveryServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiscoveryServicesRest service;

	@Test
	void getContactsReturnCode() throws Exception {
		List<ContactInfo> coll = Collections.singletonList(
				ContactInfo.of(ContactType.TECH_EMAIL, "my@email.com"));
		when(service.getContactsForService(any(), any())).thenReturn(coll);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/1/contacts"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("my@email.com")));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/1/contacts"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("my@email.com")));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/1/contacts"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("my@email.com")));
	}

	@Test
	void getScreenshotsForService() throws Exception {
		List<ScreenshotInfo> coll = Collections.singletonList(
				ScreenshotInfo.of("12321", new ScreenshotInfo.PreviewImg[0]));
		when(service.getScreenshotsForService(any(), any())).thenReturn(coll);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/12321/screenshots"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("12321")));
	}

	@Test
	void getDataPriceForService() throws Exception {
		List<PriceInfo> coll = Collections.singletonList(
				new PriceInfo("12321", "priceName", "12.4"));
		when(service.getDataPriceForService(any(), any())).thenReturn(coll);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/12321/price"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("priceName")));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/12321/price"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("priceName")));
	}

	@Test
	void getPprDetails() throws Exception {
		PprDetails rs = new PprDetails(
				"description",
				new String[0],
				"Germany",
				"Germany",
				"2020",
				"2022");
		when(service.getPprDetails(any(), any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/12321/details"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Germany")));
	}

	@Test
	void getSampleRecords() throws Exception {
		List<SampleRecord> rs = Collections.singletonList(
				new SampleRecord("99", "desc", "url.url"));
		when(service.getSampleRecords(any(), any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/99/sample-records"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("url.url")));
	}

	@Test
	void getDataDetails() throws Exception {
		DataDetails rs = new DataDetails(
				"990",
				"name",
				"img.url",
				"pprName",
				"ppr.url",
				"desc",
				"source",
				"cloud-service",
				"Germany",
				"Germany",
				"2022",
				"2022",
				Collections.emptyList(),
				"category");
		when(service.getDataDetails(any(), any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/990/details"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("cloud-service")));
	}

	@Test
	void getPprDataDetails() throws Exception {
		List<DataDetails> rs = Collections.singletonList(new DataDetails(
				"990",
				"name",
				"img.url",
				"pprName",
				"ppr.url",
				"desc",
				"source",
				"cloud-service",
				"Germany",
				"Germany",
				"2022",
				"2022",
				Collections.emptyList(),
				"category"));
		when(service.getPprDataDetails(any(), any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/12/data"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("cloud-service")));
	}

	@Test
	void getServiceDetails() throws Exception {
		ServiceDetails rs = new ServiceDetails("990",
				"name",
				"img.url",
				"logo",
				"pprName",
				"ppr.url",
				"desc",
				"features",
				"stack",
				"security",
				"Germany",
				"Germany",
				"2022",
				"category",
				Collections.emptyList(),
				"terms",
				Collections.emptyList());
		when(service.getServiceDetails(any(), any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/990/details"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("ppr.url")));
	}

	void getPprServices() throws Exception {
		List<ServiceDetails> rs = Collections.singletonList(new ServiceDetails("990",
				"name",
				"img.url",
				"logo",
				"pprName",
				"ppr.url",
				"desc",
				"features",
				"stack",
				"security",
				"Germany",
				"Germany",
				"2022",
				"category",
				Collections.emptyList(),
				"terms",
				Collections.emptyList()));
		when(service.getPprServices(any(), any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/990/services"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("ppr.url")));
	}

	void dataFilter() throws Exception {
		when(service.dataFilter(any())).thenReturn(new HashMap<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/filter-criterias"))
				.andExpect(status().isOk());
	}

	void chipsSuggestions() throws Exception {
		ChipsDTO rs = new ChipsDTO(
				Collections.singletonList(new ChipsEntry("lavel_1", "term_2")));
		when(service.chipsSuggestions(any())).thenReturn(rs);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/suggestions"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("lavel_1")));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/suggestions"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("lavel_1")));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/suggestions"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("lavel_1")));
	}

	void servicesFilter() throws Exception {
		when(service.servicesFilter(any())).thenReturn(new HashMap<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/filter-criterias"))
				.andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/1/2/filter-criterias"))
				.andExpect(status().isOk());
	}

	void pprFilter() throws Exception {
		when(service.pprFilter(any())).thenReturn(new HashMap<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/filter-criterias"))
				.andExpect(status().isOk());
	}

	void dataSearch() throws Exception {
		when(service.dataSearch(any(), any())).thenReturn(new HashMap<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/data/search"))
				.andExpect(status().isOk());
	}

	void pprSearch() throws Exception {
		when(service.pprSearch(any(), any())).thenReturn(new HashMap<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/ppr/search"))
				.andExpect(status().isOk());
	}

	void srvSearch() throws Exception {
		when(service.srvSearch(any(), any())).thenReturn(new HashMap<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/search"))
				.andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/discovery/services/1/2/search"))
				.andExpect(status().isOk());
	}
}
