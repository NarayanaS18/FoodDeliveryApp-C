package edu.classproject.search;

import edu.classproject.restaurant.MenuItem;
import edu.classproject.restaurant.Restaurant;
import edu.classproject.restaurant.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchServiceImpl implements SearchService {
    private final RestaurantRepository repository;

    public SearchServiceImpl(RestaurantRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public List<SearchResult> searchRestaurants(String query) {
        List<SearchResult> results = new ArrayList<>();
        if (query == null) {
            return results;
        }

        String q = query.trim().toLowerCase();
        if (q.isEmpty()) {
            return results;
        }

        for (Restaurant r : repository.findAll()) {
            String name = r.name() == null ? "" : r.name();

            // collect all menu items that match the query
            List<String> matchingItems = new ArrayList<>();
            for (MenuItem item : r.menu().values()) {
                String itemName = item.name() == null ? "" : item.name();
                if (itemName.toLowerCase().contains(q)) {
                    matchingItems.add(itemName);
                }
            }

            String matchedItemsValue = matchingItems.isEmpty() ? null : String.join(", ", matchingItems);

            // if restaurant name matches, still include matching menu items (if any)
            if (name.toLowerCase().contains(q)) {
                results.add(new SearchResult(r.restaurantId(), name, "name", matchedItemsValue));
                continue;
            }

            // if name doesn't match but some menu items matched, report as item match
            if (matchedItemsValue != null) {
                results.add(new SearchResult(r.restaurantId(), name, "item", matchedItemsValue));
            }
        }

        return results;
    }
}
