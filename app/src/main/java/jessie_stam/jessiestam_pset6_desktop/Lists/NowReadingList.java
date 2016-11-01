package jessie_stam.jessiestam_pset6_desktop.Lists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
 * TBR Jar - NowReadingList
 *
 * Jessie Stam
 * 10560599
 *
 * Activity that displays the books that the user has added to currently reading list. When clicked,
 * BookDetailsActivity will be started.
 */
public class NowReadingList extends AppCompatActivity {

    private Toolbar toolbar;
    MenuHelper menu_helper;

    RecyclerView nowreading_recycler;
    LinearLayoutManager manager;
    BooksAdapter adapter;

    ArrayList<String> title_list;
    ArrayList<String> author_list;
    ArrayList<String> image_list;

    ArrayList<BookItem> nowreading_list;
    BookManager book_manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowreading);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();

        book_manager = BookManager.getOurInstance();

        nowreading_recycler = (RecyclerView) findViewById(R.id.booksfound_list);
        nowreading_list = new ArrayList<>();

        // check if savedInstanceState is null, if not, load lists from onRestore
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
        nowreading_recycler.setLayoutManager(manager);

        // create new BooksAdapter object and set to RecyclerView
        adapter = new BooksAdapter(this, title_list, author_list, image_list);
        nowreading_recycler.setAdapter(adapter);

        nowreading_list = book_manager.getNowreading();
        setData(nowreading_list);

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

    public void setData(ArrayList<BookItem> nowreading_list) {

        // clear old data
        title_list.clear();
        author_list.clear();
        image_list.clear();

        // add title, author and image for each item in list
        if (nowreading_list.size() != 0) {
            for (BookItem item : nowreading_list){
                title_list.add(item.getTitle());
                author_list.add(item.getAuthor());
                image_list.add(item.getImage());
            }
        }

        adapter.notifyDataSetChanged();
    }
}
