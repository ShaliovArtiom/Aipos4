package com.shaliov.server.storage;

import com.shaliov.common.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * ��������� ���� ������
 * @author ShaliovArtiom, TruntsVitalij
 */
public class Storage {
    /**
     * ����, ��������������� ��� �������� ������������� ���������� ������
     */
    private static Storage instance = null;
    /**
     * ���� ����
     */
    private List<Book> bookList = new ArrayList<>();
    
    /**
     * ����������� �� ���������
     */
    private Storage() {
    }

    /**
     * ���������� ����� � ���� ����
     * @param book �����, ������� ���������� ��������
     */
    public void add(Book book)
    {
        bookList.add(book);
    }

    /**
     * ����� ������� ����� ����
     */
    public void clear() {
        bookList.clear();
    }

    /**
     * ����� ������ �����
     * @param book �����, ������� ���������� ����� � �������� � ����
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
     * ����� �������� �������������� ���� ������
     * @return ����������� �������� instance
     */
    public static Storage getIstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    /**
     * ����� ��������� ����� ����
     * @return ���������� ������ ���� ����
     */
    public List<Book> getBookList() {
        return bookList;
    }
}
