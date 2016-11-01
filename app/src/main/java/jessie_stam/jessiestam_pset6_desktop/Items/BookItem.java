package jessie_stam.jessiestam_pset6_desktop.Items;

/**
 * TBR Jar - BookItem
 *
 * Jessie Stam
 * 10560599
 *
 * BookItem class that stores titles, authors, images, years, ratings and summaries.
 */
public class BookItem {

    // fields for title, author and image
    private String title;
    private String author;
    private String image;
    private String year;
    private String rating;
    private String summary;

    // methods for title
    public String getTitle() { return title; }
    public void setTitle(String new_title) { title = new_title; }

    // methods for author
    public String getAuthor() { return author; }
    public void setAuthor(String new_author) { author = new_author; }

    // methods for image
    public String getImage() { return image; }
    public void setImage(String new_image) { image = new_image; }

    // methods for year
    public String getYear() { return year; }
    public void setYear(String new_year) { year = new_year; }

    // methods for rating
    public String getRating() { return rating; }
    public void setRating(String new_rating) { rating = new_rating; }

    public String getSummary() { return summary; }
    public void setSummary(String new_summary) { summary = new_summary; }

}
