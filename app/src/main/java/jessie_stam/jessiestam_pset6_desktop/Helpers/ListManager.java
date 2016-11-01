//package jessie_stam.jessiestam_pset6_desktop.Helpers;
//
//import java.util.ArrayList;
//
//import jessie_stam.jessiestam_pset6_desktop.Items.BookItem;
//import jessie_stam.jessiestam_pset6_desktop.Items.User;
//
///**
// * Created by Jessie on 1-11-2016.
// */
//
//public class ListManager {
//
//    // define instance
//    private static BookManager ourInstance = null;
//
//    // list of users
//    private ArrayList<User> users = new ArrayList<>();
//
//    private ArrayList<BookItem> tbr_jar = new ArrayList<>();
//    private ArrayList<BookItem> favorites = new ArrayList<>();
//    private ArrayList<BookItem> nowreading = new ArrayList<>();
//    private ArrayList<BookItem> finished = new ArrayList<>();
//
//    // construct the instance
//    public static BookManager getOurInstance() {
//
//        if (ourInstance == null) {
//            ourInstance = new BookManager();
//        }
//        return ourInstance;
//    }
//
//    /**
//     * Create TodoItem for list
//     */
//    public void create_user(String user_email) {
//
//        User user = new User();
//        user.setEmail(user_email);
//
//        user.setTBR(tbr_jar);
//        user.setFavorites(favorites);
//        user.setReading(nowreading);
//        user.setFinished(finished);
//
//        users.add(user);
//
//    }
//
//    public void add_to_tbr(String user_email, BookItem new_book) {
//
//        for (User user : users) {
//            if (user_email.equals(user.getEmail())) {
//                ArrayList<BookItem> tbr = user.getTBR();
//                tbr.add(new_book);
//                user.setTBR(tbr);
//            }
//        }
//    }
//
//    public void add_to_favorites(String user_email, BookItem new_book) {
//
//        for (User user : users) {
//            if (user_email.equals(user.getEmail())) {
//                ArrayList<BookItem> favorties = user.getFavorites();
//                favorties.add(new_book);
//                user.setTBR(favorties);
//            }
//        }
//    }
//
//    public void add_to_reading(String user_email, BookItem new_book) {
//
//        for (User user : users) {
//            if (user_email.equals(user.getEmail())) {
//                ArrayList<BookItem> reading = user.getReading();
//                reading.add(new_book);
//                user.setTBR(reading);
//            }
//        }
//    }
//
//    public void add_to_finished(String user_email, BookItem new_book) {
//
//        for (User user : users) {
//            if (user_email.equals(user.getEmail())) {
//                ArrayList<BookItem> finished = user.getFinished();
//                finished.add(new_book);
//                user.setTBR(finished);
//            }
//        }
//    }
//
//
//}
