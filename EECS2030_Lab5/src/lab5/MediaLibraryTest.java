package lab5;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * JUnit 5 test class for MediaLibrary
 * Tests execute in order by method name
 * @author Dr. Sukhwant Sagar
*/
 
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MediaLibraryTest {
    
    private static final String TEST_FILE = "test_media.txt";
    private static final String INVALID_FILE = "invalid_media.txt";
    private MediaLibrary library;
    
    @BeforeEach
    public void setUp() {
        library = new MediaLibrary();
    }
    
    @AfterEach
    public void tearDown() {
        // Clean up test files
        new File(TEST_FILE).delete();
        new File(INVALID_FILE).delete();
    }
    
    // ==================== EXCEPTION TESTS ====================
    
    @Test
    public void test01_InvalidMediaFormatException_Constructor() {
        InvalidMediaFormatException ex = new InvalidMediaFormatException("Test error");
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("Test error"));
    }
    
    @Test
    public void test02_InvalidMediaFormatException_WithCause() {
        Throwable cause = new NumberFormatException("Invalid number");
        InvalidMediaFormatException ex = new InvalidMediaFormatException("Parse error", cause);
        assertNotNull(ex);
        assertEquals(cause, ex.getCause());
    }
    
    @Test
    public void test03_MediaNotFoundException_Constructor() {
        MediaNotFoundException ex = new MediaNotFoundException("Titanic");
        assertNotNull(ex);
        assertEquals("Titanic", ex.getSearchedTitle());
    }
    
    @Test
    public void test04_MediaNotFoundException_GetSearchedTitle() {
        MediaNotFoundException ex = new MediaNotFoundException("Avatar");
        assertEquals("Avatar", ex.getSearchedTitle());
    }
    
    // ==================== MEDIA CLASS TESTS ====================
    
    @Test
    public void test05_Media_DefaultConstructor() {
        Media media = new Media();
        assertEquals("", media.getTitle());
        assertEquals("", media.getCreator());
        assertEquals(0, media.getYearReleased());
    }
    
    @Test
    public void test06_Media_ParameterizedConstructor() {
        Media media = new Media("Test Title", "Test Creator", 2020);
        assertEquals("Test Title", media.getTitle());
        assertEquals("Test Creator", media.getCreator());
        assertEquals(2020, media.getYearReleased());
    }
    
    @Test
    public void test07_Media_Setters() {
        Media media = new Media();
        media.setTitle("New Title");
        media.setCreator("New Creator");
        media.setYearReleased(2021);
        
        assertEquals("New Title", media.getTitle());
        assertEquals("New Creator", media.getCreator());
        assertEquals(2021, media.getYearReleased());
    }
    
    @Test
    public void test08_Media_GetMediaType() {
        Media media = new Media("Test", "Creator", 2020);
        assertEquals("Generic Media", media.getMediaType());
    }
    
    @Test
    public void test09_Media_GetDisplayInfo() {
        Media media = new Media("Test Media", "John Doe", 2020);
        String expected = "Media: Test Media by John Doe (2020)";
        assertEquals(expected, media.getDisplayInfo());
    }
    
    @Test
    public void test10_Media_GetPopularityScore() {
        Media media = new Media("Test", "Creator", 2020);
        double expected = (2025 - 2020) * 1.0;
        assertEquals(expected, media.getPopularityScore(), 0.01);
    }
    
    @Test
    public void test11_Media_Equals_SameObject() {
        Media media1 = new Media("Title", "Creator", 2020);
        Media media2 = new Media("Title", "Creator", 2021);
        assertTrue(media1.equals(media2));
    }
    
    @Test
    public void test12_Media_Equals_DifferentTitle() {
        Media media1 = new Media("Title1", "Creator", 2020);
        Media media2 = new Media("Title2", "Creator", 2020);
        assertFalse(media1.equals(media2));
    }
    
    @Test
    public void test13_Media_Equals_CaseInsensitive() {
        Media media1 = new Media("TITLE", "CREATOR", 2020);
        Media media2 = new Media("title", "creator", 2020);
        assertTrue(media1.equals(media2));
    }
    
    // ==================== BOOK CLASS TESTS ====================
    
    @Test
    public void test14_Book_DefaultConstructor() {
        Book book = new Book();
        assertEquals("", book.getTitle());
        assertEquals("", book.getIsbn());
        assertEquals(0, book.getNumberOfPages());
    }
    
    @Test
    public void test15_Book_ParameterizedConstructor() {
        Book book = new Book("1984", "George Orwell", 1949, "978-0451524935", 328);
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getCreator());
        assertEquals(1949, book.getYearReleased());
        assertEquals("978-0451524935", book.getIsbn());
        assertEquals(328, book.getNumberOfPages());
    }
    
    @Test
    public void test16_Book_Setters() {
        Book book = new Book();
        book.setIsbn("123-456");
        book.setNumberOfPages(250);
        
        assertEquals("123-456", book.getIsbn());
        assertEquals(250, book.getNumberOfPages());
    }
    
    @Test
    public void test17_Book_GetMediaType() {
        Book book = new Book("Title", "Author", 2020, "ISBN", 200);
        assertEquals("Book", book.getMediaType());
    }
    
    @Test
    public void test18_Book_GetDisplayInfo() {
        Book book = new Book("1984", "George Orwell", 1949, "978-0451524935", 328);
        String expected = "Book: 1984 by George Orwell (1949) - ISBN: 978-0451524935, Pages: 328";
        assertEquals(expected, book.getDisplayInfo());
    }
    
    @Test
    public void test19_Book_GetPopularityScore() {
        Book book = new Book("Title", "Author", 2020, "ISBN", 300);
        double expected = (2025 - 2020) * 0.5 + (300 / 100.0);
        assertEquals(expected, book.getPopularityScore(), 0.01);
    }
    
    @Test
    public void test20_Book_Polymorphism() {
        Media media = new Book("Title", "Author", 2020, "ISBN", 200);
        assertEquals("Book", media.getMediaType()); // Dynamic binding
    }
    
    // ==================== MOVIE CLASS TESTS ====================
    
    @Test
    public void test21_Movie_DefaultConstructor() {
        Movie movie = new Movie();
        assertEquals("", movie.getTitle());
        assertEquals("", movie.getGenre());
        assertEquals(0, movie.getDuration());
    }
    
    @Test
    public void test22_Movie_ParameterizedConstructor() {
        Movie movie = new Movie("Inception", "Christopher Nolan", 2010, 148, "Science Fiction");
        assertEquals("Inception", movie.getTitle());
        assertEquals("Christopher Nolan", movie.getCreator());
        assertEquals(2010, movie.getYearReleased());
        assertEquals(148, movie.getDuration());
        assertEquals("Science Fiction", movie.getGenre());
    }
    
    @Test
    public void test23_Movie_Setters() {
        Movie movie = new Movie();
        movie.setGenre("Action");
        movie.setDuration(120);
        
        assertEquals("Action", movie.getGenre());
        assertEquals(120, movie.getDuration());
    }
    
    @Test
    public void test24_Movie_GetMediaType() {
        Movie movie = new Movie("Title", "Director", 2020, 120, "Drama");
        assertEquals("Movie", movie.getMediaType());
    }
    
    @Test
    public void test25_Movie_GetDisplayInfo() {
        Movie movie = new Movie("Inception", "Christopher Nolan", 2010, 148, "Science Fiction");
        String expected = "Movie: Inception directed by Christopher Nolan (2010) - 148 mins, Genre: Science Fiction";
        assertEquals(expected, movie.getDisplayInfo());
    }
    
    @Test
    public void test26_Movie_GetPopularityScore() {
        Movie movie = new Movie("Title", "Director", 2020, 150, "Drama");
        double expected = (2025 - 2020) * 0.8 + (150 / 10.0);
        assertEquals(expected, movie.getPopularityScore(), 0.01);
    }
    
    @Test
    public void test27_Movie_Polymorphism() {
        Media media = new Movie("Title", "Director", 2020, 120, "Drama");
        assertEquals("Movie", media.getMediaType()); // Dynamic binding
    }
    
    // ==================== MUSIC CLASS TESTS ====================
    
    @Test
    public void test28_Music_DefaultConstructor() {
        Music music = new Music();
        assertEquals("", music.getTitle());
        assertEquals("", music.getGenre());
        assertEquals(0, music.getDuration());
    }
    
    @Test
    public void test29_Music_ParameterizedConstructor() {
        Music music = new Music("Thriller", "Michael Jackson", 1982, 42, "Pop");
        assertEquals("Thriller", music.getTitle());
        assertEquals("Michael Jackson", music.getCreator());
        assertEquals(1982, music.getYearReleased());
        assertEquals(42, music.getDuration());
        assertEquals("Pop", music.getGenre());
    }
    
    @Test
    public void test30_Music_Setters() {
        Music music = new Music();
        music.setGenre("Rock");
        music.setDuration(45);
        
        assertEquals("Rock", music.getGenre());
        assertEquals(45, music.getDuration());
    }
    
    @Test
    public void test31_Music_GetMediaType() {
        Music music = new Music("Title", "Artist", 2020, 40, "Pop");
        assertEquals("Music", music.getMediaType());
    }
    
    @Test
    public void test32_Music_GetDisplayInfo() {
        Music music = new Music("Thriller", "Michael Jackson", 1982, 42, "Pop");
        String expected = "Music: Thriller by Michael Jackson (1982) - 42 mins, Genre: Pop";
        assertEquals(expected, music.getDisplayInfo());
    }
    
    @Test
    public void test33_Music_GetPopularityScore() {
        Music music = new Music("Title", "Artist", 2020, 40, "Pop");
        double expected = (2025 - 2020) * 0.6 + (40 / 5.0);
        assertEquals(expected, music.getPopularityScore(), 0.01);
    }
    
    @Test
    public void test34_Music_Polymorphism() {
        Media media = new Music("Title", "Artist", 2020, 40, "Pop");
        assertEquals("Music", media.getMediaType()); // Dynamic binding
    }
    
    // ==================== MEDIALIBRARY TESTS ====================
    
    @Test
    public void test35_MediaLibrary_Constructor() {
        MediaLibrary lib = new MediaLibrary();
        assertNotNull(lib);
        assertNotNull(lib.getMediaCollection());
        assertEquals(0, lib.getMediaCollection().size());
    }
    
    @Test
    public void test36_MediaLibrary_AddMedia() {
        Book book = new Book("Title", "Author", 2020, "ISBN", 200);
        library.addMedia(book);
        assertEquals(1, library.getMediaCollection().size());
    }
    
    @Test
    public void test37_MediaLibrary_AddMedia_NullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            library.addMedia(null);
        });
    }
    
    @Test
    public void test38_MediaLibrary_AddMultipleMedia() {
        library.addMedia(new Book("Book1", "Author1", 2020, "ISBN1", 200));
        library.addMedia(new Movie("Movie1", "Director1", 2021, 120, "Drama"));
        library.addMedia(new Music("Music1", "Artist1", 2022, 40, "Pop"));
        
        assertEquals(3, library.getMediaCollection().size());
    }
    
    @Test
    public void test39_MediaLibrary_LoadFromFile_Valid() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        assertEquals(3, library.getMediaCollection().size());
    }
    
    @Test
    public void test40_MediaLibrary_LoadFromFile_FileNotFound() {
        assertThrows(FileNotFoundException.class, () -> {
            library.loadMediaFromFile("nonexistent_file.txt");
        });
    }
    
    @Test
    public void test41_MediaLibrary_LoadFromFile_InvalidFormat() throws Exception {
        createInvalidFile();
        assertThrows(InvalidMediaFormatException.class, () -> {
            library.loadMediaFromFile(INVALID_FILE);
        });
    }
    
    @Test
    public void test42_MediaLibrary_FindMediaByTitle_Found() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        Media found = library.findMediaByTitle("1984");
        assertNotNull(found);
        assertEquals("1984", found.getTitle());
        assertEquals("Book", found.getMediaType());
    }
    
    @Test
    public void test43_MediaLibrary_FindMediaByTitle_NotFound() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        assertThrows(MediaNotFoundException.class, () -> {
            library.findMediaByTitle("Nonexistent Title");
        });
    }
    
    @Test
    public void test44_MediaLibrary_FindMediaByTitle_CaseInsensitive() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        Media found = library.findMediaByTitle("inception");
        assertNotNull(found);
        assertEquals("Inception", found.getTitle());
    }
    
    @Test
    public void test45_MediaLibrary_GetMediaByType_Books() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        ArrayList<Media> books = library.getMediaByType("Book");
        assertEquals(1, books.size());
        assertEquals("Book", books.get(0).getMediaType());
    }
    
    @Test
    public void test46_MediaLibrary_GetMediaByType_Movies() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        ArrayList<Media> movies = library.getMediaByType("Movie");
        assertEquals(1, movies.size());
        assertEquals("Movie", movies.get(0).getMediaType());
    }
    
    @Test
    public void test47_MediaLibrary_GetMediaByType_Music() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        ArrayList<Media> music = library.getMediaByType("Music");
        assertEquals(1, music.size());
        assertEquals("Music", music.get(0).getMediaType());
    }
    
    @Test
    public void test48_MediaLibrary_GetMediaByType_Empty() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        ArrayList<Media> result = library.getMediaByType("Podcast");
        assertEquals(0, result.size());
    }
    
    @Test
    public void test49_MediaLibrary_GetMostPopularMedia() throws Exception {
        library.addMedia(new Book("Old Book", "Author", 1900, "ISBN", 200));
        library.addMedia(new Book("New Book", "Author", 2024, "ISBN", 500));
        library.addMedia(new Movie("Movie", "Director", 2020, 180, "Drama"));
        
        Media popular = library.getMostPopularMedia();
        assertNotNull(popular);
        // The old book should have highest score due to age
    }
    
    @Test
    public void test50_MediaLibrary_GetMostPopularMedia_EmptyLibrary() {
        Media popular = library.getMostPopularMedia();
        assertNull(popular);
    }
    
    @Test
    public void test51_MediaLibrary_GetAllMediaSortedByYear() throws Exception {
        library.addMedia(new Book("Book3", "Author", 2020, "ISBN", 200));
        library.addMedia(new Book("Book1", "Author", 1950, "ISBN", 200));
        library.addMedia(new Book("Book2", "Author", 1980, "ISBN", 200));
        
        ArrayList<Media> sorted = library.getAllMediaSortedByYear();
        
        assertEquals(3, sorted.size());
        assertEquals(1950, sorted.get(0).getYearReleased());
        assertEquals(1980, sorted.get(1).getYearReleased());
        assertEquals(2020, sorted.get(2).getYearReleased());
    }
    
    @Test
    public void test52_MediaLibrary_GetAllMediaSortedByYear_OriginalUnchanged() throws Exception {
        library.addMedia(new Book("Book2", "Author", 2020, "ISBN", 200));
        library.addMedia(new Book("Book1", "Author", 1950, "ISBN", 200));
        
        ArrayList<Media> sorted = library.getAllMediaSortedByYear();
        
        // Original should still have 2020 first
        assertEquals(2020, library.getMediaCollection().get(0).getYearReleased());
        // Sorted should have 1950 first
        assertEquals(1950, sorted.get(0).getYearReleased());
    }
    
    @Test
    public void test53_MediaLibrary_GetMediaStatistics() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        String stats = library.getMediaStatistics();
        
        assertNotNull(stats);
        assertTrue(stats.contains("Total items: 3"));
        assertTrue(stats.contains("Books: 1"));
        assertTrue(stats.contains("Movies: 1"));
        assertTrue(stats.contains("Music albums: 1"));
    }
    
    @Test
    public void test54_MediaLibrary_GetMediaStatistics_EmptyLibrary() {
        String stats = library.getMediaStatistics();
        assertEquals("No media in collection", stats);
    }
    
    @Test
    public void test55_MediaLibrary_DisplayAllMedia() throws Exception {
        createTestFile();
        library.loadMediaFromFile(TEST_FILE);
        
        // This method prints to console, just verify no exception
        assertDoesNotThrow(() -> library.displayAllMedia());
    }
    
    // ==================== POLYMORPHISM TESTS ====================
    
    @Test
    public void test56_Polymorphism_MediaCollectionHoldsDifferentTypes() {
        ArrayList<Media> collection = new ArrayList<>();
        collection.add(new Book("Book", "Author", 2020, "ISBN", 200));
        collection.add(new Movie("Movie", "Director", 2020, 120, "Drama"));
        collection.add(new Music("Music", "Artist", 2020, 40, "Pop"));
        
        assertEquals(3, collection.size());
        assertEquals("Book", collection.get(0).getMediaType());
        assertEquals("Movie", collection.get(1).getMediaType());
        assertEquals("Music", collection.get(2).getMediaType());
    }
    
    @Test
    public void test57_Polymorphism_DynamicBinding() {
        Media[] mediaArray = new Media[3];
        mediaArray[0] = new Book("Book", "Author", 2020, "ISBN", 200);
        mediaArray[1] = new Movie("Movie", "Director", 2020, 120, "Drama");
        mediaArray[2] = new Music("Music", "Artist", 2020, 40, "Pop");
        
        // Dynamic binding - calls appropriate subclass method
        assertEquals("Book", mediaArray[0].getMediaType());
        assertEquals("Movie", mediaArray[1].getMediaType());
        assertEquals("Music", mediaArray[2].getMediaType());
    }
    
    @Test
    public void test58_Polymorphism_DifferentPopularityCalculations() {
        Media book = new Book("Title", "Author", 2020, "ISBN", 300);
        Media movie = new Movie("Title", "Director", 2020, 150, "Drama");
        Media music = new Music("Title", "Artist", 2020, 40, "Pop");
        
        // Each type calculates popularity differently
        double bookScore = book.getPopularityScore();
        double movieScore = movie.getPopularityScore();
        double musicScore = music.getPopularityScore();
        
        assertNotEquals(bookScore, movieScore);
        assertNotEquals(movieScore, musicScore);
        assertNotEquals(bookScore, musicScore);
    }
    
    @Test
    public void test59_Integration_CompleteWorkflow() throws Exception {
        // Create file
        createTestFile();
        
        // Load from file
        library.loadMediaFromFile(TEST_FILE);
        assertEquals(3, library.getMediaCollection().size());
        
        // Add more media
        library.addMedia(new Book("New Book", "Author", 2023, "ISBN", 250));
        assertEquals(4, library.getMediaCollection().size());
        
        // Find media
        Media found = library.findMediaByTitle("1984");
        assertNotNull(found);
        
        // Get by type
        ArrayList<Media> books = library.getMediaByType("Book");
        assertEquals(2, books.size());
        
        // Get most popular
        Media popular = library.getMostPopularMedia();
        assertNotNull(popular);
        
        // Get sorted
        ArrayList<Media> sorted = library.getAllMediaSortedByYear();
        assertEquals(4, sorted.size());
        
        // Get statistics
        String stats = library.getMediaStatistics();
        assertTrue(stats.contains("Total items: 4"));
    }
    
    @Test
    public void test60_EdgeCase_LoadFileWithEmptyLines() throws Exception {
        // Create file with empty lines
        PrintWriter writer = new PrintWriter(new File(TEST_FILE));
        writer.println("BOOK|1984|George Orwell|1949|978-0451524935|328");
        writer.println("");
        writer.println("MOVIE|Inception|Christopher Nolan|2010|148|Science Fiction");
        writer.println("   ");
        writer.println("MUSIC|Thriller|Michael Jackson|1982|42|Pop");
        writer.close();
        
        library.loadMediaFromFile(TEST_FILE);
        assertEquals(3, library.getMediaCollection().size());
    }
    
    // ==================== Utility Methods ====================
    
    private void createTestFile() throws Exception {
        PrintWriter writer = new PrintWriter(new File(TEST_FILE));
        writer.println("BOOK|1984|George Orwell|1949|978-0451524935|328");
        writer.println("MOVIE|Inception|Christopher Nolan|2010|148|Science Fiction");
        writer.println("MUSIC|Thriller|Michael Jackson|1982|42|Pop");
        writer.close();
    }
    
    private void createInvalidFile() throws Exception {
        PrintWriter writer = new PrintWriter(new File(INVALID_FILE));
        writer.println("BOOK|1984|George Orwell");  // Missing fields
        writer.println("INVALID|Title|Creator|2020|Field|Extra");  // Invalid type
        writer.println("MOVIE|Title|Director|NotANumber|120|Drama");  // Invalid number
        writer.close();
    }
}