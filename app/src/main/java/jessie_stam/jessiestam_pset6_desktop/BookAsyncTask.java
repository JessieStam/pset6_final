package jessie_stam.jessiestam_pset6_desktop;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Jessie on 24-10-2016.
 */

public class BookAsyncTask extends AsyncTask<String, Integer, ArrayList<BookItem>> {

    private Context context;
    private BooksFoundActivity booksfoundactivity;

    /**
     * Constructs BookAsyncTask
     */
    public BookAsyncTask(BooksFoundActivity activity) {
        booksfoundactivity = activity;
        context = this.booksfoundactivity.getApplicationContext();
    }

    // let user know data is being downloaded
    @Override
    protected void onPreExecute() {
        // inform user
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected ArrayList<BookItem> doInBackground(String... params) {

        ArrayList<BookItem> bookitem_list = null;

        String search_url =
                "https://www.goodreads.com/search/index.xml?key=ZZh7kngcTINCg4tjAG0GDQ&q=";
        String user_input = params[0];
        String book_search = user_input.replaceAll(" ", "+");

        // complete URL string and turn into URL
        String complete_URL_string = search_url + book_search;
        URL complete_URL;
        HttpURLConnection connection;

        try {
            complete_URL = new URL(complete_URL_string);

            connection = (HttpURLConnection) complete_URL.openConnection();
            connection.setRequestMethod("GET");
            InputStream input_stream = connection.getInputStream();

            if (input_stream != null) {
                bookitem_list = processXML(input_stream);
                Log.d("test", "data found...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookitem_list;
    }

    // read the data and put in list
    @Override
    protected void onPostExecute(ArrayList<BookItem> result) {
        super.onPostExecute(result);

        Log.d("test", "on post execute");

        if (result.size() != 0) {
            Toast.makeText(context, "Data was found!", Toast.LENGTH_SHORT).show();
            Log.d("test", "result list is not empty");
            this.booksfoundactivity.setData(result);
        }
        else {
            Toast.makeText(context, "No data was found...", Toast.LENGTH_SHORT).show();
            Log.d("test", "result list is empty");
        }
    }

    public ArrayList<BookItem> processXML(InputStream input_stream) throws Exception {

        Log.d("test", "we get in the process loop");

        ArrayList<BookItem> book_list = new ArrayList<>();

        XmlPullParserFactory factory;
        XmlPullParser parser;

        factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);

        parser = factory.newPullParser();
        parser.setInput(input_stream, null);

        BookItem book_item = null;
        String text = "";

        int event_type = parser.getEventType();
        while (event_type != XmlPullParser.END_DOCUMENT) {

            String tag_name = parser.getName();

            if (tag_name != null) {
                Log.d("test", "tag name is: " + tag_name);
            }
            else {
                Log.d("test", "tag name is null");
            }

            switch (event_type) {
                case XmlPullParser.START_TAG:

                    Log.d("test", "case is start tag");

                    if (tag_name.equalsIgnoreCase("work")) {
                        book_item = new BookItem();
                    }
                    break;

                case XmlPullParser.TEXT:

                    Log.d("test", "case is text");

                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:

                    Log.d("test", "case is end tag");

                    if (tag_name.equalsIgnoreCase("title")) {

                        Log.d("test", "tag equals title");

                        if (text != null) {
                            book_item.setTitle(text);
                        }
                        else{
                            Log.d("test", "Text is null");
                        }
                    }
                    else if (tag_name.equalsIgnoreCase("name")) {

                        Log.d("test", "tag equals name");

                        if (text != null) {
                            book_item.setAuthor(text);
                        }
                        else{
                            Log.d("test", "Text is null");
                        }
                    }
                    else if (tag_name.equalsIgnoreCase("small_image_url")) {

                        Log.d("test", "tag equals small_image_url");

                        if (text != null) {
                            book_item.setImage(text);
                        }
                        else{
                            Log.d("test", "Text is null");
                        }
                    }
                    else if (tag_name.equalsIgnoreCase("original_publication_year")) {

                        Log.d("test", "tag equals original publication year");

                        if (text != null) {
                            book_item.setYear(text);
                        }
                        else{
                            Log.d("test", "Text is null");
                        }
                    }
                    else if (tag_name.equalsIgnoreCase("average_rating")) {

                        Log.d("test", "tag equals average rating");

                        if (text != null) {
                            book_item.setRating(text);
                        }
                        else{
                            Log.d("test", "Text is null");
                        }
                    }
                    else if (tag_name.equalsIgnoreCase("work")) {

                        Log.d("test", "tag equals work");

                        book_list.add(book_item);
                    }
                    break;
                default:
                    break;
            }
            event_type = parser.next();

        }

        if (book_list.size() != 0) {
            Log.d("test", "book list is not empty");

            for (BookItem item : book_list) {
                String title = item.getTitle();
                String author = item.getAuthor();
                String image = item.getImage();
                String year = item.getYear();
                String rating = item.getRating();

//                Log.d("test", "title: " + title);
//                Log.d("test", "author: " + author);
//                Log.d("test", "image: " + image);
//                Log.d("test", "year: " + year);
//                Log.d("test", "rating: " + rating);
            }
        }
        else {
            Log.d("test", "book list is empty");
        }

        //this.booksfoundactivity.setData(book_list);
        return book_list;
    }
}
