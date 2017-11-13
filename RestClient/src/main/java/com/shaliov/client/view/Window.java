package com.shaliov.client.view;


import com.shaliov.client.controller.EditDialogController;
import com.shaliov.common.model.Book;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Класс, описывающий главное окно программы
 * @author ShaliovArtiom, TruntsVitalij
 */
public class Window extends Application {

    /**
     * панель
     */
    private AnchorPane layout;
    /**
     * контейнер верхнего уровня
     */
    private static Stage primaryStage;

    /**
     * Конструктор по умолчанию
     */
    public Window() {
    }

    /**
     * Функция инициализации контейнера верхнего уровня
     * @param primaryStage контейнер верхнего уровня
     * @throws Exception ошибка создания окна
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initLayout();
    }

    /**
     * Функция отображения таблицы
     */
    public void bind() {
        launch();
    }

    /**
     * Метод создания таблицы
     * @throws Exception ошибка чтения table.fxml
     */
    private void initLayout() throws Exception {
        URL resource = Window.class.getResource("/table.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        layout = loader.load();
        Scene scene = new Scene(layout);
        primaryStage.setTitle("AIPOS4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Метод вывода окна изменения, добавления книги
     * @param book книга
     * @return возвращение значения булевской переменной
     * @throws IOException ошибка создания окна
     */
    public static boolean showEditDialog(Book book) throws IOException {
        try {
            URL resource = Window.class.getResource("/dialog.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
