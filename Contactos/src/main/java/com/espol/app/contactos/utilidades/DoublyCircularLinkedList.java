/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.utilidades;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author carfgonz
 * @param <E>
 */
public class DoublyCircularLinkedList<E> implements List<E>{
    
    private Node<E> last;

    public DoublyCircularLinkedList() {
        this.last = null;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    @Override
    public int size() {
        int count = 0;
        if (!this.isEmpty()) {
            Node<E> t = this.getLast();
            count++;
            t = t.getNext();
            
            for (Node<E> n =t; n!=this.getLast(); n=n.getNext()){
                count++;
            }            
            return count;
        }        
        return count;
    }

    @Override
    public boolean isEmpty() {
        return this.getLast() == null;
    }

    @Override
    public void clear() {
        while(!this.isEmpty()){
            this.removeFirst();
        }
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> node = new Node<E>(element);
        if (this.isEmpty()){
            this.last = node;
            return true;
        } else {
            node.setNext(this.getLast());
            node.setPrevious(this.getLast());
            this.last.getNext().setPrevious(node);
            node.setNext(this.last.getNext());
            this.last.setNext(node);
            return true;
        }                
    }

    @Override
    public boolean add(E element) {
        Node<E> node = new Node<E>(element);
        if (this.isEmpty()) {
            this.last = node;
            return true;
        } else {
            node.setNext(this.getLast().getNext());
            this.last.getNext().setPrevious(node);
            this.last.setNext(node);
            node.setPrevious(this.getLast());
            this.last = node;
            return true;
        }
    }

    @Override
    public E removeFirst() {
        Node<E> node = new Node<E>();
        if (!this.isEmpty()) {
            node = this.getLast().getNext();
            if (this.last.getNext() == this.last) {
                this.last = null;
                return node.getElement();
            } else {                
                this.last.getNext().getNext().setPrevious(this.getLast());
                //this.last.getNext().setNext(null);
                //this.last.getNext().setPrevious(null);
                this.last.setNext(this.getLast().getNext().getNext());
                return node.getElement();
                }
        }
        return node.getElement();
    }

    @Override
    public E removeLast() {
        Node<E> node = new Node<E>();
        if (!this.isEmpty()) {
            node = this.getLast();
            if (node.getNext() == this.last) {                
                this.last = null;
                return node.getElement();
            } else {
                this.last.getNext().setPrevious(this.getLast().getPrevious());
                this.last.getPrevious().setNext(this.getLast().getNext());
                this.last = this.getLast().getPrevious();                        
                return this.last.getElement();
            }
        }
        return node.getElement();
    }

    @Override
    public boolean add(int index, E element) {
        Node<E> newNode = new Node(element);
        Node<E> cursor = this.last;
        
        if (index>this.size()-1) {
            return false;
        }
        
        if (element == null) {
            return false;
        }
        
        if (index == 0) {            
            return this.addFirst(element);
        }
        
        if (index == this.size()-1) {            
            return this.add(element);
        }

        for (int i=0; i<index; i++) {
            cursor = cursor.getNext();
        }
        
        newNode.setPrevious(cursor);
        newNode.setNext(cursor.getNext());
        cursor.getNext().getNext().setPrevious(newNode);
        cursor.setNext(newNode);        
        
        return true;                
    }

    @Override
    public E remove(int index) {
        Node<E> cursor = this.last;
        E element = null;
        
        if (index == 0) {
            element = this.removeFirst();
            return element;
        }
        
        if (index == this.size()-1) {
            element = this.removeLast();
            return element;
        }
        
        if (index>this.size()-1) {
            return element;
        }
        
        for (int i=0; i<index; i++) {
            cursor = cursor.getNext();
        }
        element = cursor.getNext().getElement();
        cursor.getNext().getNext().setPrevious(cursor);
        cursor.setNext(cursor.getNext().getNext());        
        return element;
        
    }

    @Override
    public E get(int index) {        
        Node<E> cursor = last.getNext();
        
        if (index>this.size()-1) {
            return null;
        }
        
        for (int i=0; i<index; i++) {
            cursor = cursor.getNext();
        }
        
        return cursor.getElement();        
    }

    @Override
    public E set(int index, E element) {
        Node<E> cursor = last.getNext();
        
        if (index>this.size()-1) {
            return null;
        }
        
        for (int i=0; i<index; i++) {
            cursor = cursor.getNext();
        }
        E elementOld = cursor.getElement();
        cursor.setElement(element);
        return elementOld;
    }

    @Override
    public void reverse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> removeFirstNElements(int n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> findIntersection(List<E> anotherList, Comparator<E> cmp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            int count = 0;
            Node<E> cursor = last.getNext();
            @Override
            public boolean hasNext() {                             
                return count<size();
            }

            @Override
            public E next() {                
                E elemento = cursor.getElement();
                cursor = cursor.getNext();
                count++;
                return elemento;
            }
        };
        return it;
    }
    
    public String toString() {
        String linea = "[";
        if (!this.isEmpty()) {
            Iterator<E> it = this.iterator();            
            while (it.hasNext()) {
                linea+=it.next()+", ";            
            }
            linea = linea.substring(0, linea.length()-2);
            linea+="]";
        }
        return linea;        
    }
}
