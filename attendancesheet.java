import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AttendanceSheet {

    public static void main(String[] args) {

        // Create main JFrame
        JFrame frame = new JFrame("Attendance Sheet");
        frame.setSize(950, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with GridLayout: 6 rows, 7 columns (Name In, Course, Time In, Name Out, Time Out, Signature, Clear)
        JPanel panel = new JPanel(new GridLayout(6, 7, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add headers
        panel.add(new JLabel("Name (Time In)"));
        panel.add(new JLabel("Course / Year"));
        panel.add(new JLabel("Time In"));
        panel.add(new JLabel("Name (Time Out)"));
        panel.add(new JLabel("Time Out"));
        panel.add(new JLabel("Signature"));
        panel.add(new JLabel("Clear"));

        // Formatter for showing time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

        // Loop to create 5 student entries
        for (int i = 0; i < 5; i++) {

            // Create text fields for attendance
            JTextField nameInField = new JTextField();
            JTextField courseField = new JTextField();
            JTextField timeInField = new JTextField();
            JTextField nameOutField = new JTextField();
            JTextField timeOutField = new JTextField();

            // Create signature panel and clear button
            SignaturePanel signaturePanel = new SignaturePanel();
            JButton clearBtn = new JButton("Clear");

            // Time fields should not be manually editable
            timeInField.setEditable(false);
            timeOutField.setEditable(false);

            // Document listener to auto-fill Time In when name and course are entered
            DocumentListener timeInListener = new DocumentListener() {
                public void insertUpdate(DocumentEvent e) { setTimeIn(); }
                public void removeUpdate(DocumentEvent e) {}
                public void changedUpdate(DocumentEvent e) {}

                private void setTimeIn() {
                    if (!nameInField.getText().isEmpty()
                            && !courseField.getText().isEmpty()
                            && timeInField.getText().isEmpty()) {
                        timeInField.setText(LocalTime.now().format(timeFormat));
                    }
                }
            };

            // Document listener to auto-fill Time Out when Name Out is entered
            DocumentListener timeOutListener = new DocumentListener() {
                public void insertUpdate(DocumentEvent e) { setTimeOut(); }
                public void removeUpdate(DocumentEvent e) {}
                public void changedUpdate(DocumentEvent e) {}

                private void setTimeOut() {
                    if (!nameOutField.getText().isEmpty()
                            && timeOutField.getText().isEmpty()) {
                        timeOutField.setText(LocalTime.now().format(timeFormat));
                    }
                }
            };

            // Clear button clears the signature drawing
            clearBtn.addActionListener(e -> signaturePanel.clear());

            // Add document listeners to fields
            nameInField.getDocument().addDocumentListener(timeInListener);
            courseField.getDocument().addDocumentListener(timeInListener);
            nameOutField.getDocument().addDocumentListener(timeOutListener);

            // Add all components to panel
            panel.add(nameInField);
            panel.add(courseField);
            panel.add(timeInField);
            panel.add(nameOutField);
            panel.add(timeOutField);
            panel.add(signaturePanel);
            panel.add(clearBtn);
        }

        // Add panel to frame
        frame.add(panel);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}

// Custom panel class for drawing signature
class SignaturePanel extends JPanel {

    private Image image;
    private Graphics2D g2;
    private int x, y;

    public SignaturePanel() {
        setPreferredSize(new Dimension(120, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Capture mouse pressed for starting point
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

        // Capture mouse drag for drawing lines
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (g2 != null) {
                    g2.drawLine(x, y, e.getX(), e.getY());
                    repaint();
                    x = e.getX();
                    y = e.getY();
                }
            }
        });
    }

    // Method to clear the signature
    public void clear() {
        if (g2 != null) {
            g2.clearRect(0, 0, getWidth(), getHeight());
            repaint();
        }
    }

    // Paint component to initialize drawing area
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            image = createImage(getWidth(), getHeight());
            g2 = (Graphics2D) image.getGraphics();
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        g.drawImage(image, 0, 0, null);
    }
}
Write to FAMILY YOUTH
