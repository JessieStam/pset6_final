package jessie_stam.jessiestam_pset6_desktop.Datafetchers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import jessie_stam.jessiestam_pset6_desktop.Activities.BooksFoundActivity;
import jessie_stam.jessiestam_pset6_desktop.Helpers.BookManager;
import jessie_stam.jessiestam_pset6_desktop.Items.BookItem;

/**
 * TBR Jar - BooksAsyncTask
 *
 * Jessie Stam
 * 10560599
 *
 * Takes user input (from BooksFoundActivity) and uses the GoodReads Api to fetch data for that
 * specific book/author/isbn number. Calls BooksFoundActivity setData function to display data.
 */
public class BookAsyncTask extends AsyncTask<String, Integer, ArrayList<BookItem>> {

    private Context context;
    private BooksFoundActivity booksfoundactivity;
    private BookManager manager;

    /**
     * Constructs BookAsyncTask
     */
    public BookAsyncTask(BooksFoundActivity activity) {
        booksfoundactivity = activity;
        context = this.booksfoundactivity.getApplicationContext();
        manager = BookManager.getOurInstance();
    }

    // let user know data is being downloaded
    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected ArrayList<BookItem> doInBackground(String... params) {

        ArrayList<BookItem> bookitem_list = null;

        // make string and replace spaces for pluses
        String search_url =
                "https://www.goodreads.com/search/index.xml?key=ZZh7kngcTINCg4tjAG0GDQ&q=";
        String user_input = params[0];
        String book_search = user_input.replaceAll(" ", "+");

        // complete URL string and turn into URL
        String complete_URL_string = search_url + book_search;
        URL complete_URL;
        HttpURLConnection connection;

        try {
            // try to make connection
            complete_URL = new URL(complete_URL_string);

            connection = (HttpURLConnection) complete_URL.openConnection();
            connection.setRequestMethod("GET");
            InputStream input_stream = connection.getInputStream();

            // if inputstream exists, process xml file and save in bookitem_list
            if (input_stream != null) {
                bookitem_list = processXML(input_stream);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookitem_list;
    }

    // if datalist is filled, setData in BooksFoundActivity
    @Override
    protected void onPostExecute(ArrayList<BookItem> result) {
        super.onPostExecute(result);

        if (result.size() != 0) {
            Toast.makeText(context, "Data was found!", Toast.LENGTH_SHORT).show();
            this.booksfoundactivity.setData(result);
        }
        else {
            Toast.makeText(context, "No data was found...", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Process xml file and add data to BookItem
     */
    public ArrayList<BookItem> processXML(InputStream input_stream) throws Exception {

        ArrayList<BookItem> book_list = new ArrayList<>();

        // construct parser and factory, add inputstream
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

            switch (event_type) {
                case XmlPullParser.START_TAG:

                    // if tag is work, create BookItem in advance
                    if (tag_name.equalsIgnoreCase("work")) {
                        book_item = new BookItem();
                    }
                    break;

                case XmlPullParser.TEXT:

                    // when text is found, save it temporarily to maybe put in the BookItem
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:

                    // if tag is title, add title to BookItem object
                    if (tag_name.equalsIgnoreCase("title")) {
                        if (text != null) {
                            book_item.setTitle(text);
                        }
                    }
                    // if tag is name, add author to BookItem object
                    else if (tag_name.equalsIgnoreCase("name")) {

                        if (text != null) {
                            book_item.setAuthor(text);
                        }
                    }
                    // if tag is small_image_url, add image to BookItem object
                    else if (tag_name.equalsIgnoreCase("small_image_url")) {

                        if (text != null) {
                            book_item.setImage(text);
                        }
                    }
                    // if tag is original_publication_year, add year to BookItem object
                    else if (tag_name.equalsIgnoreCase("original_publication_year")) {

                        if (text != null) {
                            book_item.setYear(text);
                        }
                    }
                    // if tag is average_rating, add rating to BookItem object
                    else if (tag_name.equalsIgnoreCase("average_rating")) {

                        if (text != null) {
                            book_item.setRating(text);
                        }
                    }
                    // if tag is work, add BookItem to list of BookItems
                    else if (tag_name.equalsIgnoreCase("work")) {

                        book_item.setSummary("No summary yet.");

                        manager.addBookToDisplayList(book_item);
                        book_list.add(book_item);
                    }
                    break;
                default:
                    break;
            }
            event_type = parser.next();
        }
        return book_list;
    }
}
