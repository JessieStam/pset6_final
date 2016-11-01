package jessie_stam.jessiestam_pset6_desktop.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import jessie_stam.jessiestam_pset6_desktop.Activities.FindBooksActivity;
import jessie_stam.jessiestam_pset6_desktop.Activities.MainActivity;
import jessie_stam.jessiestam_pset6_desktop.Lists.FavoritesList;
import jessie_stam.jessiestam_pset6_desktop.Lists.FinishedList;
import jessie_stam.jessiestam_pset6_desktop.Lists.NowReadingList;
import jessie_stam.jessiestam_pset6_desktop.Lists.TbrJarList;
import jessie_stam.jessiestam_pset6_desktop.R;

/**
 * TBR Jar - MenuHelper
 *
 * Jessie Stam
 * 10560599
 *
 * Helper class that handles toolbar actions.
 */
public class MenuHelper {

    Context context;
    BookManager manager;

    /**
     * Gets the clicked toolbar item and handles accordingly.
     */
    public String getClickedMenuItem(MenuItem item, Context context) {

        this.context = context;
        manager = BookManager.getOurInstance();

        int id = item.getItemId();
        String toast = "";

        // chick which item is clicked
        switch (id) {
            // if settings button is clicked, inform user that it doesn't work
            case R.id.action_settings:
                toast = "This button is for decorative purposes only";
                break;
            // if account button is clicked, take user to MainActivity
            case R.id.action_account:
                Intent openAccountpage = new Intent(context, MainActivity.class);
                context.startActivity(openAccountpage);
                break;

            // if tbr list is clicked and user is logged in, take user to tbr
            case R.id.tbrList:
                toast = checkIfLoggedIn();

                if (toast.equals("")) {
                    Intent openTBR = new Intent(context, TbrJarList.class);
                    context.startActivity(openTBR);
                }
                break;

            // if favorite list is clicked and user in logged in, take user to favorite
            case R.id.favList:

                if (toast.equals("")) {
                    Intent openFavorites = new Intent(context, FavoritesList.class);
                    context.startActivity(openFavorites);
                }
                break;

            // if finished list is clicked and user is logged in, take user to finished;
            case R.id.readList:

                if (toast.equals("")) {
                    Intent openFinished = new Intent(context, FinishedList.class);
                    context.startActivity(openFinished);
                }
                break;

            // if current list is clicked and user is logged in, take user to current list;
            case R.id.currentList:

                if (toast.equals("")) {
                    Intent openNowreading = new Intent (context, NowReadingList.class);
                    context.startActivity(openNowreading);
                }
                break;

            // if search button is clicked and user is logged in, take user to FindBooksActivity
            case R.id.action_search:
                toast = checkIfLoggedIn();
                if (toast.equals("")) {
                    Intent searchBook = new Intent(context, FindBooksActivity.class);
                    context.startActivity(searchBook);
                    break;
                }
        }
        return toast;
    }

    /**
     * Check if user is logged in to be able to move on.
     */
    public String checkIfLoggedIn() {

        String user = manager.getCurrent_user();
        String toast = "";

        if (user.equals("")) {
            toast = "You have to log in first!";
        }
        else {
            toast = "";
        }

        return toast;
    }
}
