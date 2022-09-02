package com.example.googlebooksapi;

class Book {

   private String mBookTitle;
   private String mBookAuthor;
   private String mBookPublisher;

   public String getBookTitle() {
      return mBookTitle;
   }

   public String getBookAuthor() {
      return mBookAuthor;
   }

   public String getBookPublisher() {
      return mBookPublisher;
   }

   public Book(String bookTitle, String bookAuthor, String bookPublisher) {
      mBookTitle = bookTitle;
      mBookAuthor = bookAuthor;
      mBookPublisher = bookPublisher;
   }
}
