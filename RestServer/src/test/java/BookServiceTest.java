import com.shaliov.common.model.Book;
import com.shaliov.server.mysql.DBWorker;
import com.shaliov.server.mysql.MysqlOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.Assert.assertEquals;

/**
 * @author ShaliovArtiom.
 */
public class BookServiceTest {

    private DBWorker dbWorker;
    private MysqlOption mysqlOption;

    public final String sqlForAdd = "insert into book_table (BookName, AuthorName, PageOfBook) values (\'test\', \'test\', 1)";
    public final String sqlForRename = "update book_table SET BookName = \'test\' , AuthorName = \'test\' , PageOfBook = 1 WHERE id = 0";
    public final String sqlForGetAll = "select * from book_table";
    public final String sqlForRemove = "delete LOW_PRIORITY from book_table WHERE id = 0 and BookName = \'test\' and AuthorName = \'test\' and PageOfBook =1";

    @Before
    public void init(){
        dbWorker = DBWorker.getInstance();
        mysqlOption = MysqlOption.getInstance();
    }

    @After
    public void destroy(){
        dbWorker = null;
        mysqlOption = null;
    }

    @Test
    public void getAllBooks(){
        mysqlOption.getAllBook();
        assertEquals(sqlForGetAll, mysqlOption.getSqlForGetAll());
    }

    @Test
    public void addBook(){
        Book book = new Book("test", "test");
        book.setPageValue(1);
        mysqlOption.addBookTable(book);
        assertEquals(sqlForAdd, mysqlOption.getSqlForAdd());
    }

    @Test
    public void removeBook(){
        Book book = new Book("test", "test");
        book.setPageValue(1);
        mysqlOption.deleteBookTable(book);
        assertEquals(sqlForRemove, mysqlOption.getSqlForRemove());
    }

    @Test
    public void renameBook(){
        Book book = new Book("test", "test");
        book.setPageValue(1);
        mysqlOption.renameBookTable(book);
        assertEquals(sqlForRename, mysqlOption.getSqlForRename());
    }

    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(BookServiceTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }

}
