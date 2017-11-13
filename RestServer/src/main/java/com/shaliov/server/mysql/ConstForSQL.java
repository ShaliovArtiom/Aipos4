package com.shaliov.server.mysql;


/**
 * константы для SQL
 */
public final class ConstForSQL {
    public static final String INSERT_NEW_BOOK = "insert into book_table (BookName, AuthorName, PageOfBook) values (?, ?, ?)";

    public static final String RENAME_BOOK = "update book_table SET BookName = ? , AuthorName = ? , PageOfBook = ? WHERE id = ?";

    public static final String SELECT_ALL_FROM_BOOK = "select * from book_table";

    public static final String DELETE_FROM_BOOK = "delete LOW_PRIORITY from book_table WHERE id = ? and BookName = ? and AuthorName = ? and PageOfBook =?";
    public static final String DELETE_FROM_BOOKID = "delete from book_table WHERE id = ?";
    public static final String FIND_RENAME = "select from book_table WHERE id = ?";


}
