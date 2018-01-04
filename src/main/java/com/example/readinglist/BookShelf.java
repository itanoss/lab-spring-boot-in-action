package com.example.readinglist;

import java.util.Optional;

public class BookShelf {
    private final IsbnSearchService isbnSearchService;
    private final ReadingListRepository repository;

    public BookShelf(IsbnSearchService isbnSearchService, ReadingListRepository repository) {
        this.isbnSearchService = isbnSearchService;
        this.repository = repository;
    }

    void startToRead(String isbn, String readerName) throws IsbnNotFoundException {
        Optional<IsbnSearchService.IsbnInformation> infoOpt = isbnSearchService.findByIsbn(isbn);
        IsbnSearchService.IsbnInformation info = infoOpt.orElseThrow(() -> new IsbnNotFoundException());

        Book book = asBook(info);
        book.setReader(readerName);
        repository.save(book);
    }

    private Book asBook(IsbnSearchService.IsbnInformation info) {
        Book book = new Book();
        book.setAuthor(info.author);
        book.setIsbn(info.isbn);
        book.setTitle(info.title);
        book.setDescription(info.description);
        return book;
    }

    public class IsbnNotFoundException extends Exception {
    }
}
