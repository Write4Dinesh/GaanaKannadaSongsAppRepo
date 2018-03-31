package com.shrinvi.ssr.model;

import android.content.Context;

import com.shrinvi.ssr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shrinvigroup on 27/03/2018.
 */

public class DataProvider {

    public static List<KNANewsPaper> getNewsPapers(Context context) {
        List<KNANewsPaper> list = new ArrayList<>();
        String[] playlistNames = context.getResources().getStringArray(R.array.playlist_name);
        String[] playListUrls = context.getResources().getStringArray(R.array.playlist_url);
        list.add(new KNANewsPaper(playlistNames[0], playListUrls[0]));
        list.add(new KNANewsPaper(playlistNames[1], playListUrls[1]));
        list.add(new KNANewsPaper(playlistNames[2], playListUrls[2]));
        list.add(new KNANewsPaper(playlistNames[3], playListUrls[3]));
        return list;
    }
}
