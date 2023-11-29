/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author acer
 */
public class GetFileName {

    public String getFileName(String file) {
        int cnt = 0;
        for (int i = 0; i < file.length(); i++) {
            if (file.charAt(i) == '\\') {
                cnt = i;
            }
        }
        return file.substring(cnt + 1);
    }
}
