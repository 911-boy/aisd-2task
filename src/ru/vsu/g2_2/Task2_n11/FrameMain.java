package ru.vsu.g2_2.Task2_n11;

import ru.vsu.g2_2.Task2_n11.utils.JTableUtils;
import ru.vsu.g2_2.Task2_n11.utils.SwingUtils;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static ru.vsu.g2_2.Task2_n11.Main.fillIncrease;

public class FrameMain extends JFrame{
    private JTable listInput;
    private JTable listOutput;
    private JButton sumPairsButton;
    private JPanel panelMain;


    public FrameMain(){
        // создаем компоненты вручную
        createUIComponents();
        
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(listInput, 40, true, true, false, true);
        JTableUtils.initJTableForArray(listOutput, 40, true, true, false, true);

        listInput.setRowHeight(40);
        listOutput.setRowHeight(40);

        MyLinkedList<Integer> a = fillIncrease(10);
        TableModel tIn = listInput.getModel();

        JTableUtils.writeArrayToJTable(listInput, a.asArray());
        this.pack();

        sumPairsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] array = JTableUtils.readIntArrayFromJTable(listInput);
                    MyLinkedList<Integer> a = MyLinkedList.asLinkedList(array);
                    a.solve();
                    int[] arrOutput = a.asArray();
                    JTableUtils.writeArrayToJTable(listOutput, arrOutput);
                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }
            }
        });
    }
    
    
    private void createUIComponents() {
        
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(10, 10));
        
        
        listInput = new JTable();
        listOutput = new JTable();
        sumPairsButton = new JButton("Объединить пары элементов");
        
        
        JScrollPane scrollPaneInput = new JScrollPane(listInput);
        JScrollPane scrollPaneOutput = new JScrollPane(listOutput);
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(sumPairsButton);
        
        
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(2, 1, 0, 10));
        tablesPanel.add(scrollPaneInput);
        tablesPanel.add(scrollPaneOutput);
        
        
        panelMain.add(tablesPanel, BorderLayout.CENTER);
        panelMain.add(buttonPanel, BorderLayout.SOUTH);
        
        
        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
