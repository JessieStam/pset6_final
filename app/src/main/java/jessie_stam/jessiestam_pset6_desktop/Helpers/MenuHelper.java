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
 * Created by Jessie on 31-10-2016.
 */

public class MenuHelper {

    Context context;
    BookManager manager;

    public String getClickedMenuItem(MenuItem item, Context context) {

        this.context = context;

        manager = BookManager.getOurInstance();

        int id = item.getItemId();
        String toast = "";

        switch (id) {
            case R.id.action_settings:
                toast = "This button is for decorative purposes only";
                break;
            case R.id.action_lists:
                break;

            case R.id.action_account:
                Intent openAccountpage = new Intent(context, MainActivity.class);
                context.startActivity(openAccountpage);
                break;

            case R.id.tbrList:

                toast = checkIfLoggedIn();

                if (toast.equals("")) {
                    Intent openTBR = new Intent(context, TbrJarList.class);
                    context.startActivity(openTBR);
                    break;
                }

            case R.id.favList:

                if (toast.equals("")) {
                    Intent openFavorites = new Intent(context, FavoritesList.class);
                    context.startActivity(openFavorites);
                    break;
                }

            case R.id.readList:

                if (toast.equals("")) {
                    Intent openFinished = new Intent(context, FinishedList.class);
                    context.startActivity(openFinished);
                    break;
                }

            case R.id.currentList:

                if (toast.equals("")) {
                    Intent openNowreading = new Intent (context, NowReadingList.class);
                    context.startActivity(openNowreading);
                    break;
                }

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
