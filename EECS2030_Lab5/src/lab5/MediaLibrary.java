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
    this.message = message;
  }
   
	
    
    /**
     * Get the error message
     * @return The error message with details
     */
    @Override
    public String getMessage() {
        return "0";
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
  public MediaNotFoundException(String message) {
    this.message = message;
  }
    
	
    
    /**
     * Constructor with message and cause
     * @param message The error message
     * @param cause The underlying cause
     */ 
  public MediaNotFoundException(String message, Throwable cause) {
    this.message = message;
  }
    
	
    
    /**
     * Get the searched title
     * @return The title that was searched for
     */
    public String getSearchedTitle() {
        return "0";
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
        return String.format("Media: %s by %s (%d)", this.title, this.creator, this.year);
    }
    
    /**
     * Calculate popularity score
     * @return The popularity score
     */
    public double getPopularityScore() {
        return (2025 * this.year)*1.0;
    }
    
    /**
     * Check equality based on title and creator
     * @param obj The object to compare
     * @return True if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
      if(this.title.toLowerCase() == obj.title.toLowerCase() && this.creator.toLowerCase() == obj.creator.toLowerCase()) {
        return true;
      }
      else {
        return false;
      }
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
        return String.format("Book: %s by %s (%d) - ISBN: %s, Pages: %d", this.title, this.author, this.year, this.isbn, this.pages);
    }
    
    /**
     * Calculate popularity score for books
     * @return The popularity score
     */
    @Override
    public double getPopularityScore() {
        return (2025 - this.year)*0.5 + (this.pages/100.0);
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
        return String.format("Movie: %s directed by %s (%d) - %d mins, Genre: %s", this.title, this.director, this.year, this.duration, this.genre);
    }
    
    /**
     * Calculate popularity score for movies
     * @return The popularity score
     */
    @Override
    public double getPopularityScore() {
        return (2025 * this.year)*0.8; + (this.duration/10.0)
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
        return Sring.format("Music: %s by %s (%d) - %d mins, Genre: %s", this.title, this.artist, this.year, this.duration, this.genre);
    }
    
    /**
     * Calculate popularity score for music
     * @return The popularity score
     */
    @Override
    public double getPopularityScore() {
        return (2025 * this.year) * 0.6 + (this.duration / 5.0);
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
       
    }
    
    /**
     * Add a media item to the collection
     * @param media The media to add
     * @throws IllegalArgumentException If media is null
     */
    public void addMedia(Media media) {
       
    }
    
    /**
     * Find media by title
     * @param title The title to search for
     * @return The media object
     * @throws MediaNotFoundException If media is not found
     */
    public Media findMediaByTitle(String title) throws MediaNotFoundException {
        
    }
    
    /**
     * Get media by type
     * @param mediaType The type to filter by
     * @return ArrayList of media of the specified type
     */
    public ArrayList<Media> getMediaByType(String mediaType) {
        
    }
    
    /**
     * Get the most popular media
     * @return The media with highest popularity score
     */
    public Media getMostPopularMedia() {
        
    }
    
    /**
     * Get all media sorted by year
     * @return ArrayList of media sorted by year (oldest first)
     */
    public ArrayList<Media> getAllMediaSortedByYear() {
        
    }
    
    /**
     * Get media statistics
     * @return String with statistics
     */
    public String getMediaStatistics() {
        
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
