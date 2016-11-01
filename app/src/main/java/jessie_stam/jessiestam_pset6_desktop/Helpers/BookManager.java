package jessie_stam.jessiestam_pset6_desktop.Helpers;

import java.util.ArrayList;

import jessie_stam.jessiestam_pset6_desktop.Items.BookItem;
import jessie_stam.jessiestam_pset6_desktop.Items.User;

/**
 * TBR Jar - BooksManager
 *
 * Jessie Stam
 * 10560599
 *
 * Singleton that manages BookItems and Lists.
 */
public class BookManager {

    // define instance
    private static BookManager ourInstance = null;

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<BookItem> tbr_jar = new ArrayList<>();
    private ArrayList<BookItem> favorites = new ArrayList<>();
    private ArrayList<BookItem> nowreading = new ArrayList<>();
    private ArrayList<BookItem> finished = new ArrayList<>();
    private ArrayList<BookItem> display_list = new ArrayList<>();

    private String current_user = "";

    // construct the instance
    public static BookManager getOurInstance(){

        if (ourInstance == null) {
            ourInstance = new BookManager();
        }
        return ourInstance;
    }

    /**
     * Create User and add User to users list.
     */
    public void create_user(String user_email) {

        int i = 0;
        current_user = user_email;

        // check if user is already in list
        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                i = 1;
            }
        }

        // if not already in list, add new user to list
        if (i != 1) {

            User user = new User();
            user.setEmail(user_email);

            user.setTBR(tbr_jar);
            user.setFavorites(favorites);
            user.setReading(nowreading);
            user.setFinished(finished);

            users.add(user);
        }
    }

    /**
     * Returns the current user.
     */
    public String getCurrent_user() { return current_user; }

    /**
     * Logs the users out.
     */
    public void logout_user() {
        current_user = "";
    }

    /**
     * Adds BookItem to the tbr list.
     */
    public void add_to_tbr(BookItem new_book) {

        String user_email = getCurrent_user();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                ArrayList<BookItem> tbr = user.getTBR();
                tbr.add(new_book);
                user.setTBR(tbr);
            }
        }
    }

    /**
     * Returns the tbr list of current user.
     */
    public ArrayList<BookItem> getTbr_jar() {

        String user_email = getCurrent_user();
        ArrayList<BookItem> tbr = new ArrayList<>();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                tbr = user.getTBR();
            }
        }
        return tbr;
    }

    /**
     * Adds BookItem to the favorites list of current user.
     */
    public void add_to_favorites(BookItem new_book) {

        String user_email = getCurrent_user();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                ArrayList<BookItem> favorties = user.getFavorites();
                favorties.add(new_book);
                user.setTBR(favorties);
            }
        }
    }

    /**
     * Returns the favorite list for current user.
     */
    public ArrayList<BookItem> getFavorites() {

        String user_email = getCurrent_user();
        ArrayList<BookItem> favorites = new ArrayList<>();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                favorites = user.getFavorites();
            }
        }
        return favorites;
    }

    /**
     * Adds BookItem to currently reading list of current user.
     */
    public void add_to_reading(BookItem new_book) {

        String user_email = getCurrent_user();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                ArrayList<BookItem> reading = user.getReading();
                reading.add(new_book);
                user.setTBR(reading);
            }
        }
    }

    /**
     * Returns the currenly reading list for current user.
     */
    public ArrayList<BookItem> getNowreading() {

        String user_email = getCurrent_user();
        ArrayList<BookItem> now_reading = new ArrayList<>();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                now_reading = user.getReading();
            }
        }
        return now_reading;
    }

    /**
     * Adds BookItem to finished list of current user.
     */
    public void add_to_finished(BookItem new_book) {

        String user_email = getCurrent_user();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                ArrayList<BookItem> finished = user.getFinished();
                finished.add(new_book);
                user.setTBR(finished);
            }
        }
    }

    /**
     * Returns the finished list of current user.
     */
    public ArrayList<BookItem> getFinished() {

        String user_email = getCurrent_user();
        ArrayList<BookItem> finished = new ArrayList<>();

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                finished = user.getFinished();
            }
        }
        return finished;
    }

    /**
     * Create BookItem.
     */
    public BookItem create_book (String title_string, String author_string, String image_string,
                                 String year_string, String rating_string) {

        BookItem book_item = new BookItem();

        book_item.setTitle(title_string);
        book_item.setAuthor(author_string);
        book_item.setImage(image_string);
        book_item.setYear(year_string);
        book_item.setRating(rating_string);
        book_item.setSummary("No summary yet.");

        // add BookItem to display list
        addBookToDisplayList(book_item);

        return book_item;
    }

    /**
     * Add BookItem to displaylist.
     */
    public void addBookToDisplayList (BookItem book_item) { display_list.add(book_item); }

    /**
     * Clears the displayList.
     */
    public void clearDisplayList() { display_list.clear(); }

    /**
     * Returns the BookItem for a given title String.
     */
    public BookItem getItemByTitle(String title) {

        for (BookItem item : display_list) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }
}
