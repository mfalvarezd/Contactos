/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.utilidades;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author ander
 */
public class DoubleCircularLinkedList<E> implements List<E>{
    
    private Node<E> last;

    public DoubleCircularLinkedList() {
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
    public boolean addLast(E element) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            Node<E> cursor = last;
            
            @Override
            public boolean hasNext() {
                
                System.out.println("dentro del has"+cursor.getElement());
                return cursor!=null;
            }

            @Override
            public E next() {
                E elemento = cursor.getElement();
                System.out.println("DENTRO DEL NEXT"+elemento);
                cursor = cursor.getNext();
                System.out.println("Luego del ++"+cursor.getElement());
                
                return elemento;
            }
        };
        return it;
    }
    
    public String toString() {
        String linea = "";
        if (!this.isEmpty()) {
            Iterator<E> it = this.iterator();
            while (it.hasNext()) {
                linea+=it.next()+", ";            
            }               
        }
        /*
            if(!this.isEmpty()) {
                Node<E> n = this.getLast();
                n = n.getNext();
                linea+=n.getElement()+", ";                
        
            while(n!=this.getLast()) {
                n=n.getNext();
                linea+=n.getElement()+", ";
            }        
            return linea;  
        }
        */
        return linea;        
    }
}
