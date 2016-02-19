package ch.chiodo.pumper.service;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeService {
    public static <T extends Serializable> void saveObject(T object, String filename, Context context) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public static <T extends Serializable> T loadObject(String filename, Context context) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = context.openFileInput(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T object = (T)objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return object;
    }
}
