package jessie_stam.jessiestam_pset6_desktop;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jessie on 24-10-2016.
 */

public class BooksFoundActivity extends FindBooksActivity {

    EditText user_search_input;
    String user_input;

    private Toolbar toolbar;
    MenuHelper menu_helper;

    RecyclerView books_found_list;
    LinearLayoutManager manager;
    BooksAdapter adapter;

//    String title;
//    String author;
    String searched_book;
    ArrayList<String> title_list;
    ArrayList<String> author_list;
    ArrayList<String> image_list;

    //ArrayList<BookItem> bookitem_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booksfound);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();

        books_found_list = (RecyclerView) findViewById(R.id.booksfound_list);

        //bookitem_list = new ArrayList<>();

        title_list = new ArrayList<>();
        author_list = new ArrayList<>();
        image_list = new ArrayList<>();

//        title_list.add("testbook title");
//        author_list.add("testbook author");
//        image_list.add("https://images.gr-assets.com/books/1454428541s/9118135.jpg");

//        if (savedInstanceState == null) {
//            // create new list
//            bookitem_list = new ArrayList<>();
//        }
//        // if savedInstanceState is not empty, restore lists
//        else {
//            // restore titles and posters lists
//            // bookitem_list = savedInstanceState.getArrayList("booklist");
//        }

        // get extras from MainActivity
        Bundle extras = getIntent().getExtras();

        // if extras exist, update title and poster string and titles and posters lists
        if (extras != null) {
            searched_book = extras.getString("searched_book");

            Log.d("test", "user input in booksfound is: " + searched_book);
        }

        // use a linear layout manager on RecyclerView
        manager = new LinearLayoutManager(this);
        books_found_list.setLayoutManager(manager);

        // create new BooksAdapter object and set to RecyclerView
        adapter = new BooksAdapter(title_list, author_list, image_list);
        books_found_list.setAdapter(adapter);

        BookAsyncTask asyncTask = new BookAsyncTask(this);
        asyncTask.execute(searched_book);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu_item) {

        String clicked_item = menu_helper.getClickedMenuItem(menu_item, this);
        Toast.makeText(this, clicked_item, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(menu_item);
    }

    public void setData(ArrayList<BookItem> book_list) {

        Log.d("test", "setData, iterate over book_list");

        //bookitem_list = book_list;

        title_list.clear();
        author_list.clear();
        image_list.clear();

        if (book_list.size() != 0) {
            for (BookItem item : book_list){
                title_list.add(item.getTitle());
                author_list.add(item.getAuthor());
                image_list.add(item.getImage());

                Log.d("test", "title: " + item.getTitle());
                Log.d("test", "author: " + item.getAuthor());
                Log.d("test", "image: " + item.getImage());
            }
        }
        else {
            Log.d("test", "book_list is empty");
        }

        adapter.notifyDataSetChanged();
    }
}
