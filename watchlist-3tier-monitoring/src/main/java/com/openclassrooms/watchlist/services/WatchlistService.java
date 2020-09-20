package com.openclassrooms.watchlist.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exceptions.DuplicateTitleException;
import com.openclassrooms.watchlist.repositories.WatchlistRepository;
import com.openclassrooms.watchlist.exceptions.MaxItemsExceededException;

@Service
public class WatchlistService {

	private static final int MAXITEMSCOUNT = 10;
	
	private final Logger log = LoggerFactory.getLogger(WatchlistService.class);

	@Autowired
	private WatchlistRepository watchlistRepo;

	@Autowired
	private MovieRatingService movieRatingServ;

//  NOT NECESSARY WITH DI!	
//	protected WatchlistService() {
//		watchlistRepo = new WatchlistRepository();
//		movieRatingServ = new MovieRatingService();
//	}

	public List<WatchlistItem> getItemList() {
		log.debug("Getting the Item List");
		List<WatchlistItem> watchList = watchlistRepo.getWatchList();
		watchList.forEach(item -> {
			Optional<String> movieRating = movieRatingServ.getMovieRating(item.getTitle());
			if (!movieRating.get().isEmpty() &&   movieRating.isPresent()) {
				item.setRating(movieRating.get());
			}
		});
		log.debug("The Watchlist: {}",watchList);
		return watchList;
	}

	public void addOrUpdateItemList(WatchlistItem item) throws DuplicateTitleException, MaxItemsExceededException {
		WatchlistItem theItem = watchlistRepo.findWatchlistItemById(item.getId());

		if (theItem == null) {

			if (watchlistRepo.isItemAlreadyExists(item.getTitle())) {

				throw new DuplicateTitleException();

			} else if (watchlistRepo.getItemListSize() == MAXITEMSCOUNT) {

				throw new MaxItemsExceededException();

			} else {

				watchlistRepo.addItemInWatchList(item);
			}
		} else {

			// Changing the Title of the film means entering a new film.
			// This should not be possible through an update
			
			//theItem.setTitle(item.getTitle());
			
			theItem.setRating(item.getRating());
			theItem.setPriority(item.getPriority());
			theItem.setComment(item.getComment());
		}
	}

	public WatchlistItem updateItemForWatchlistForm(Integer itemId) {
		WatchlistItem watchlistItem = watchlistRepo.findWatchlistItemById(itemId);
		if (watchlistItem == null) {
			return new WatchlistItem();
		} else {
			return watchlistItem;
		}
	}

	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistRepo.findWatchlistItemById(id);
	}

	public int getMaxItems() {
		return MAXITEMSCOUNT;
	}

	public int getWatchlistSize() {
		return watchlistRepo.getItemListSize();
	}

}
