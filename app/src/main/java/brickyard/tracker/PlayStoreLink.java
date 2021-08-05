package brickyard.tracker;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;



public class PlayStoreLink {

    public void moreApps(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context.getString(R.string.url_market_search_app) + context.getString(R.string.developer))));
        } catch (android.content.ActivityNotFoundException anfe) {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(context.getString(R.string.url_playstore_search_app) + context.getString(R.string.developer))));
            } catch (Exception e) {
                Toast.makeText(context, R.string.install_google_play_store, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void rateApp(Context context) {
        try {
            Uri uri = Uri.parse(context.getString(R.string.app_link) + context.getPackageName());
            Intent app_link = new Intent(Intent.ACTION_VIEW, uri);

            context.startActivity(app_link);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void shareApp(Context context) {
        try {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);

            //set type
            shareIntent.setType("text/plain");

            //add what a subject you want
            String subject = "Money Tracker";
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);

            //message
            String message = context.getString(R.string.app_message) + "\n\n" + context.getString(R.string.app_link) + context.getPackageName();

            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);

            //start sharing via
            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.app_message)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
