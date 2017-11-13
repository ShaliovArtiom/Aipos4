package com.shaliov.client.controller;


import com.shaliov.common.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Класс реализующий диалоговое окно
 * @author ShaliovArtiom, TruntsVitalij
 */
public class EditDialogController {

    /**
     * Поле для ввода названия книги
     */
    @FXML
    private TextField bookNameField;

    /**
     * Поле для ввода Id книги
     */
    @FXML
    private TextField bookIdField;

    /**
     * Поле для ввода имени автора книги
     */
    @FXML
    private TextField authorNameField;

    /**
     * Поле для ввода количества страниц книги
     */
    @FXML
    private TextField pageField;

    /**
     * Диалоговое окно
     */
    private Stage dialogStage;
    /**
     * Книга
     */
    private Book book;
    /**
     * булевская переменная проверки состояния диалогового окна
     */
    private boolean okClicked = false;

    @FXML
    private void initialize() {}

    /**
     * Метод для открытия диалогового окна
     * @param dialogStage диалоговое окно
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Метод, реализующий занесения в поля ввода данных книги, если они есть
     * @param book книга
     */
    public void setBook(Book book){
        this.book = book;
//        bookIdField.setText(Integer.toString(book.getId()));
        bookNameField.setText(book.getBookName());
        authorNameField.setText(book.getAuthorName());
        pageField.setText(Integer.toString(book.getPageValue()));
    }

    /**
     * Проверка значения булевской переменной
     * @return возвращение значения булевской переменной
     */
    public boolean isOkClicked(){
        return okClicked;
    }

    /**
     * Метод обработки кнопки Ok
     */
    @FXML
    private void okButton() {
        if (isInputValid()) {
//            book.setId(Integer.parseInt(bookIdField.getText()));
            book.setBookName(bookNameField.getText());
            book.setAuthorName(authorNameField.getText());
            book.setPageValue(Integer.parseInt(pageField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Метод проверки корректности введённых данных
     * @return возвращение значения булевской переменной
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (bookNameField.getText() == null || bookNameField.getText().length() == 0) {
            errorMessage += "No valid Book name!\n";
        }
        if (authorNameField.getText() == null || authorNameField.getText().length() == 0) {
            errorMessage += "No valid Author name!\n";
        }
        if (pageField.getText() == null || pageField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
            try {
                Integer.parseInt(pageField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid page number (must be an integer)!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    /**
     * Метод обработки кнопки Cancel
     */
    @FXML
    private void cancelButton() {
        dialogStage.close();
    }
}
