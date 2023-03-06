package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddMovie extends Button {
    private TextField titleField, directorField, yearField, genreField, mpaaField;
    private Label statusLabel;

    public AddMovie(String text, TextField titleField, TextField directorField, TextField yearField, TextField genreField, TextField mpaaField, Label statusLabel) {
        super(text);
        this.titleField = titleField;
        this.directorField = directorField;
        this.yearField = yearField;
        this.genreField = genreField;
        this.mpaaField = mpaaField;
        this.statusLabel = statusLabel;
        setOnAction(new AddButtonHandler());
    }

    private class AddButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // get the values from the text fields
            String title = titleField.getText();
            String director = directorField.getText();
            String year = yearField.getText();
            String genre = genreField.getText();
            String mpaa = mpaaField.getText();

            // validate the input
            if (title.isEmpty() || director.isEmpty() || year.isEmpty() || genre.isEmpty() || mpaa.isEmpty()) {
                statusLabel.setText("Please fill in all fields");
                return;
            }
            if (!year.matches("\\d{4}")) {
                statusLabel.setText("Please enter a valid year");
                return;
            }

            // add the movie to the inventory file
            String movieData = title + "|" + director + "|" + genre + "|" + year + "|" + mpaa + "\n";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("movie.dat", true))) {
                writer.write(movieData);
            } catch (IOException e) {
                statusLabel.setText("Error writing to file");
                return;
            }

            // clear the text fields and show a success message
            titleField.clear();
            directorField.clear();
            yearField.clear();
            genreField.clear();
            mpaaField.clear();
            statusLabel.setText("Movie added to inventory");
        }
    }
}
