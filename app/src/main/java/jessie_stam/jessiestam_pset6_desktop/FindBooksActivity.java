package jessie_stam.jessiestam_pset6_desktop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jessie on 15-10-2016.
 */

public class FindBooksActivity extends EmailPasswordActivity {

//    CheckBox check_title;
//    CheckBox check_isbn;

    EditText user_search_input;
    String user_input;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findbooks);

//        check_title = (CheckBox) findViewById(R.id.check_title);
//        check_isbn = (CheckBox) findViewById(R.id.check_isbn);

        user_search_input = (EditText) findViewById(R.id.findBook);

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
