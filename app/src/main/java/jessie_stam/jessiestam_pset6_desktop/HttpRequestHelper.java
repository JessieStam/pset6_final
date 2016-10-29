//package jessie_stam.jessiestam_pset6_desktop;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * Created by Jessie on 24-10-2016.
// */
//
//public class HttpRequestHelper {
//
//    // strings for URL
//    private static final String url1 =
//            "https://www.goodreads.com/search/index.xml?key=ZZh7kngcTINCg4tjAG0GDQ&q=";
//
//    /**
//     * Downloads information from the server and puts it into a string object.
//     */
//    protected static synchronized String downloadFromServer(String... params) {
//
//        //Document xml_doc = null;
//        String input_string = null;
//
//        // create result String and film title String
//        //String result = "";
//        String book_input = params[0];
//
//        // replace spaces with + sign to make URL work
//        String book_search = book_input.replaceAll(" ", "+");
//
//        // complete URL string and turn into URL
//        String complete_URL_string = url1 + book_search;
//        URL complete_URL = null;
//
//        try {
//            complete_URL = new URL(complete_URL_string);
//
//            HttpURLConnection connection =
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//
//        // make internet connection
//        HttpURLConnection connection;
//        if (complete_URL != null) {
//            try {
//                connection = (HttpURLConnection) complete_URL.openConnection();
//
//                // open connection, set request method
//                connection.setRequestMethod("GET");
//
//                // get response code
//                Integer responce_code = connection.getResponseCode();
//
//                // if 200-300, read input stream
//                if (200 <= responce_code && responce_code <= 299) {
//
//                    InputStream input_stream = connection.getInputStream();
//                    input_string = input_stream.toString();
////                    DocumentBuilderFactory builder_factory = DocumentBuilderFactory.newInstance();
////                    DocumentBuilder builder = builder_factory.newDocumentBuilder();
////                    xml_doc = builder.parse(input_string);
//
////                    BufferedReader bufferedReader = new BufferedReader
////                            (new InputStreamReader(connection.getInputStream()));
////                    String line;
////                    while ((line = bufferedReader.readLine()) != null) {
////                        result = result + line;
////                    }
//                }
//                // when error occurs, read error stream
//                else {
//                    BufferedReader bufferedReader = new BufferedReader
//                            (new InputStreamReader(connection.getErrorStream()));
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // return result string
//        return input_string;
//    }
//}
