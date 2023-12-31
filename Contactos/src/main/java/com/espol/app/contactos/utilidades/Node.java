/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.app.contactos.utilidades;

import java.io.Serializable;

/**
 *
 * @author mfalvarez
 */
public class Node<E> implements Serializable{
    private E element;
    private Node<E> next;
    private Node<E> previous;

    public Node(){
    }
    
    public Node(E element) {
        this.element = element;
        this.next = this;
        this.previous= this;
    }

    public Node(E element, Node<E> next, Node<E> previous) {
        this.element = element;
        this.next = next;
        this.previous = previous;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }        
}
