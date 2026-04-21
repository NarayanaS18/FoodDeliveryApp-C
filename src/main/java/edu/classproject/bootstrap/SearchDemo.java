package edu.classproject.bootstrap;

import edu.classproject.restaurant.InMemoryRestaurantRepository;
import edu.classproject.restaurant.MenuItem;
import edu.classproject.restaurant.Restaurant;
import edu.classproject.restaurant.RestaurantRepository;
import edu.classproject.search.SearchResult;
import edu.classproject.search.SearchService;
import edu.classproject.search.SearchServiceImpl;

import java.util.List;

public class SearchDemo {
    public static void main(String[] args) {
        RestaurantRepository repo = new InMemoryRestaurantRepository();

        Restaurant r1 = new Restaurant("r1", "Burger King");
        r1.addMenuItem(new MenuItem("i1", "Cheeseburger", null));
        r1.addMenuItem(new MenuItem("i2", "Fries", null));
        repo.save(r1);

        Restaurant r2 = new Restaurant("r2", "Pizza Palace");
        r2.addMenuItem(new MenuItem("i3", "Margherita Pizza", null));
        r2.addMenuItem(new MenuItem("i4", "Garlic Bread", null));
        repo.save(r2);

        Restaurant r3 = new Restaurant("r3", "Hot Burger Hub");
        r3.addMenuItem(new MenuItem("i5", "Spicy Burger", null));
        repo.save(r3);

        Restaurant r4 = new Restaurant("r4", "Burger Palace");
    r4.addMenuItem(new MenuItem("i6", "Veg Burger", null));
    repo.save(r4);

        SearchService search = new SearchServiceImpl(repo);

        runQuery(search, "Burger");
        runQuery(search, "pizza");
        runQuery(search, "garlic");
    }

    private static void runQuery(SearchService search, String q) {
        System.out.println("\nSearch: " + q);
        List<SearchResult> results = search.searchRestaurants(q);
        for (SearchResult r : results) {
            if (r.matchedValue() != null && !r.matchedValue().isBlank()) {
                System.out.printf("- %s (id=%s) matched by %s -> %s%n", r.restaurantName(), r.restaurantId(), r.matchedBy(), r.matchedValue());
            } else {
                System.out.printf("- %s (id=%s) matched by %s%n", r.restaurantName(), r.restaurantId(), r.matchedBy());
            }
        }
        if (results.isEmpty()) {
            System.out.println("No results");
        }
    }
}
