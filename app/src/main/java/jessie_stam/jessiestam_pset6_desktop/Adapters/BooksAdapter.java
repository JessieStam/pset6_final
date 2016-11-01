package jessie_stam.jessiestam_pset6_desktop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
 * TBR Jar - BooksAdapter
 *
 * Jessie Stam
 * 10560599
 *
 * A ListView adapter that places titles, authors and images into a ListView
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

    private Context context;
    private ArrayList<String> titles;
    private ArrayList<String> authors;
    private ArrayList<String> images;
    private String title;

    /**
     * This function constructs the BookAdapter
     */
    public BooksAdapter(Context context, ArrayList<String> titles, ArrayList<String> authors, ArrayList<String> images) {

        this.context = context;
        this.titles = titles;
        this.authors = authors;
        this.images = images;
    }

    /**
     * Construct ViewHolder to display title, author and image
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

            // get title of clicked item
            TextView titleview = (TextView) view.findViewById(R.id.titles_row);
            title = titleview.getText().toString();

            // start new activity, add title
            Intent bookDetails = new Intent(context, BookDetailsActivity.class);
            bookDetails.putExtra("clicked_book", title);
            context.startActivity(bookDetails);

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

        // add data to the RecyclerView
        viewHolder.myTitlesView.setText(titles.get(position));
        viewHolder.myAuthorsView.setText(authors.get(position));

        if (images != null) {
            Context context = viewHolder.myImageView.getContext();
            Picasso.with(context).load(images.get(position)).resize(100, 148).into(viewHolder.myImageView);
        }

        // set onclick listener, so we acn move on to BookDetailsActivity later
        viewHolder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

}
