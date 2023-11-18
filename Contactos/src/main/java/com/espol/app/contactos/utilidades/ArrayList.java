/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.utilidades;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author carfgonz
 * @param <E>
 */
public class ArrayList<E> implements List<E>, Serializable {
    private E[] elements;
    private int CAPACITY = 100;
    private int effectiveSize;

    public ArrayList() {
        elements = (E[]) new Object[CAPACITY];
        effectiveSize = 0;
    }
    
    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        E[] newElements = (E[]) new Object[CAPACITY];
        elements = newElements;
        effectiveSize = 0;          
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (this.isEmpty()) {
            elements[effectiveSize++] = element;            
            return true;
        } else if (this.isFull()) {
            this.addCapacity();
        }
        
        //bit shifting
        for (int i=effectiveSize-1; i>=0; i--){
            elements[i+1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;        
        return true;   
    }
    
    private void addCapacity() {
        CAPACITY*=2;
        E[] newElements = (E[])new Object[CAPACITY];
        
        for (int i=0; i<elements.length; i++) {
            newElements[i] = elements[i];
        }        
        elements = newElements;        
    }
    
    private boolean isFull() {
        return effectiveSize == CAPACITY;
    }    

    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        } else if (this.isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (this.isFull()) {
            this.addCapacity();
        }
        
        elements[effectiveSize] = element;
        effectiveSize++;
        return true;        
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        E element = elements[0];
        for (int i=0; i<effectiveSize-1; i++) {
            elements[i] = elements[i+1];
        }
        effectiveSize--;
        return element;        
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        E element = elements[effectiveSize-1];
        E[] newElements = (E[]) new Object[CAPACITY];
        
        for (int i=0; i<effectiveSize-1; i++){
            newElements[i] = elements[i];
        }
        
        elements = newElements;
        effectiveSize--;
        return element;        
    }

    @Override
    public boolean add(int index, E element) {
        if (element == null) {
            System.out.println("No es posible agregar el elemento");
            return false;
        } else if (this.isEmpty()) {
            return false;
        } else if (index>effectiveSize) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (this.isFull()){      
            this.addCapacity();
        }
        for (int i=effectiveSize-1; i>=index; i--) {
            elements[i+1]=elements[i];
        }
        elements[index] = element;
        effectiveSize++;
        return true;         
    }

    @Override
    public E remove(int index) {
        if (this.isEmpty()) {
            return null;
        } else if (index>(effectiveSize-1)) {
            System.out.println("El indice esta fuera del alcanze del ArrayList");
            return null;
        }
        E element = elements[index];
                        
        for (int i=index; i<effectiveSize-1; i++){
            elements[i] = elements[i+1];
        }
        effectiveSize--;
        return element;        
    }

    @Override
    public E get(int index) {
        return elements[index];        
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
            int cursor = 0;
            
            @Override
            public boolean hasNext() {                
                return this.cursor<effectiveSize;                
            }

            @Override
            public E next() {                
                E element = elements[cursor];
                cursor++;                
                return element;
            }
        };
        return it;        
    }    
    
    public List<E> convertArrayList(E[] arreglo) {
        List<E> newArreglo = new ArrayList<>();
        for (int i=0; i<arreglo.length; i++) {
            newArreglo.add(arreglo[i]);
        }
        return newArreglo;
    }
    public String toString(){
        String cadena = "";
        for (int i = 0; i < effectiveSize; i++) {
            if (i < effectiveSize - 1) {
                cadena = cadena + elements[i] + ",";
            } else {
                cadena = cadena + elements[i];
            }

        }
        return '[' + cadena + ']';
    }

    @Override
    public E getPrevious(E actual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getNext(E actual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
