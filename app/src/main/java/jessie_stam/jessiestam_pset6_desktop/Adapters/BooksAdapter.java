package jessie_stam.jessiestam_pset6_desktop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jessie_stam.jessiestam_pset6_desktop.Activities.BookDetailsActivity;
import jessie_stam.jessiestam_pset6_desktop.R;


/**
 * Created by Jessie on 27-10-2016.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

    //private ArrayList<BookItem> books_list;

    private Context context;
    private ArrayList<String> titles;
    private ArrayList<String> authors;
    private ArrayList<String> images;
    private String title;

    /**
     * This function constructs the FilmAdapter
     */
    public BooksAdapter(Context context, ArrayList<String> titles, ArrayList<String> authors, ArrayList<String> images) {

        this.context = context;
        this.titles = titles;
        this.authors = authors;
        this.images = images;
    }

    /**
     * Construct ViewHolder to display title and author in
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myTitlesView;
        TextView myAuthorsView;
        ImageView myImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            myTitlesView = (TextView) itemView.findViewById(R.id.titles_row);
            myAuthorsView = (TextView) itemView.findViewById(R.id.authors_row);
            myImageView = (ImageView) itemView.findViewById(R.id.image_row);
        }
    }

    // set onClickListener to RecyclerView
    View.OnClickListener listener = new View.OnClickListener() {

        // when item is clicked, move to bookinfo activity
        @Override
        public void onClick(View view) {

            TextView titleview = (TextView) view.findViewById(R.id.titles_row);
            title = titleview.getText().toString();

            Log.d("test", "title = " + title);

            Intent bookDetails = new Intent(context, BookDetailsActivity.class);
            bookDetails.putExtra("clicked_book", title);
            context.startActivity(bookDetails);

//            ((BooksFoundActivity)context).finish();
        }
    };

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.myTitlesView.setText(titles.get(position));
        viewHolder.myAuthorsView.setText(authors.get(position));

        if (images != null) {
            Context context = viewHolder.myImageView.getContext();
            Picasso.with(context).load(images.get(position)).resize(100, 148).into(viewHolder.myImageView);
        }

        // set listener to allow for selecting item and changing color
        viewHolder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

}
