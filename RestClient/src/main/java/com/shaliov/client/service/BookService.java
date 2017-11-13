package com.shaliov.client.service;

import com.shaliov.common.model.Book;
import com.sun.jersey.api.client.GenericType;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author ShaliovArtiom.
 */

/**
 *  Клаас, который передает методам сервера данные с клиента.
 */
public class BookService {
    /**
     * Соеденение с сервером
     */
    private ClientConnection clientConnection;
    private static BookService instance = new BookService();

    private BookService() {
        clientConnection = ClientConnection.getInstance();
    }

    /**
     *  Метод получающий все книги.
     * @return
     */
    public List<Book> getAllBooks() {
        GenericType<List<Book>> genericType = new GenericType<List<Book>>() {};
        List<Book> books = clientConnection.getService().path("books")
                .accept(MediaType.APPLICATION_XML).get(genericType);
        return books;
    }

    /**
     * Метод изменяющий поля конкретной кники
     * @param book редактированная книга.
     */
    public void updateBook(Book book) {
        ClientConnection.getInstance().getService().path("books").post(book);
    }

    /**
     * Метод добавления книги
     * @param book книга, которую необходимо добавить.
     */
    public void addBook(Book book) {
        clientConnection.getService().path("books")
                .accept(MediaType.APPLICATION_XHTML_XML).put(book);
    }

    /**
     * Метод удаления книги
     * @param book книга, которую необходимо удалить.
     */
    public void deleteBook(Book book) {
        clientConnection.getService().path("books")
                .accept(MediaType.APPLICATION_XHTML_XML).delete(book);
    }

    public static BookService getInstance() {
        return instance;
    }
}

