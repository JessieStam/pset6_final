package jessie_stam.jessiestam_pset6_desktop.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import jessie_stam.jessiestam_pset6_desktop.Activities.FindBooksActivity;
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
        String toast = null;

        switch (id) {
            case R.id.action_settings:
                toast = "You clicked the settings button!";
                break;
            case R.id.action_lists:
                toast = "You clicked the lists button!";
                break;
            case R.id.action_account:
                toast = "You clicked the account button!";

                break;
            case R.id.tbrList:
                toast = "You clicked TBR bitch";

                Intent openTBR = new Intent(context, TbrJarList.class);
                context.startActivity(openTBR);
                break;

            case R.id.action_search:

                toast = checkIfLoggedIn();
                if (toast.equals("")) {
                    toast = "You clicked the search button!";

                    Intent searchBook = new Intent(context, FindBooksActivity.class);
                    context.startActivity(searchBook);
                    break;
                }


//                Intent searchBook = new Intent(context, FindBooksActivity.class);
//                context.startActivity(searchBook);
//                break;
        }
        return toast;
    }

    public String checkIfLoggedIn() {

        String user = manager.getCurrent_user();
        String toast = null;

        if (user.equals("")) {
            toast = "You have to log in first!";
        }
        else {
            toast = "";
        }

        return toast;
    }
}
