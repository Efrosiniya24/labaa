import Candy.Menu;
import java.io.*;

public class Serializator {
    public void serialization(Menu menu) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream("Menu");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(menu);
            System.out.println("Данные записаны в файл");
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
    public Menu deserialization()  throws IOException, ClassNotFoundException {
        try(FileInputStream fileInputStream = new FileInputStream("Menu");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            return     (Menu) objectInputStream.readObject();
        }
    }
}

