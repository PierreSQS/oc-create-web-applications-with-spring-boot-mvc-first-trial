package com.openclassrooms.watchlist.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.exceptions.DuplicateTitleException;
import com.openclassrooms.watchlist.services.WatchlistService;
import com.openclassrooms.watchlist.exceptions.MaxItemsExceededException;


@Controller
public class WatchlistController {
	
	private final Logger log = LoggerFactory.getLogger(WatchlistController.class);
	
	@Autowired
	private WatchlistService watchlistServ;

	@GetMapping("/watchlist")
	public ModelAndView getWatchList() {
		log.info("GET /watchlist called");
		
		String viewName = "watchlist";
		Map<String, Object> modelView = new HashMap<>();
		
		modelView.put("watchlistItems", watchlistServ.getItemList() );
		modelView.put("numberOfMovies", watchlistServ.getWatchlistSize());
		return new ModelAndView(viewName, modelView);
	}

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
		log.info("GET /watchlistItemForm called with ID = {}",id);


		String viewName = "watchlistItemForm";
		Map<String, Object> model = new HashMap<String, Object>();
		
// TODO Also works but ???????
//		WatchlistItem watchlistItem = watchlistServ.updateItemForWatchlistForm(id);
		WatchlistItem watchlistItem = watchlistServ.findWatchlistItemById(id);

		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);
		}
		return new ModelAndView(viewName, model);		

// THIS PART IS CRITICAL FOR THE UNIT TESTS BUT WORKS WITH THE APPLICATION!!!!!!!!		
//		WatchlistItem watchlistItem = watchlistServ.updateItemForWatchlistForm(id);
//
//		model.put("watchlistItem", watchlistItem);
//
//		return new ModelAndView(viewName, model);
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {
		log.info("POST /watchlistItemForm called");

		if (bindingResult.hasErrors()) {
			log.debug("Validation Error on the view!!!");
			return new ModelAndView("watchlistItemForm");
		}

		try {
			watchlistServ.addOrUpdateItemList(watchlistItem);
		} catch (DuplicateTitleException e) {

			bindingResult.rejectValue("title", "", "This movie is already on your watchlist");
			return new ModelAndView("watchlistItemForm");
		} catch (MaxItemsExceededException e) {

			bindingResult.rejectValue(null, "",
					"The maximal amount (" + watchlistServ.getMaxItems() + ") of movies exceeded! ");
			return new ModelAndView("watchlistItemForm");
		}

		RedirectView redirView = new RedirectView();
		redirView.setUrl("/watchlist");
		return new ModelAndView(redirView);
	}

}
