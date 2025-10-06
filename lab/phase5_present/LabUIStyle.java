package phase5_present;

import javax.swing.*;
import java.awt.*;

public class LabUIStyle {
    public static final Color CONTROL_BG = new Color(245, 245, 250);
    public static final Color TEXT_COLOR = new Color(33, 33, 33);
    public static final Color PRIMARY_BLUE = new Color(90, 120, 255);
    public static final Color FOCUS_BLUE = new Color(100, 150, 255);

    public static void applyGlobalStyle() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        UIManager.put("control", CONTROL_BG);
        UIManager.put("text", TEXT_COLOR);
        UIManager.put("nimbusBase", PRIMARY_BLUE);
        UIManager.put("nimbusBlueGrey", new Color(200, 200, 210));
        UIManager.put("nimbusFocus", FOCUS_BLUE);
        UIManager.put("nimbusSelectionBackground", PRIMARY_BLUE);
    }

    public static Font mainFont(int size) {
        return new Font("Segoe UI", Font.PLAIN, size);
    }

    public static Font titleFont(int size) {
        return new Font("Segoe UI Semibold", Font.PLAIN, size);
    }

    public static void styleButton(JButton button) {
        button.setBackground(CONTROL_BG);
        button.setForeground(TEXT_COLOR);
        button.setFont(mainFont(14));
        button.setFocusPainted(false);
    }

    public static void applyDarkTheme() {
        UIManager.put("control", new Color(40, 40, 50));
        UIManager.put("text", new Color(220, 220, 220));
        UIManager.put("nimbusBase", new Color(60, 90, 180));
        UIManager.put("nimbusBlueGrey", new Color(70, 70, 80));
        UIManager.put("nimbusFocus", new Color(80, 120, 200));
        UIManager.put("nimbusSelectionBackground", new Color(60, 90, 180));
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(CONTROL_BG);
    }
}