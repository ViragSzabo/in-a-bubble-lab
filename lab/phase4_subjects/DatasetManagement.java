package phase4_subjects;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatasetManagement extends Component {
    public List<String> loadDataset(File file)
    {
        List<String> dataset = new ArrayList<String>();
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                dataset.add(scanner.nextLine());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to read file: " + e.getMessage());
        }
        return dataset;
    }
}