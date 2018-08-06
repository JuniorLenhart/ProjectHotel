package hotel.persistencia;

import java.util.ArrayList;

public interface DAO<T> {

    public String insert(T pT);
    public String update(T pT);
    public String delete(int pCodigo);
    public ArrayList<T> readAll();
    public ArrayList<T> read(String pParam);
    public T readId(int pCodigo);
    
}
