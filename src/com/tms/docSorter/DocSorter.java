package com.tms.docSorter;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DocSorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String path = scanner.next();
           // String path = ("D:\\TeachMeSkills\\TMS_Lesson_7_HW\\docNumberList.txt");

            try (FileReader fis = new FileReader(path);
                 FileWriter fosDoc = new FileWriter("D:\\TeachMeSkills\\TMS_Lesson_7_HW\\docNum.txt");
                 FileWriter fosDocErr = new FileWriter("D:\\TeachMeSkills\\TMS_Lesson_7_HW\\docNumErr.txt");
                 BufferedReader reader = new BufferedReader(fis)) {
                String line = reader.readLine();

                while (line != null) {
                    String status = "\n";
                    if (line.length() != 15) {
                        status = "\nДлина документа не соответствует регламенту.\n";
                        fosDocErr.write(line + status);
                        line = reader.readLine();
                        continue;

                    } else if (checkStringStart(line, "docnum") || checkStringStart(line, "kontrakt")) {
                        fosDoc.write(line + status);

                    } else {
                        status = "\nФормат документа не соответствует регламенту.\n";
                        fosDocErr.write(line + status);
                    }
                    line = reader.readLine();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (InputMismatchException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    /**
     * Метод позволяет проверить, является ли заданная последовательность символов началом строки
     *
     * @param str          - строка, принимаемая на вход
     * @param charSequence - последовательность символов, принимаемая на вход
     * @return возвращает строку
     */
    public static boolean checkStringStart(String str, String charSequence) {
        String result = "";
        if (isValid(str)) {
            charSequence = charSequence.toLowerCase();
            str = str.toLowerCase();
            if (str.startsWith(charSequence)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Данный метод проверяет, корректно ли введена строка
     *
     * @param str - принимает введенную строку
     * @return - возвращает булево значние
     */
    private static boolean isValid(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        } else {
            System.out.print("Введенное значение не корректно. Строка пуста или null");
            return false;
        }
    }

}

