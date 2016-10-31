package jessie_stam.jessiestam_pset6_desktop;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

/**
 * Created by Jessie on 31-10-2016.
 */

public class MenuHelper {

    Context context;

    public String getClickedMenuItem(MenuItem item, Context context) {

        this.context = context;

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
                break;
            case R.id.action_search:
                toast = "You clicked seach";
                Intent searchBook = new Intent(context, FindBooksActivity.class);
                context.startActivity(searchBook);
                break;
        }
        return toast;
    }
}
