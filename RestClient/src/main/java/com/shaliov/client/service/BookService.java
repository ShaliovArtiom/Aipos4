package com.shaliov.client.service;

import com.shaliov.common.model.Book;
import com.sun.jersey.api.client.GenericType;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author ShaliovArtiom.
 */

/**
 *  �����, ������� �������� ������� ������� ������ � �������.
 */
public class BookService {
    /**
     * ���������� � ��������
     */
    private ClientConnection clientConnection;
    private static BookService instance = new BookService();

    private BookService() {
        clientConnection = ClientConnection.getInstance();
    }

    /**
     *  ����� ���������� ��� �����.
     * @return
     */
    public List<Book> getAllBooks() {
        GenericType<List<Book>> genericType = new GenericType<List<Book>>() {};
        List<Book> books = clientConnection.getService().path("books")
                .accept(MediaType.APPLICATION_XML).get(genericType);
        return books;
    }

    /**
     * ����� ���������� ���� ���������� �����
     * @param book ��������������� �����.
     */
    public void updateBook(Book book) {
        ClientConnection.getInstance().getService().path("books").post(book);
    }

    /**
     * ����� ���������� �����
     * @param book �����, ������� ���������� ��������.
     */
    public void addBook(Book book) {
        clientConnection.getService().path("books")
                .accept(MediaType.APPLICATION_XHTML_XML).put(book);
    }

    /**
     * ����� �������� �����
     * @param book �����, ������� ���������� �������.
     */
    public void deleteBook(Book book) {
        clientConnection.getService().path("books")
                .accept(MediaType.APPLICATION_XHTML_XML).delete(book);
    }

    public static BookService getInstance() {
        return instance;
    }
}

