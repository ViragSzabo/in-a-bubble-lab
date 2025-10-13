package phase4_subjects;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DatasetManagement extends Component {

    /**
     * Load dataset from a file line by line.
     * @param file CSV, TXT, JSON
     * @return List of lines
     */
    public List<String> loadDataset(File file)
    {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to read file:\n" + e.getMessage(),
                    "File Read Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
}