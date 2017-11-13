package com.shaliov.server.service;

import com.shaliov.common.model.Book;
import com.shaliov.server.mysql.MysqlOption;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author ShaliovArtiom.
 */

/**
 * Класс, обрабатывающий запросы клиента
 */
@Path("books")
public class BookService {

    /**
     * Метод для получения всех книг.
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Book> getAllBook() {
        return MysqlOption.getInstance().getAllBook();
    }


    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book getBookId(@PathParam("id") int id) {
        Book book = MysqlOption.getInstance().getBookId(id);
        if (book != null)
        {
            return book;
        }
        else
        {
            throw new NotFoundException();
        }
    }


    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Book deleteBookId(@PathParam("id") int id) {
        Book book = MysqlOption.getInstance().deleteBookTable(id);
        if (book != null)
        {
            return book;
        }
        else
        {
            throw new NotFoundException();
        }
    }

    /**
     * Метод для дабавления книги
     * @param book книга, которую необходимо дабавить.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void addBook(Book book) {
        MysqlOption.getInstance().addBookTable(book);
    }

    /**
     * Метод для удаления книги
     * @param book книга, которую необходимо удалить.
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteBook(Book book) {
        MysqlOption.getInstance().deleteBookTable(book);
    }

    /**
     * Метод для изменения книги
     * @param book книга, на которую необходимо заменить существующую книгу.
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void updateBook(Book book) {
        MysqlOption.getInstance().renameBookTable(book);
    }
}
