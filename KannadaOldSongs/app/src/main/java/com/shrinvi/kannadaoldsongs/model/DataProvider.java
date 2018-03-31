package com.shrinvi.kannadaoldsongs.model;

import android.content.Context;

import com.shrinvi.kannadaoldsongs.R;

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
        list.add(new KNANewsPaper(playlistNames[0], R.drawable.dr_raj, playListUrls[0]));
        list.add(new KNANewsPaper(playlistNames[1], R.drawable.dr_vishnu, playListUrls[1]));
        list.add(new KNANewsPaper(playlistNames[2], R.drawable.shankar_nag, playListUrls[2]));
        list.add(new KNANewsPaper(playlistNames[3], R.drawable.ravichandran, playListUrls[3]));
        return list;
    }
}
