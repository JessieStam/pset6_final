package jessie_stam.jessiestam_pset6_desktop.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import jessie_stam.jessiestam_pset6_desktop.Helpers.MenuHelper;
import jessie_stam.jessiestam_pset6_desktop.R;

/**
 * Created by Jessie on 15-10-2016.
 */

public class FindBooksActivity extends EmailPasswordActivity {

//    CheckBox check_title;
//    CheckBox check_isbn;

    EditText user_search_input;
    String user_input;

    private Toolbar toolbar;
    MenuHelper menu_helper;

    //BookManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findbooks);

//        check_title = (CheckBox) findViewById(R.id.check_title);
//        check_isbn = (CheckBox) findViewById(R.id.check_isbn);

        user_search_input = (EditText) findViewById(R.id.findBook);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();

        //manager = BookManager.getOurInstance();
        //manager.clearDisplayList();

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

    public void searchBooks(View view) {

        user_input = user_search_input.getText().toString();

        if (user_input == "") {
            Toast.makeText(this, "Enter a title, author or ISBN to search!", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            // search books

            Intent searchBook = new Intent(this, BooksFoundActivity.class);
            searchBook.putExtra("searched_book", user_input);

            Log.d("test", "user input in findbooks is: " + user_input);
//            searchBook.putExtra("title_list", titles);
//            searchBook.putExtra("poster_list", posters);

            startActivity(searchBook);

            // clear the EditText
            user_search_input.getText().clear();

        }
    }
}
