package com.example.readinglist;

import java.util.Optional;

public interface IsbnSearchService {
    Optional<IsbnInformation> findByIsbn(String isbn);

    class IsbnInformation {
        public final String isbn;
        public final String title;
        public final String author;
        public final String description;

        public IsbnInformation(String isbn, String title, String author, String description) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.description = description;
        }


    }
}
