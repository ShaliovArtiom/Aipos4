package com.shaliov.client.controller;

import com.shaliov.client.service.BookService;
import com.shaliov.client.view.Window;
import com.shaliov.common.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/** ������� �������
 * @author ShaliovArtiom, TruntsVitalij
 */
public class BookTableController implements Initializable {

    /**
     * ������, ������� ��������� � �������
     */
    private ObservableList<Book> data;
    @FXML
    /**
     * �������������� �������
     */
    private TableView<Book> bookTableView;
    @FXML
    /**
     * ������� �������� ����� ������� �������
     */
    private TableColumn<Book, String> bookNameColumn;
    @FXML
    /**
     * ������� ����� ������ ������� �������
     */
    private TableColumn<Book, String> authorNameColumn;
    @FXML

    /**
     * ��� ������ �� �������������� �������
     */
    private Label authorNameLabel;
    @FXML
    /**
     * �������� ����� �� �������������� �������
     */
    private Label bookNameLable;
    @FXML
    /**
     * ���������� ������� �� �������������� �������
     */
    private Label numberOfPageLable;
    @FXML
    /**
     * Id ����� �� �������������� �������
     */
    private Label idLable;


    /**
     * ����� ����������� ������ ��������� �����, ���� ��� ����
     * @param book �����
     */
    public void showDetails(Book book) {

        if (book != null) {
            bookNameLable.setText(book.getBookName());
            authorNameLabel.setText(book.getAuthorName());
            numberOfPageLable.setText(String.valueOf(book.getPageValue()));
            idLable.setText(String.valueOf(book.getId()));
        } else {
            bookNameLable.setText("");
            authorNameLabel.setText("");
            numberOfPageLable.setText("");
            idLable.setText("");
        }
    }

    /**
     * ����� ��������� ������ New
     * @throws IOException
     * @throws ParseException
     */
    @FXML
    private void newButton() throws IOException, ParseException {
        Book tempBook = new Book();
        boolean okClicked = Window.showEditDialog(tempBook);
        if (okClicked) {
            BookService.getInstance().addBook(tempBook);
            refresh();
        }
    }

    /**
     * ����� ��������� ������� Edit
     * @throws IOException
     * @throws ParseException
     */
    @FXML
    private void editButton() throws IOException, ParseException {

        Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = Window.showEditDialog(selectedBook);
            if (okClicked) {
                BookService.getInstance().updateBook(selectedBook);
                showDetails(selectedBook);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Window.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

    }

    /**
     * ����� ��������� ������ Delete
     * @throws ParseException
     */
    @FXML
    private void deleteButton() throws ParseException {
        int selectedIndex = bookTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Book book =  bookTableView.getSelectionModel().getSelectedItem();
            BookService.getInstance().deleteBook(book);
            data.remove(book);
            refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Window.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();
        }
    }

    /**
     * ������������� ������� �������
     * @param location ���� � �������
     * @param resources ������ �������
     */
    public void initialize(URL location, ResourceBundle resources) {

        bookNameColumn.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
        authorNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthorNameProperty());

        showDetails(null);

        bookTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));

        try {
            refresh();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����� ��������� ������ refresh
     * @throws ParseException
     */
    @FXML
    public void refresh() throws ParseException {
        List<Book> bookList = BookService.getInstance().getAllBooks();
        data = null;
        data = FXCollections.observableArrayList();
        for (Book book : bookList) {
            data.add(book);

        }
        bookTableView.setItems(data);
    }

}
