import java.io.IOException;

public interface Task<T> {
    T run() throws IOException;
}