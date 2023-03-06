package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MovieSearch {

	private List<Movie> movieList;

	public MovieSearch() {
		movieList = loadMovies();
	}

	List<Movie> loadMovies() {
		List<Movie> movieList = new ArrayList<Movie>();
		try (BufferedReader reader = new BufferedReader(new FileReader("movie.dat"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split("\\|");
				String title = fields[0];
				String director = fields[1];
				String genre = fields[2];
				int year = Integer.parseInt(fields[3]);
				String mpaaRating = fields[4];
				Movie movie = new Movie(title, director, year, genre, mpaaRating);
				movieList.add(movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieList;
	}

	public List<Movie> searchMovies(String title) {
		List<Movie> results = new ArrayList<>();
		for (Movie movie : movieList) {
			if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
				results.add(movie);
			}
		}
		return results;
	}

	public void saveMovies() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("movie.dat"))) {
			for (Movie movie : movieList) {
				writer.println(movie.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
