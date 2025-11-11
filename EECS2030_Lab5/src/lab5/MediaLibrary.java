package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Custom exception thrown when media file format is invalid
 */
class InvalidMediaFormatException extends Exception {
  	private static final long serialVersionUID = 1L;
	private String message;
    
    /**
     * Constructor with message
     * @param message The error message
     */ 
  public InvalidMediaFormatException(String message){
    this.message = message;
  }
    
	
    
    /**
     * Constructor with message and cause
     * @param message The error message
     * @param cause The underlying cause
     */ 
  public InvalidMediaFormatException(String message, Throwable cause) {
    super(message, cause);
    this.message = message;
  }
   
	
    
    /**
     * Get the error message
     * @return The error message with details
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}

/**
 * Custom exception thrown when media is not found
 */
class MediaNotFoundException extends Exception {
   	private static final long serialVersionUID = 1L;
	private String searchedTitle;
    
    /**
     * Constructor with title
     * @param title The title that was searched for
     */ 
  public MediaNotFoundException(String title) {
    this.searchedTitle = title;
  }
    
	
    
    /**
     * Constructor with message and cause
     * @param message The error message
     * @param cause The underlying cause
     */ 
  public MediaNotFoundException(String message, Throwable cause) {
    super(message, cause);
    this.searchedTitle = "";
  }
    
	
    
    /**
     * Get the searched title
     * @return The title that was searched for
     */
    public String getSearchedTitle() {
        return this.searchedTitle;
    }
}

/**
 * Parent class representing general media properties
 */
class Media {
    protected String title;
    protected String creator;
    protected int yearReleased;
    
    /**
     * Default constructor
     */
    public Media() {
        this.title = "";
        this.creator = "";
        this.yearReleased = 0;
    }
    
    /**
     * Parameterized constructor
     * @param title The media title
     * @param creator The creator (author/director/artist)
     * @param year The year released
     */
    public Media(String title, String creator, int year) {
        this.title = title;
        this.creator = creator;
        this.yearReleased = year;
    }
    
    /**
     * Get the title
     * @return The title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Set the title
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Get the creator
     * @return The creator
     */
    public String getCreator() {
        return creator;
    }
    
    /**
     * Set the creator
     * @param creator The creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    /**
     * Get the year released
     * @return The year released
     */
    public int getYearReleased() {
        return yearReleased;
    }
    
    /**
     * Set the year released
     * @param year The year to set
     */
    public void setYearReleased(int year) {
        this.yearReleased = year;
    }
    
    /**
     * Get the media type
     * @return The media type
     */
    public String getMediaType() {
        return "Generic Media";
    }
    
    /**
     * Get display information
     * @return Formatted display string
     */
    public String getDisplayInfo() {
        return String.format("Media: %s by %s (%d)", this.title, this.creator, this.yearReleased);
    }
    
    /**
     * Calculate popularity score
     * @return The popularity score
     */
    public double getPopularityScore() {
        return (2025 - this.yearReleased) * 1.0;
    }
    
    /**
     * Check equality based on title and creator
     * @param obj The object to compare
     * @return True if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Media media = (Media) obj;
        return title.equalsIgnoreCase(media.title) && creator.equalsIgnoreCase(media.creator);
    }
}

/**
 * Book class extending Media
 */
class Book extends Media {
    private String isbn;
    private int numberOfPages;
    
    /**
     * Default constructor
     */
    public Book() {
        super();
        this.isbn = "";
        this.numberOfPages = 0;
    }
    
    /**
     * Parameterized constructor
     * @param title The book title
     * @param author The author
     * @param year The year published
     * @param isbn The ISBN
     * @param pages The number of pages
     */
    public Book(String title, String author, int year, String isbn, int pages) {
        super(title, author, year);
        this.isbn = isbn;
        this.numberOfPages = pages;
    }
    
    /**
     * Get the ISBN
     * @return The ISBN
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * Set the ISBN
     * @param isbn The ISBN to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    /**
     * Get the number of pages
     * @return The number of pages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }
    
    /**
     * Set the number of pages
     * @param pages The number of pages to set
     */
    public void setNumberOfPages(int pages) {
        this.numberOfPages = pages;
    }
    
    /**
     * Get the media type
     * @return "Book"
     */
    @Override
    public String getMediaType() {
        return "Book";
    }
    
    /**
     * Get display information
     * @return Formatted book information
     */
    @Override
    public String getDisplayInfo() {
        return String.format("Book: %s by %s (%d) - ISBN: %s, Pages: %d", this.title, this.creator, this.yearReleased, this.isbn, this.numberOfPages);
    }
    
