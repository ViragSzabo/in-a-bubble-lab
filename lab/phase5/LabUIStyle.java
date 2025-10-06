package phase5;

import javax.swing.*;
import java.awt.*;

public class LabUIStyle {
    public static void applyGlobalStyle() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        UIManager.put("control", new Color(245, 245, 250));
        UIManager.put("text", new Color(33, 33, 33));
        UIManager.put("nimbusBase", new Color(90, 120, 255));
        UIManager.put("nimbusBlueGrey", new Color(200, 200, 210));
        UIManager.put("nimbusFocus", new Color(100, 150, 255));
        UIManager.put("nimbusSelectionBackground", new Color(120, 150, 255));
    }

    public static Font mainFont() {
        return new Font("Segoe UI", Font.PLAIN, 14);
    }

    public static Font titleFont() {
        return new Font("Segoe UI Semibold", Font.PLAIN, 16);
    }
}