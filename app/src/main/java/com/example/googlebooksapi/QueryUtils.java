package com.example.googlebooksapi;

import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class QueryUtils {

   private static final String LOG_TAG = QueryUtils.class.getSimpleName();

   public QueryUtils() {
   }

   public static List<Book> fetchBooksData(String requestUrl){
      URL url = createUrl(requestUrl);
      String jsonResponse = null;
      try {
         jsonResponse = makeHttpRequest(url);
      } catch (IOException e){
         Log.e(LOG_TAG, "Problem Making Http Request", e);
      }
      List<Book> books = extractItemsFromJson(jsonResponse);
      return books;
   }

   public static List<Book> extractItemsFromJson(String bookJSON){
      if (TextUtils.isEmpty(bookJSON)){
         return null;
      }

      List<Book> books = new ArrayList<>();
      try {

         JSONObject root = new JSONObject(bookJSON);
         JSONArray itemsArray = root.getJSONArray("items");
         for (int i = 0; i <= itemsArray.length(); i++){
            JSONObject currentBook = itemsArray.getJSONObject(i);

            JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

            String title = volumeInfo.getString("title");
            String publisher = volumeInfo.getString("publisher");
            String publishedDate = volumeInfo.getString("publishedDate");

            Book book = new Book(title, publisher, publishedDate);
            books.add(book);
         }

      } catch (JSONException e){
         Log.e("QueryUtils", "Problem parsing the Book JSON results", e);
      }
      return books;
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
   /**
    * Returns String which contains jsonResponse from the given URL
    */
   private static String makeHttpRequest(URL url) throws IOException{
      String jsonResponse = "";

      if (url == null){
         return null;
      }

      HttpURLConnection urlConnection = null;
      InputStream inputStream = null;

      try {
         urlConnection = (HttpURLConnection) url.openConnection();
         urlConnection.setReadTimeout(10000)/*milliseconds*/;
         urlConnection.setConnectTimeout(15000)/*milliseconds*/;
         urlConnection.setRequestMethod("GET");
         urlConnection.connect();

         if (urlConnection.getResponseCode() == 200){
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
         } else {
            Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode());
         }
      } catch (IOException e){
         Log.e(LOG_TAG, "Problem in retrieving the Book JSON results.", e);
      } finally {
         if (urlConnection != null){
            urlConnection.disconnect();
         }
         if (inputStream != null){
            inputStream.close();
         }
      }

      return jsonResponse;
   }

   /**
    * Convert the {@link InputStream} into a String which contains the
    * whole JSON response from the server.
    */
   private static String readFromStream (InputStream inputStream) throws IOException{

      StringBuilder output = new StringBuilder();
      if (inputStream != null){
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
         BufferedReader reader = new BufferedReader(inputStreamReader);
         String line = reader.readLine();
         while (line != null){
            output.append(line);
            line = reader.readLine();
         }
      }
      return output.toString();
   }
}
