package ru.vsu.g2_2.Task2_n11;

import ru.vsu.g2_2.Task2_n11.utils.JTableUtils;
import ru.vsu.g2_2.Task2_n11.utils.SwingUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.vsu.g2_2.Task2_n11.Main.fillIncrease;

public class FrameMain extends JFrame {
    private JTable listInput;
    private JTable listOutput;
    private JButton removeFibIndex;
    private JPanel panelMain;
    private JButton fillRandomButton;

    public FrameMain() {
        setTitle("Задача №13 - Объединение пар элементов списка");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        
        // Создаем таблицы
        listInput = new JTable();
        listOutput = new JTable();
        
        JTableUtils.initJTableForArray(listInput, 40, true, true, false, true);
        JTableUtils.initJTableForArray(listOutput, 40, true, true, false, true);
        
        listInput.setRowHeight(40);
        listOutput.setRowHeight(40);
        
        
        JScrollPane scrollPaneInput = new JScrollPane(listInput);
        JScrollPane scrollPaneOutput = new JScrollPane(listOutput);
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        
        fillRandomButton = new JButton("Заполнить случайными числами");
        removeFibIndex = new JButton("Объединить пары элементов");
        
        buttonPanel.add(fillRandomButton);
        buttonPanel.add(removeFibIndex);
        
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 0, 10));
        centerPanel.add(scrollPaneInput);
        centerPanel.add(scrollPaneOutput);
        
        panelMain.add(centerPanel, BorderLayout.CENTER);
        panelMain.add(buttonPanel, BorderLayout.SOUTH);
        
        setContentPane(panelMain);
        
        
        MyLinkedList<Integer> a = fillIncrease(10);
        JTableUtils.writeArrayToJTable(listInput, a.asArray());
        
        
        fillRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MyLinkedList<Integer> randomList = fillIncrease(10);
                    JTableUtils.writeArrayToJTable(listInput, randomList.asArray());
                    
                    JTableUtils.writeArrayToJTable(listOutput, new int[0]);
                } catch (Exception ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }
            }
        });

        removeFibIndex.addActionListener(new ActionListener() {
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
}
