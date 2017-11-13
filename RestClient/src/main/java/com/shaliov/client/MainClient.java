package com.shaliov.client;

import com.shaliov.client.view.Window;

/**
 * @author ShaliovArtiom, TruntsVitalij
 */
public class MainClient {
    /**
     * Метод, генерирующая главное окно
     * @param argc массив строк, переданный на вход программе
     */
    public static void main(String[] argc) {
        Window window = new Window();
        window.bind();
    }
}
