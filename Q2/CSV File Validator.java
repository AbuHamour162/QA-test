import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CSVValidator {

    private static final String EXPECTED_COLUMN_1 = "name";
    private static final String EXPECTED_COLUMN_2 = "id";

    public static void main(String[] args) {
        String directoryPath = "/path/to/directory";
        File directory = new File(directoryPath);

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".csv")) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine();
                    if (line == null || !line.contains(EXPECTED_COLUMN_1) || !line.contains(EXPECTED_COLUMN_2)) {
                        System.out.println(file.getName() + " The column <columnName> of the file is not one of the expected columns");
                        continue;
                    }

                    Set<String> nameSet = new HashSet<>();
                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split(",");
                        if (fields.length < 2) {
                            System.out.println(file.getName() + " The file is not in the form of a coherent CSV table.");
                            break;
                        }

                        String name = fields[0];
                        String id = fields[1];
                        if (name.trim().isEmpty()) {
                            System.out.println(file.getName() + " The expected <columnName> column is not in the file.");
                            break;
                        }

                        if (!name.matches("[a-zA-Z]+")) {
                            System.out.println(file.getName() + " The data type of the : <" + name + "> column is not what is expected ");
                            break;
                        }

                        if (!nameSet.add(name)) {
                            System.out.println(file.getName() + " The : < "+ name + ">column is duplicated. ");
                            break;
                        }

                        Set<String> idSet = new HashSet<>();
                        if (!idSet.add(id)) {
                            System.out.println(file.getName() + " The id :<" + id +" >is not unique ." );
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
