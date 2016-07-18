package org.warnier.zhang.qrcode;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JQRCodeFrame extends JFrame {
    private Webcam webcam;
    private JTextArea textArea;

    public JQRCodeFrame() {
        super();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(WebcamResolution.QVGA.getSize());
        JPanel cameraContainer = new WebcamPanel(webcam);
        add(cameraContainer, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setSize(400, 400);
        add(textArea, BorderLayout.CENTER);

        JButton button = new JButton("关闭摄像头");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(button, BorderLayout.SOUTH);
    }

    public void setText(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.setText(text);
            }
        });
    }
}

