package com.vlados.inc.vlados;

import java.io.*;
import java.util.*;

public class VladOSCore {

    private final File storageDir = new File("vladOS_storage");
    private final File usersDir = new File(storageDir, "users");
    private final File appsDir = new File(storageDir, "apps");
    private final File settingsDir = new File(storageDir, "settings");
    private final File logsDir = new File(storageDir, "logs");
    private final File hardDisk = new File(storageDir, "hard_disk");

    public int VladOSSetup() {
        // Создаем необходимые директории, если их нет
        storageDir.mkdir();
        usersDir.mkdir();
        appsDir.mkdir();
        settingsDir.mkdir();
        logsDir.mkdir();
        hardDisk.mkdir();

        // Запрашиваем имя пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scrivi nome utente: ");

        String username = scanner.nextLine();
        System.out.println("Scrivi l'accesso di utente user/admin: ");
        String role = scanner.nextLine();
        // Закрытие сканера после того, как все данные введены
        scanner.close();

        // Формируем имя файла для пользователя
        String userFileName = "userFile_" + username + ".vusr";
    

        // Создаем файл для пользователя, если он не существует
        final File userFile = new File(usersDir, userFileName);
        try {
            if (!userFile.exists()) {
                // Попытка создать новый файл
                userFile.createNewFile();
                System.out.println("File creato con successo: " + userFile.getName());
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
                    // Запись текста в файл
                    writer.write("role: " + role);
                    writer.newLine();  // Добавляет новую строку в файл
                    writer.write("username: " + username);
                    System.out.println("Данные успешно записаны в файл.");
                } catch (IOException e) {
                    // Обработка ошибок
                    System.out.println("Ошибка при записи в файл: " + e.getMessage());
                }
            } else {
                System.out.println("File già esiste");
            }
        } catch (IOException e) {
            System.out.println("Errore durante la creazione del file: " + e.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            
            // Ищем значения по ключу "role" и "username"
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("role:")) {
                    String role1 = line.split(":")[1].trim(); // Получаем значение после "role:"
                    System.out.println("Role: " + role1);
                } else if (line.startsWith("username:")) {
                    String username2 = line.split(":")[1].trim(); // Получаем значение после "username:"
                    System.out.println("Username: " + username2);
                } else if (line.startsWith("theme:")) {
                    String theme1 = line.split(":")[1].trim();
                    System.out.println("Theme: " + theme1);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }



        initializeVladOS(settingsDir, username, hardDisk, logsDir, appsDir);


    














        return 0;
    }
    private int initializeVladOS(File settingsDir, String username, File hard_disk, File logDir, File appsDir) {
        String configFileName = "config_" + username + ".vset";
        File configFile = new File(settingsDir, configFileName);
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write("username: " + username);
            writer.newLine();
            writer.write("theme: light");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File imagesDir = new File(hard_disk, "Images");
        imagesDir.mkdir();
        File documentsDir = new File(hard_disk, "Documents");
        documentsDir.mkdir();
        File videosDir = new File(hard_disk, "Videos");
        videosDir.mkdir();
        File usersDir = new File(hard_disk, "users");
        usersDir.mkdir();
        File userDir = new File(usersDir, username);
        userDir.mkdir();
        File userDownloadsDir = new File(userDir, "Downloads");
        userDownloadsDir.mkdir();
        File userVideosDir = new File(userDir, "Videos");
        userVideosDir.mkdir();
        File userImagesDir = new File(userDir, "Images");
        userImagesDir.mkdir();
        File userDocumentsDir = new File(userDir, "Documents");
        userDocumentsDir.mkdir();
        File userTrashDir = new File(userDir, "Trash");
        userTrashDir.mkdir();
        
        return 0;   
    }

}
