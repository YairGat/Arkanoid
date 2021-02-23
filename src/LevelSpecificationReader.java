import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelSpecificationReader {

    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> lst = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    lst.add(new Level(bufferedReader));
                }
            }
        } catch (IOException e) {
            System.out.println("unsuccessfully reading from the file.");
            System.exit(1);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("unsuccessfully closing the file.");
                System.exit(1);
            }
        }
        return lst;
    }
}