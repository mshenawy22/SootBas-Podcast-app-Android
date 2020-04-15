package com.sootbas.sootbasapp.custom;

import android.content.SearchRecentSuggestionsProvider;

public class QuerySuggestionProvider extends SearchRecentSuggestionsProvider{

    public static final String AUTHORITY =
        "com.sootbas.sootbasapp.custom.QuerySuggestionProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public QuerySuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

}
