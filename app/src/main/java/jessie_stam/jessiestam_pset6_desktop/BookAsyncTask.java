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

public class BookAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    BooksFoundActivity booksfoundactivity;

    ArrayList<BookItem> book_list = new ArrayList<>();
    //ArrayList<String> data_list;

    /**
     * Constructs TitleAsyncTask
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
    protected String doInBackground(String... params) {
        //return HttpRequestHelper.downloadFromServer(params);

        //InputStream input_stream = null;

        Log.d("test", "do we even get here?");

        String search_url =
                "https://www.goodreads.com/search/index.xml?key=ZZh7kngcTINCg4tjAG0GDQ&q=";
        String user_input = params[0];

        Log.d("test", "user input is: " + user_input);
        String book_search = user_input.replaceAll(" ", "+");

        // complete URL string and turn into URL
        String complete_URL_string = search_url + book_search;
        URL complete_URL = null;
        HttpURLConnection connection;

        try {
            complete_URL = new URL(complete_URL_string);

            connection = (HttpURLConnection) complete_URL.openConnection();
            connection.setRequestMethod("GET");
            InputStream input_stream = connection.getInputStream();

            if (input_stream != null) {
                Toast.makeText(context, "Data was found", Toast.LENGTH_SHORT).show();
                processXML(input_stream);
            }
            else {
                Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
            }

            processXML(input_stream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void processXML(InputStream input_stream) throws Exception {

        Log.d("test", "we get in the process loop");

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

            Log.d("test", "we get in the parse loop");

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

                Log.d("test", "title: " + title);
                Log.d("test", "author: " + author);
                Log.d("test", "image: " + image);
                Log.d("test", "year: " + year);
                Log.d("test", "rating: " + rating);
            }
        }
        else {
            Log.d("test", "book list is empty");
        }

        this.booksfoundactivity.setData(book_list);

    }

    // read the data and put in list
//    @Override
//    protected void onPostExecute(String readBookInfo) {
//        super.onPostExecute(readBookInfo);
//
//        // if nothing was found, inform user
//        if (book_list.size() == 0) {
//            Toast.makeText(context, "...but no books were found for your input", Toast.LENGTH_SHORT).show();
//        } else{
//            // start booksFoundActivity setData function to display the data
//            this.booksfoundactivity.setData(book_list);
//        }
//
//
////        if (readFilmInfo.length() == 0) {
////            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
////        } else {
////            Toast.makeText(context, "Data was found", Toast.LENGTH_SHORT).show();
////
////            // create new datalist
////            data_list = new ArrayList<>();
////
////            try {
////                // create new JSONObject
////                JSONObject response_object = new JSONObject(readFilmInfo);
////                String title_object = response_object.getString("Title");
////                String year_object = response_object.getString("Year");
////                String director_object = response_object.getString("Director");
////                String actors_object = response_object.getString("Actors");
////                String summary_object = response_object.getString("Plot");
////                String poster_object = response_object.getString("Poster");
////
////                // add to list as String
////                data_list.add(title_object);
////                data_list.add(year_object);
////                data_list.add(director_object);
////                data_list.add(actors_object);
////                data_list.add(summary_object);
////                data_list.add(poster_object);
////
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////            // if film is not found, let user know
////            if (data_list.size() == 0) {
////                Toast.makeText(context, "but your film was not...", Toast.LENGTH_SHORT).show();
////            }
////
////            // start Second Activity's setData function to display the data
////            this.secondActivity.setData(data_list);
////        }
//    }
}
