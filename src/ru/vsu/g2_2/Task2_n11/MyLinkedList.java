package ru.vsu.g2_2.Task2_n11;

import java.util.Iterator;

public class MyLinkedList<T> {


    public class Node{
        T value;
        Node next;

        public Node(T value, Node next){
            this.value =  value;
            this.next = next;
        }

        public Node(T value){
            this.value = value;
        }

    }

    private Node head;
    private Node tail;
    private int size;

    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){ return size;}
    public Node getHead(){ return head;}

//    public String asString(LinkedList list) throws LinkedListException{
//        String res = "{";
//        if (list.size != 0){
//            res += (list.get(0));
//        }
//
//        // Зависимость по квадратичному закону
//        for (int i = 1; i < list.size; i++){
//            res += ", " + list.get(i);
//
//        }
//        res += "}";
//        return res;
//    }

//    public String asString(LinkedList<T> lst) throws LinkedListException{
//        StringBuilder res = new StringBuilder("{");
//
//        if (isEmpty()){
//            throw new LinkedListException("List is empty");
//        }
//
//        Iterator<T> iterator = lst.iterator();
//
//        while (iterator.hasNext()){
//            res.append(iterator.next());
//            if (iterator.hasNext()){
//                res.append(",");
//            } else{
//                res.append("}");
//            }
//        }
//
//        return res.toString();
//    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{");
        Node curr = head;

        while (curr != null) {
            res.append(curr.value);

            if (curr.next != null) {
                res.append(", ");
            }

            curr = curr.next;
        }

        res.append("}");
        return res.toString();
    }

    public void addFirst(T value){
        Node first = new Node(value);

        if (size == 0){
            head = first;
            tail = first;
        } else{
            first.next = head;
            head = first;
        }
        size++;
    }

    public void addLast(T value){
        Node last = new Node(value);

        if (size == 0){
            head = last;
        } else{
            tail.next = last;
        }
        tail = last;
        size++;
    }

    private Node getNode(int index) throws MyLinkedListException {
        if (index < 0 || index >= size){
            throw new MyLinkedListException("Index out of bounds");
        }

        Node t = head;
        for (int i = 0; i < index; i++){
            t = t.next;
        }

        return t;
    }

    public T get(int index) throws MyLinkedListException {
        return getNode(index).value;
    }

    public void set(int index, T value) throws MyLinkedListException {
        getNode(index).value = value;
    }

    public void insert (int index, T value) throws MyLinkedListException {
        if (index == 0){
            addFirst(value);
        } else if (index == size){
            addLast(value);
        } else{
            Node n = new Node(value);
            Node prev = getNode(index - 1);

            n.next = prev.next;
            prev.next = n;

            size++;
        }
    }

    public T removeFirst() throws MyLinkedListException {
        if (isEmpty()){
            throw new MyLinkedListException("List is empty");
        }
        Node resDelete = head;
        head = head.next;
        size--;

        if (isEmpty()){
            tail = null;
        }

        return resDelete.value;

    }

    public T removeLast() throws MyLinkedListException {
        if (isEmpty()){
            throw  new MyLinkedListException("List is empty");
        }

        Node prev = getNode(size - 2);
        Node resDelete = prev.next;
        prev.next = null;
        tail = prev;
        size--;

        return resDelete.value;
    }



    public T remove(int index) throws MyLinkedListException {
        if (isEmpty()){
            throw new MyLinkedListException("List is empty");
        }

        if (index < 0 || index > size){
            throw new MyLinkedListException("Index out of bounds");
        }

        if (index == size - 1){
           return removeLast();
        }

        if (index == 0){
            return removeFirst();
        }

        Node prev = getNode(index - 1);
        Node resDelete = prev.next;
        prev.next = prev.next.next;
        size--;

        return resDelete.value;

    }

    public int[] asArray(){
        int[] arr = new int[this.size];
        Node t = head;

        for (int i = 0; i < size; i++){
            arr[i] = (int)t.value;
            t = t.next;
        }

        return arr;
    }

    public static MyLinkedList<Integer> asLinkedList(int[] arr) throws Exception {
        if (arr.length == 0){
            throw new Exception("Array is empty");
        }
        MyLinkedList<Integer> a = new MyLinkedList<>();

        for (int i = 0; i < arr.length; i++){
            a.addLast(arr[i]);
        }

        return a;
    }

    public void solve() throws MyLinkedListException {
        if (isEmpty()) {
            throw new MyLinkedListException("List is empty");
        }
        
        if (size == 1) {
            
            return;
        }
        
        Node current = head;
        
       
        while (current != null && current.next != null) {
            
            if (current.value instanceof Number && current.next.value instanceof Number) {
                
                Integer sum = ((Number) current.value).intValue() + ((Number) current.next.value).intValue();
                current.value = (T) sum;
                
                
                current.next = current.next.next;
                
                
                if (current.next == null) {
                    tail = current;
                }
                
                
                size--;
            }
            
            
            current = current.next;
        }
    }



}
