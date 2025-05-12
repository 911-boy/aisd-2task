package ru.vsu.g2_1.Task2_n13;

import java.util.Random;

public class Main {
    private static final Random rnd = new Random();
    public static MyLinkedList<Integer> fillIncrease(int n){
        MyLinkedList<Integer> res = new MyLinkedList<>();
        int prev  = 0;
        for (int i = 0; i < n; i++){
            prev += rnd.nextInt(100);
            res.addFirst(prev);
        }
        return res;
    }
    public static void main(String[] args) throws MyLinkedListException {
//        MyLinkedList<Integer> a = fillIncrease(14);
//        System.out.println(a);

        FrameMain frame = new FrameMain();
        frame.setSize(500, 500);
        frame.setVisible(true);




    }
}