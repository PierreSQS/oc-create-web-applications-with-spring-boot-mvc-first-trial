package com.openclassrooms.watchlist;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.watchlist.services.WatchlistService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WatchlistControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	WatchlistService watchlistServ;

	@Test
	public void testGetWatchlist() throws Exception {
		mockMvc.perform(get("/watchlist"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attribute("numberOfMovies", equalTo(0)))
				.andExpect(model().attributeExists("watchlistItems"));
	
	}

	@Test
	public void testShowWatchlistItemForm() throws Exception {
		mockMvc.perform(get("/watchlistItemForm"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("watchlistItemForm"))
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("watchlistItem"));

	}

	@Test
	public void testSubmitWatchlistItemForm() throws Exception {
		mockMvc.perform(post("/watchlistItemForm")
							.param("title", "Top Gun")
							.param("rating", "5.5")
							.param("priority", "L")
							.param("comment", "no content in the story!!!"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/watchlist"));

	}

}
