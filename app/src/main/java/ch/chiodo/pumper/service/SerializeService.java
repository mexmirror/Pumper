package ch.chiodo.pumper.service;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeService {
    public static <T extends Serializable> void saveObject(T object,FileOutputStream fileOutputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public static <T extends Serializable> void loadObject(T object, FileInputStream fileInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        object = (T)objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
}
