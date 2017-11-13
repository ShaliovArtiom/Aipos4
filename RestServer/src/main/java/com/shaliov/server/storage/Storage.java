package com.shaliov.server.storage;

import com.shaliov.common.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Локальная база данных
 * @author ShaliovArtiom, TruntsVitalij
 */
public class Storage {
    /**
     * поле, предназначенное для создания единственного экземпляра класса
     */
    private static Storage instance = null;
    /**
     * Лист книг
     */
    private List<Book> bookList = new ArrayList<>();
    
    /**
     * Конструктор по умолчанию
     */
    private Storage() {
    }

    /**
     * Добавление книги в лист книг
     * @param book книга, которую необходимо добавить
     */
    public void add(Book book)
    {
        bookList.add(book);
    }

    /**
     * Метод очистки листа книг
     */
    public void clear() {
        bookList.clear();
    }

    /**
     * Метод поиска книги
     * @param book книга, которую необходимо найти и изменить её поля
     */
    public Book findRenameBook(Book book) {
        for (Book findBok : bookList) {
            if (book.getId() == findBok.getId()) {
                book.setBookName(findBok.getBookName());
                book.setAuthorName(findBok.getAuthorName());
                book.setPageValue(findBok.getPageValue());
                return book;
            }
        }
        book.setId(0);
        return book;
    }

    public Book findRenameBookId(int id) {
        for (Book findBok : bookList) {
            if (id == findBok.getId()) {

                return findBok;
            }
        }
        return null;
    }

    public void findRenameBookRename(Book book) {
        for (Book findBok : bookList) {
            if (book.getId() == findBok.getId()) {
                findBok.setBookName(book.getBookName());
                findBok.setAuthorName(book.getAuthorName());
                findBok.setPageValue(book.getPageValue());
            }
        }
    }

    /**
     * Метод проверки единственности базы данных
     * @return возвращение значения instance
     */
    public static Storage getIstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    /**
     * Метод получения листа книг
     * @return возвращает полный лист книг
     */
    public List<Book> getBookList() {
        return bookList;
    }
}
