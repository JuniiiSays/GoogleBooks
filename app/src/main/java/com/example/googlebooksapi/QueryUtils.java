package com.example.googlebooksapi;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class QueryUtils {

   private static final String LOG_TAG = QueryUtils.class.getSimpleName();

   public QueryUtils() {
   }

   /**
    * Returns new URL object from the given string URL.
    */
   private static URL createUrl(String stringUrl){
      URL url = null ;
      try {
         url = new URL(stringUrl);
      } catch (MalformedURLException e){
         Log.e(LOG_TAG, "Problem in Creating Url ", e);
      }
      return url;
   }
}
