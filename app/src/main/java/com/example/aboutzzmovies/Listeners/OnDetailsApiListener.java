package com.example.aboutzzmovies.Listeners;

import com.example.aboutzzmovies.Models.DetailApiResponse;

public interface OnDetailsApiListener {
    void onResponse(DetailApiResponse response);
    void  onError(String message);
}