    /**
     * Calculate popularity score for books
     * @return The popularity score
     */
    @Override
    public double getPopularityScore() {
        return (2025 - this.yearReleased)*0.5 + (this.numberOfPages/100.0);
    }
}

/**
 * Movie class extending Media
 */
class Movie extends Media {
    private String genre;
    private int duration;
    
    /**
     * Default constructor
     */
    public Movie() {
        super();
        this.genre = "";
        this.duration = 0;
    }
    
    /**
     * Parameterized constructor
     * @param title The movie title
     * @param director The director
     * @param year The year released
     * @param duration The duration in minutes
     * @param genre The genre
     */
    public Movie(String title, String director, int year, int duration, String genre) {
        super(title, director, year);
        this.duration = duration;
        this.genre = genre;
    }
    
    /**
     * Get the genre
     * @return The genre
     */
    public String getGenre() {
        return genre;
    }
    
    /**
     * Set the genre
     * @param genre The genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /**
     * Get the duration
     * @return The duration in minutes
     */
    public int getDuration() {
        return duration;
    }
    
    /**
     * Set the duration
     * @param duration The duration in minutes
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    /**
     * Get the media type
     * @return "Movie"
     */
    @Override
    public String getMediaType() {
        return "Movie";
    }
    
    /**
     * Get display information
     * @return Formatted movie information
     */
    @Override
    public String getDisplayInfo() {
        return String.format("Movie: %s directed by %s (%d) - %d mins, Genre: %s", this.title, this.creator, this.yearReleased, this.duration, this.genre);
    }
    
    /**
     * Calculate popularity score for movies
     * @return The popularity score
     */
    @Override
    public double getPopularityScore() {
        return (2025 - this.yearReleased)*0.8 + (this.duration/10.0);
    }
}

/**
 * Music class extending Media
 */
class Music extends Media {
    private String genre;
    private int duration;
    
    /**
     * Default constructor
     */
    public Music() {
        super();
        this.genre = "";
        this.duration = 0;
    }
    
    /**
     * Parameterized constructor
     * @param title The album title
     * @param artist The artist
     * @param year The year released
     * @param duration The duration in minutes
     * @param genre The genre
     */
    public Music(String title, String artist, int year, int duration, String genre) {
        super(title, artist, year);
        this.duration = duration;
        this.genre = genre;
    }
    
    /**
     * Get the genre
     * @return The genre
     */
    public String getGenre() {
        return genre;
    }
    
    /**
     * Set the genre
     * @param genre The genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /**
     * Get the duration
     * @return The duration in minutes
     */
    public int getDuration() {
        return duration;
    }
    
    /**
     * Set the duration
     * @param duration The duration in minutes
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    /**
     * Get the media type
     * @return "Music"
     */
    @Override
    public String getMediaType() {
        return "Music";
    }
    
    /**
     * Get display information
     * @return Formatted music information
     */
    @Override
    public String getDisplayInfo() {
        return String.format("Music: %s by %s (%d) - %d mins, Genre: %s", this.title, this.creator, this.yearReleased, this.duration, this.genre);
    }
    
    /**
     * Calculate popularity score for music
     * @return The popularity score
     */
    @Override
    public double getPopularityScore() {
        return (2025 - this.yearReleased) * 0.6 + (this.duration / 5.0);
    }
}

/**
 * MediaLibrary class that manages a collection of media items
 */
public class MediaLibrary {
    private ArrayList<Media> mediaCollection;
    
    /**
     * Constructor
     */
    public MediaLibrary() {
        this.mediaCollection = new ArrayList<>();
    }
    
