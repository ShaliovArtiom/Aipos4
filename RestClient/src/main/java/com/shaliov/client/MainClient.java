package com.shaliov.client;

import com.shaliov.client.view.Window;

/**
 * @author ShaliovArtiom, TruntsVitalij
 */
public class MainClient {
    /**
     * �����, ������������ ������� ����
     * @param argc ������ �����, ���������� �� ���� ���������
     */
    public static void main(String[] argc) {
        Window window = new Window();
        window.bind();
    }
}
