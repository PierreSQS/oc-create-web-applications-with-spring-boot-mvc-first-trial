package com.openclassrooms.watchlist.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.openclassrooms.watchlist.domain.WatchlistItem;

@Repository
public class WatchlistRepository {

	private List<WatchlistItem> watchlistItems;

	protected WatchlistRepository() {
		this.watchlistItems = new ArrayList<>();
	}

	public void addItemInWatchList(WatchlistItem item) {
		watchlistItems.add(item);
	}

	public List<WatchlistItem> getWatchList() {
		return watchlistItems;
	}

	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistItems.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
	}

	public Optional<WatchlistItem> findWatchlistItemByTitle(String title) {
		return watchlistItems.stream().filter(item -> item.getTitle().trim().equalsIgnoreCase(title.trim())).findFirst();
	}

	public boolean isItemAlreadyExists(String title) {

		return findWatchlistItemByTitle(title).isPresent();
	}
	
	public int getItemListSize() {
		return watchlistItems.size();
	}

}
