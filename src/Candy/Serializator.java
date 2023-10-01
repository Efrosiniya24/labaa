package Candy;

import java.io.*;
import java.util.ArrayList;

public class Serializator {
    public static void serialization(ArrayList menu) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream("Menu");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(menu);
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
    public static ArrayList deserialization()  throws IOException, ClassNotFoundException {
        try(FileInputStream fileInputStream = new FileInputStream("Menu");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            return     (ArrayList) objectInputStream.readObject();
        }
    }
}

