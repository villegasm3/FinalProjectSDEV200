package application;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieInventoryApp extends Application {

    private TextField titleField, directorField, yearField, genreField, mpaaField;
    private Label titleLabel, directorLabel, yearLabel, genreLabel, mpaaLabel, statusLabel;

    private List<Movie> movieList;
    private MovieSearch movieFileSearch;

    @Override
    public void start(Stage primaryStage) {
        movieFileSearch = new MovieSearch();
        movieList = movieFileSearch.loadMovies();

        titleLabel = new Label("Movie Title:");
        titleField = new TextField();
        directorLabel = new Label("Director:");
        directorField = new TextField();
        yearLabel = new Label("Release Year:");
        yearField = new TextField();
        genreLabel = new Label("Genre:");
        genreField = new TextField();
        mpaaLabel = new Label("MPAA Raiting:");
        mpaaField = new TextField();
        statusLabel = new Label();

        // create a grid pane
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(0, 0, 0, 20)); // add padding on the left
        root.add(titleLabel, 0, 0);
        root.add(titleField, 1, 0);
        root.add(directorLabel, 0, 1);
        root.add(directorField, 1, 1);
        root.add(yearLabel, 0, 2);
        root.add(yearField, 1, 2);
        root.add(genreLabel, 0, 3);
        root.add(genreField, 1, 3);
        root.add(mpaaLabel, 0, 4);
        root.add(mpaaField, 1, 4);

        Button addButton = new AddMovie("Add", titleField, directorField, yearField, genreField, mpaaField, statusLabel);
        Button searchButton = new Button("Search");
        HBox buttonBox = new HBox(addButton, searchButton);
        buttonBox.setSpacing(10);
        root.add(buttonBox, 0, 5);

        root.add(statusLabel, 1, 5);

        // create a VBox to hold search results
        VBox searchResults = new VBox();
        root.add(searchResults, 2, 0, 1, 6);

        // set the action for the search button
        searchButton.setOnAction(e -> {
            String searchTitle = titleField.getText();
            List<Movie> results = movieFileSearch.searchMovies(searchTitle);

            // clear previous search results
            searchResults.getChildren().clear();

            // add each search result as a Label to the VBox
            for (Movie movie : results) {
                Label movieLabel = new Label(movie.toString());
                searchResults.getChildren().add(movieLabel);
            }
        });

        Scene scene = new Scene(root, 800, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Movie Inventory");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
