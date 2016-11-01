package jessie_stam.jessiestam_pset6_desktop.Items;

import java.util.ArrayList;

/**
 * Created by Jessie on 1-11-2016.
 */

public class User {

    // fields for title, author and image
    private String email;

    // private ArrayList<ArrayList<BookItem>> my_lists;
    private ArrayList<BookItem> tbr_jar;
    private ArrayList<BookItem> favorites;
    private ArrayList<BookItem> reading;
    private ArrayList<BookItem> finished;

    // constructor
    //public BookItem(String new_string) { title = new_string; }

    // methods for e-mail
    public String getEmail() { return email; }
    public void setEmail(String new_email) { email = new_email; }

    // methods for tbr list
    public ArrayList<BookItem> getTBR() { return tbr_jar; }
    public void setTBR(ArrayList<BookItem> new_tbr) { tbr_jar = new_tbr; }

    public ArrayList<BookItem> getFavorites() { return favorites; }
    public void setFavorites(ArrayList<BookItem> new_favorites) { favorites = new_favorites; }

    public ArrayList<BookItem> getReading() { return reading; }
    public void setReading(ArrayList<BookItem> new_reading) { reading = new_reading; }

    public ArrayList<BookItem> getFinished() { return finished; }
    public void setFinished(ArrayList<BookItem> new_finished) { finished = new_finished; }

}
