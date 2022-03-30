package com.example.aboutzzmovies.Listeners;

import com.example.aboutzzmovies.Models.SearchApiResponse;

public interface OnSearchApiListener {
    void onResponse(SearchApiResponse searchApiResponse);
    void onError(String message);
}
