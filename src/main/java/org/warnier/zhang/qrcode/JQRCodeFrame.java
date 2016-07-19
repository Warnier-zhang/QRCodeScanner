package org.warnier.zhang.qrcode;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JQRCodeFrame extends JFrame {
    private Webcam camera;
    // Replace JTextField with JButton instance.
    private JButton textField;

    public JQRCodeFrame() {
        this("QR Scanner");
    }

    public JQRCodeFrame(String title) {
        super(title);
        init();
        add(makeQRUi());
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel makeQRUi() {
        JPanel container = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(container);
        textField = new JButton();
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setText("等待扫描中...");
        layout.row().grid().add(makeCamera());
        layout.row().grid().add(textField, 2).add(makeButton("关闭"));
        return container;
    }

    private JPanel makeCamera() {
        camera = Webcam.getWebcams().get(0);
        camera.setViewSize(WebcamResolution.QVGA.getSize());
        JPanel cameraContainer = new WebcamPanel(camera);
        return cameraContainer;
    }

    private JButton makeButton(String title) {
        JButton button = new JButton(title);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return button;
    }

    public void updateText(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textField.setText(text);
            }
        });
    }
}

