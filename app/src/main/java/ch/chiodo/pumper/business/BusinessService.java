package ch.chiodo.pumper.business;

import java.util.List;

public interface BusinessService<T> {
    void insert(T object, Callback<T> callback);
    void get(long id, Callback<T> callback);
    void getList(Callback<List<T>> callback);
    void update(T object, Callback<T> callback);
    void delete(T object, Callback<T> callback);
}
