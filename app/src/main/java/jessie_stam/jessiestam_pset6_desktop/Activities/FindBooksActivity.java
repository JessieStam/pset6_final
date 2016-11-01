package jessie_stam.jessiestam_pset6_desktop.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import jessie_stam.jessiestam_pset6_desktop.Helpers.MenuHelper;
import jessie_stam.jessiestam_pset6_desktop.R;

/**
 * TBR Jar - FindBooksActiviy
 *
 * Jessie Stam
 * 10560599
 *
 * Prompts the user for a title, author of ISBN, then moves on to BooksFoundActivity.
 */
public class FindBooksActivity extends EmailPasswordActivity {

    EditText user_search_input;
    String user_input;

    private Toolbar toolbar;
    MenuHelper menu_helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findbooks);

        user_search_input = (EditText) findViewById(R.id.findBook);

        // construct toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu_helper = new MenuHelper();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu_item) {

        // display toast for clicked toolbar item
        String clicked_item = menu_helper.getClickedMenuItem(menu_item, this);
        Toast.makeText(this, clicked_item, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(menu_item);
    }

    /**
     * Takes the user input and sends it to BooksFoundActivity
     */
    public void searchBooks(View view) {

        // get user input string, make sure it is not empty
        user_input = user_search_input.getText().toString();

        if (user_input.equals("")) {
            Toast.makeText(this, "Enter a title, author or ISBN to search!", Toast.LENGTH_SHORT)
                    .show();
        }
        else {
            // moves to BooksFoundActivity, adds user input string
            Intent searchBook = new Intent(this, BooksFoundActivity.class);
            searchBook.putExtra("searched_book", user_input);
            startActivity(searchBook);

            // clear the EditText
            user_search_input.getText().clear();

        }
    }
}
