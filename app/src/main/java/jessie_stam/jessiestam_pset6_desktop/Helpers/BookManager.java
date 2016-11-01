package jessie_stam.jessiestam_pset6_desktop.Helpers;

import android.util.Log;

import java.util.ArrayList;

import jessie_stam.jessiestam_pset6_desktop.Items.BookItem;
import jessie_stam.jessiestam_pset6_desktop.Items.User;

/**
 * Created by Jessie on 1-11-2016.
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
     * Create TodoItem for list
     */
    public void create_user(String user_email) {

        int i = 0;
        current_user = user_email;

        for (User user : users) {
            if (user_email.equals(user.getEmail())) {
                i = 1;
                Log.d("test", "current user in find_user: " + current_user);
            }
        }

        if (i != 1) {
            Log.d("test", "current user in create_user: " + current_user);

            User user = new User();
            user.setEmail(user_email);

            user.setTBR(tbr_jar);
            user.setFavorites(favorites);
            user.setReading(nowreading);
            user.setFinished(finished);

            users.add(user);
        }
    }

    public String getCurrent_user() { return current_user; }

    public void logout_user() {
        current_user = "";
    }

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
     * Create TodoItem for list
     */
    public BookItem create_book (String title_string, String author_string, String image_string,
                                 String year_string, String rating_string) {

        // create new item and set id, title and status
        BookItem book_item = new BookItem();

        book_item.setTitle(title_string);
        book_item.setAuthor(author_string);
        book_item.setImage(image_string);
        book_item.setYear(year_string);
        book_item.setRating(rating_string);
        book_item.setSummary("No summary yet.");

        addBookToDisplayList(book_item);

        return book_item;
    }

    public void addBookToDisplayList (BookItem book_item) {

        display_list.add(book_item);
        Log.d("test", "addtodisplaylist gebruikt");

        if (display_list.size() == 0) {
            Log.d("test", "display list is empty na gebruik");
        }
        else {
            Log.d("test", "display list size na gebruik: " + display_list.size());
        }
    }

   // public ArrayList<BookItem> getDisplayList() { return display_list; }

    public void clearDisplayList() { display_list.clear(); }

    public BookItem getItemByTitle(String title) {

        if (display_list.size() == 0) {
            Log.d("test", "display list is empty");
        }

        for (BookItem item : display_list) {
            if (item.getTitle().equals(title)) {
                Log.d("test", "display list item is " + item.getTitle());

                return item;
            }
        }
        return null;
    }

}