    /**
     * Load media from file
     * @param filename The file to load from
     * @throws InvalidMediaFormatException If the format is invalid
     * @throws FileNotFoundException If the file is not found
     */
    public void loadMediaFromFile(String filename) throws InvalidMediaFormatException, FileNotFoundException {
        File fileToRead = new File(filename);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(fileToRead);
            while (fileScanner.hasNextLine()) {
                String currentLine = fileScanner.nextLine().trim();
                if (currentLine.isEmpty()) {
                    continue;
                }
                String[] mediaParts = currentLine.split("\\|");
                try {
                    String mediaType = mediaParts[0];
                    String title = mediaParts[1];
                    String creator = mediaParts[2];
                    int year = Integer.parseInt(mediaParts[3]);

                    if (mediaType.equalsIgnoreCase("BOOK")) {
                        if (mediaParts.length != 6) {
                            throw new InvalidMediaFormatException("Invalid number of fields for Book: " + currentLine);
                        }
                        String isbn = mediaParts[4];
                        int pages = Integer.parseInt(mediaParts[5]);
                        Book newBook = new Book(title, creator, year, isbn, pages);
                        this.addMedia(newBook);
                    } else if (mediaType.equalsIgnoreCase("MOVIE")) {
                        if (mediaParts.length != 6) {
                            throw new InvalidMediaFormatException("Invalid number of fields for Movie: " + currentLine);
                        }
                        int duration = Integer.parseInt(mediaParts[4]);
                        String genre = mediaParts[5];
                        Movie newMovie = new Movie(title, creator, year, duration, genre);
                        this.addMedia(newMovie);
                    } else if (mediaType.equalsIgnoreCase("MUSIC")) {
                        if (mediaParts.length != 6) {
                            throw new InvalidMediaFormatException("Invalid number of fields for Music: " + currentLine);
                        }
                        int duration = Integer.parseInt(mediaParts[4]);
                        String genre = mediaParts[5];
                        Music newMusic = new Music(title, creator, year, duration, genre);
                        this.addMedia(newMusic);
                    } else {
                        throw new InvalidMediaFormatException("Unknown media type: " + mediaType);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidMediaFormatException("Error parsing line: " + currentLine, e);
                }
            }
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }
    
    /**
     * Add a media item to the collection
     * @param media The media to add
     * @throws IllegalArgumentException If media is null
     */
    public void addMedia(Media media) {
       if (media == null) {
            throw new IllegalArgumentException("Media cannot be null");
        }
        this.mediaCollection.add(media);
    }
    
    /**
     * Find media by title
     * @param title The title to search for
     * @return The media object
     * @throws MediaNotFoundException If media is not found
     */
    public Media findMediaByTitle(String title) throws MediaNotFoundException {
        for (Media media : this.mediaCollection) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        throw new MediaNotFoundException(title);
    }
    
    /**
     * Get media by type
     * @param mediaType The type to filter by
     * @return ArrayList of media of the specified type
     */
    public ArrayList<Media> getMediaByType(String mediaType) {
        ArrayList<Media> result = new ArrayList<>();
        for (Media media : this.mediaCollection) {
            if (media.getMediaType().equalsIgnoreCase(mediaType)) {
                result.add(media);
            }
        }
        return result;
    }
    
    /**
     * Get the most popular media
     * @return The media with highest popularity score
     */
    public Media getMostPopularMedia() {
        if (this.mediaCollection.isEmpty()) {
            return null;
        }
        Media mostPopular = this.mediaCollection.get(0);
        for (Media media : this.mediaCollection) {
            if (media.getPopularityScore() > mostPopular.getPopularityScore()) {
                mostPopular = media;
            }
        }
        return mostPopular;
    }
    
    /**
     * Get all media sorted by year
     * @return ArrayList of media sorted by year (oldest first)
     */
    public ArrayList<Media> getAllMediaSortedByYear() {
        ArrayList<Media> sortedList = new ArrayList<>(this.mediaCollection);
        sortedList.sort((media1, media2) -> Integer.compare(media1.getYearReleased(), media2.getYearReleased()));
        return sortedList;
    }
    
    /**
     * Get media statistics
     * @return String with statistics
     */
    public String getMediaStatistics() {
        int total = this.mediaCollection.size();
        if (total == 0) {
            return "No media in collection";
        }
        int books = 0;
        int movies = 0;
        int music = 0;
        int totalYear = 0;
        for (Media media : this.mediaCollection) {
            totalYear += media.getYearReleased();
            String mediaType = media.getMediaType();
            if (mediaType.equals("Book")) {
                books++;
            } else if (mediaType.equals("Movie")) {
                movies++;
            } else if (mediaType.equals("Music")) {
                music++;
            }
        }
        double averageYear = (double) totalYear / total;
        return String.format("Library Statistics:\nTotal items: %d\nBooks: %d\nMovies: %d\nMusic albums: %d\nAverage year of release: %.2f", total, books, movies, music, averageYear);
    }
    
    /**
     * Display all media
     */
    public void displayAllMedia() {
        for (Media media : mediaCollection) {
            System.out.println(media.getDisplayInfo());
        }
    }
    
    /**
     * Get the media collection (for testing purposes)
     * @return The media collection
     */
    public ArrayList<Media> getMediaCollection() {
        return mediaCollection;
    }
}
