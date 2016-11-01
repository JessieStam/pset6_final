package jessie_stam.jessiestam_pset6_desktop.Lists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import jessie_stam.jessiestam_pset6_desktop.Adapters.BooksAdapter;
import jessie_stam.jessiestam_pset6_desktop.Helpers.BookManager;
import jessie_stam.jessiestam_pset6_desktop.Helpers.MenuHelper;
import jessie_stam.jessiestam_pset6_desktop.Items.BookItem;
import jessie_stam.jessiestam_pset6_desktop.R;

/**
 * Created by Jessie on 1-11-2016.
 */

public class FinishedList extends AppCompatActivity{

    private Toolbar toolbar;
    MenuHelper menu_helper;

    RecyclerView finished_recycler;
    LinearLayoutManager manager;
    BooksAdapter adapter;

    ArrayList<String> title_list;
    ArrayList<String> author_list;
    ArrayList<String> image_list;

    ArrayList<BookItem> finished_list;
    BookManager book_manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();

        book_manager = BookManager.getOurInstance();

        finished_recycler = (RecyclerView) findViewById(R.id.booksfound_list);
        finished_list = new ArrayList<>();

        if (savedInstanceState == null) {
            title_list = new ArrayList<>();
            author_list = new ArrayList<>();
            image_list = new ArrayList<>();
        }
        else {
            title_list = savedInstanceState.getStringArrayList("title_list");
            author_list = savedInstanceState.getStringArrayList("author_list");
            image_list = savedInstanceState.getStringArrayList("image_list");
        }

        // use a linear layout manager on RecyclerView
        manager = new LinearLayoutManager(this);
        finished_recycler.setLayoutManager(manager);

        // create new BooksAdapter object and set to RecyclerView
        adapter = new BooksAdapter(this, title_list, author_list, image_list);
        finished_recycler.setAdapter(adapter);

        finished_list = book_manager.getFinished();
        setData(finished_list);
        Log.d("test", "finished is: " + finished_list.size());

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

    public void setData(ArrayList<BookItem> finished_list) {

        Log.d("test", "setData, iterate over finished_list");

        //bookitem_list = book_list;

        title_list.clear();
        author_list.clear();
        image_list.clear();

        if (finished_list.size() != 0) {
            for (BookItem item : finished_list){
                title_list.add(item.getTitle());
                author_list.add(item.getAuthor());
                image_list.add(item.getImage());

                Log.d("test", "title: " + item.getTitle());
                Log.d("test", "author: " + item.getAuthor());
                Log.d("test", "image: " + item.getImage());
            }
        }
        else {
            Log.d("test", "finished_list is empty");
        }

        adapter.notifyDataSetChanged();
    }
}
