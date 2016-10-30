package jessie_stam.jessiestam_pset6_desktop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Jessie on 27-10-2016.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

    //private ArrayList<BookItem> books_list;

    private ArrayList<String> titles;
    private ArrayList<String> authors;
    private ArrayList<String> images;

    /**
     * This function constructs the FilmAdapter
     */
    public BooksAdapter(ArrayList<String> titles, ArrayList<String> authors, ArrayList<String> images) {

        this.titles = titles;
        this.authors = authors;
        this.images = images;

//        if (books_list != null) {
//            for (BookItem item : books_list) {
//                this.titles.add(item.getTitle());
//                this.authors.add(item.getAuthor());
//                this.images.add(item.getImage());
//            }
//        }
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

//    // set onClickListener to RecyclerView
//    View.OnClickListener listener = new View.OnClickListener() {
//
//        // when item is clicked, change color
//        @Override
//        public void onClick(View view) {
//            // if item is selected, change color to turquoise
//            if (currentColor.equals(unfinished) || currentColor == null) {
//                view.setBackgroundColor(Color.parseColor("#d4f7f4"));
//                currentColor = finished;
//            }
//            // if item is not selected, change color back to white
//            else if (currentColor.equals(finished)) {
//                view.setBackgroundColor(Color.WHITE);
//                currentColor = unfinished;
//            }
//        }
//    };

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bookrow, parent, false);
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
        //viewHolder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
