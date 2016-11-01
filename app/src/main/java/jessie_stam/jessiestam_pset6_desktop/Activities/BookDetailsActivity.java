package jessie_stam.jessiestam_pset6_desktop.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import jessie_stam.jessiestam_pset6_desktop.Items.BookItem;
import jessie_stam.jessiestam_pset6_desktop.Helpers.BookManager;
import jessie_stam.jessiestam_pset6_desktop.Helpers.MenuHelper;
import jessie_stam.jessiestam_pset6_desktop.R;

/**
 * Created by Jessie on 31-10-2016.
 */
public class BookDetailsActivity extends BooksFoundActivity implements View.OnClickListener {

    private Toolbar toolbar;
    MenuHelper menu_helper;

    String clicked_book;
    TextView title;
    TextView author;
    TextView year;
    TextView rating;
    ImageView image;
    TextView summary;

    BookManager manager;

    Button open_options;
    Button tbr_button;
    Button fav_button;
    Button fin_button;
    Button reading_button;

    BookItem clicked_item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);

        open_options = (Button) findViewById(R.id.openoptions_button);
        tbr_button = (Button) findViewById(R.id.tbr_button);
        fav_button = (Button) findViewById(R.id.fav_button);
        fin_button = (Button) findViewById(R.id.fin_button);
        reading_button = (Button) findViewById(R.id.reading_button);

        findViewById(R.id.openoptions_button).setOnClickListener(this);
        findViewById(R.id.tbr_button).setOnClickListener(this);
        findViewById(R.id.fav_button).setOnClickListener(this);
        findViewById(R.id.fin_button).setOnClickListener(this);
        findViewById(R.id.reading_button).setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();

        manager = BookManager.getOurInstance();

        // get extras from MainActivity
        Bundle extras = getIntent().getExtras();

        // if extras exist, update title and poster string and titles and posters lists
        if (extras != null) {
            clicked_book = extras.getString("clicked_book");

            Log.d("test", "in bookdetail, clicked book = " + clicked_book);

            // get helper class to fill in blanks
            clicked_item = manager.getItemByTitle(clicked_book);

            title = (TextView) findViewById(R.id.app_detailtitle);
            title.setText(clicked_book);

            author = (TextView) findViewById(R.id.book_author_id);
            author.setText(clicked_item.getAuthor());

            image = (ImageView) findViewById(R.id.book_cover_id);
            Picasso.with(this).load(clicked_item.getImage()).resize(300, 463).into(image);

            year = (TextView) findViewById(R.id.book_year_id);
            year.setText(clicked_item.getYear());

            rating = (TextView) findViewById(R.id.book_rating_id);
            rating.setText(clicked_item.getRating());

            summary = (TextView) findViewById(R.id.book_summary);
            summary.setText(clicked_item.getSummary());

        }
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

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch (i) {
            case R.id.openoptions_button:
                open_options.setText("Add book to...");
                Log.d("test", "clicked openoptions button");
                changeVisibility();
                break;

            case R.id.tbr_button:

                Log.d("test", "clicked item in onclick: " + clicked_item);
                manager.add_to_tbr(clicked_item);
                Toast.makeText(this, "Added to your TBR Jar!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.fav_button:
                break;
            case R.id.fin_button:
                break;
            case R.id.reading_button:
                break;

        }
    }

    public void changeVisibility() {

        if (tbr_button.getVisibility() == View.VISIBLE) {
            tbr_button.setVisibility(View.INVISIBLE);
            fav_button.setVisibility(View.INVISIBLE);
            fin_button.setVisibility(View.INVISIBLE);
            reading_button.setVisibility(View.INVISIBLE);
        }
        else {
            tbr_button.setVisibility(View.VISIBLE);
            fav_button.setVisibility(View.VISIBLE);
            fin_button.setVisibility(View.VISIBLE);
            reading_button.setVisibility(View.VISIBLE);
        }

    }
}
