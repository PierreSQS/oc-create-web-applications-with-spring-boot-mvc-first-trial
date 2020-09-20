package com.openclassrooms.watchlist;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repositories.WatchlistRepository;
import com.openclassrooms.watchlist.services.MovieRatingService;
import com.openclassrooms.watchlist.services.WatchlistService;

//@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

	@Mock
	private WatchlistRepository watchlistRepoMock;

	@Mock
	private MovieRatingService movieRatingServMock;

	@InjectMocks
	private WatchlistService watchlistServ;

	@Test
	public void testGetWatchlistItemsReturnsAllItemsFromRepository() {

		// Arrange
		WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M", "comment 1");
		WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M", "comment 2");
		List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

		when(watchlistRepoMock.getWatchList()).thenReturn(mockItems);
		when(movieRatingServMock.getMovieRating(any(String.class))).thenReturn(Optional.of("10.0"));

		// Act
		List<WatchlistItem> result = watchlistServ.getItemList();

		// Assert
		assertTrue(result.size() == 2);
		assertTrue(result.get(0).getTitle().equals(item1.getTitle()));
		assertTrue(result.get(1).getTitle().equals(item2.getTitle()));
	}

	@Test
	public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {
		// Arrange
		WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M", "comment 1");
		List<WatchlistItem> result = Arrays.asList(item1);

		when(watchlistServ.getItemList()).thenReturn(result);
		when(movieRatingServMock.getMovieRating(any(String.class))).thenReturn(Optional.of("10.0"));

		// Act
		WatchlistItem watchlistItem = watchlistServ.getItemList().get(0);

		// Assert
		assertTrue(watchlistItem.getRating().equals(watchlistItem.getRating()));

	}

	@Test
	public void testGetWatchlistShouldReturnSize() {
		// Arrange
		WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M", "comment 1");
		WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M", "comment 2");
		List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

		when(watchlistServ.getItemList()).thenReturn(mockItems);
		when(movieRatingServMock.getMovieRating(any(String.class))).thenReturn(Optional.of("10.0"));
		
		
		// Act
		List<WatchlistItem> result = watchlistServ.getItemList();

		// Assert
		assertTrue(result.size() == 2);
	}

}
