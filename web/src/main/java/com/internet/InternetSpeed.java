package com.internet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
/**
 * @Description  网络测速
 * @Author xuedong.wang
 * @Date 17/6/7.
 */
public class InternetSpeed {


    public static void main(String[] args) {
        try {

            JFrame frame = new JFrame();

            JTextPane text = new JTextPane();


            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(new JScrollPane(text));
            frame.setTitle("网速测试");
            frame.setSize(800, 600);
            frame.setVisible(true);

            String[] cmd = new String[]{"cmd.exe","/c","ping www.baidu.com -t"};
            Process process = Runtime.getRuntime().exec( cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String info = "";

            DefaultStyledDocument doc = (DefaultStyledDocument)text.getStyledDocument();
            MutableAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setForeground(attr,new Color(0,102,0));



            while((info = br.readLine()) != null){
                if(!"".equals(info)){
                    try {
                        doc.insertString(doc.getLength(), info, attr);
                        doc.insertString(doc.getLength(), "\r\n", null);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    text.setCaretPosition(doc.getLength());
                }
            }

        } catch (Exception e) {

        }

    }

}
