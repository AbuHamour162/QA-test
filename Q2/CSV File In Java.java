import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class CsvWriter {

    public static void main(String[] args) {

        String csvFile = "data.csv";
        String csvDelimiter = ",";
        FileWriter writer = null;

        try {
            writer = new FileWriter(csvFile);

            // Write the header row
            writer.append("id");
            writer.append(csvDelimiter);
            writer.append("name");
            writer.append(csvDelimiter);
            writer.append("weight");
            writer.append(csvDelimiter);
            writer.append("gender");
            writer.append(csvDelimiter);
            writer.append("country");
            writer.append(csvDelimiter);
            writer.append("isQA");
            writer.append("\n");

            // Write the data rows
            int id = 100;
            String name = "Mohammad Abu Hamour";
            double weight = 55.7;
            char gender = 'M';
            String country = "Jordan";
            boolean isQA = true;

            // Validate and write the values for each column
            if (id >= 50 && id <= 1999) {
                writer.append(Integer.toString(id));
            }
            writer.append(csvDelimiter);

            if (name.length() <= 150) {
                writer.append(name);
            }
            writer.append(csvDelimiter);

            if (Pattern.matches("\\d+,\\d+", Double.toString(weight))) {
                writer.append(Double.toString(weight));
            }
            writer.append(csvDelimiter);

            if (gender == 'M' || gender == 'H' || gender == 'F') {
                writer.append(gender);
            }
            writer.append(csvDelimiter);

            if (Pattern.matches("[a-zA-Z]+", country)) {
                writer.append(country);
            }
            writer.append(csvDelimiter);

            if (isQA) {
                writer.append("yes"||"0");
            } else {
                writer.append("no"||"1");
            }

            writer.append("\n");

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
