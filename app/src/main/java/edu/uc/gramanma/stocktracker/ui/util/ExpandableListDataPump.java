package edu.uc.gramanma.stocktracker.ui.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        /*
        This is just a hardcoded way to populate the list for the moment
        I would probably use a for loop to iterate through certain JSON objects
         */
        List<String> watchlist = new ArrayList<String>();
        watchlist.add("GME: $Price");
        watchlist.add("DOGE: $Price");
        watchlist.add("NOK: $Price");

        expandableListDetail.put("Your Watchlist", watchlist);

        return expandableListDetail;
    }
}