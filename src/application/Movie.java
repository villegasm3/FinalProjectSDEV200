package application;

public class Movie {
    private String title;
    private String director;
    private int year;
    private String genre;
    private String mpaa;

    public Movie(String title, String director, int year, String genre, String mpaa) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.mpaa = mpaa;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getMpaa() {
        return mpaa;
    }

    @Override
    public String toString() {
        return title + " (" + year + ") by " + director + ", genre: " + genre + ", MPAA: " + mpaa;
    }

	public void setTitle(String hyperlink) {
		// TODO Auto-generated method stub
		
	}
}
