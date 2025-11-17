/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vaca;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author KevinAguirre
 */
public class Node<E> implements Serializable{
     //Creamos las variables del nodo,
    private int id;
    //Llevamos el conteo de la cantidad de nodos que hay.
    private static int num = 1;
    //Número de hijos que puede tener.
    private int max = 2;
    //Los datos que va a almacenar nuestro nodo.
    private E response;
    private E question;
    private E animal;
    //Los hijos que tendrá.
    private ArrayList<Node> children;
    
    //Constructores 
    //Constructor vacío.
    public Node() {
        this.id = num;
        num++;
        this.children = new ArrayList<>(); 
    }
    //Constructor lleno.
    public Node(E response, E question, E animal) {
        this();
        this.response = response;
        this.question = question;
        this.animal = animal;
    }
   
    
    //Agregar un hijo
    public void addChild(Node child){
        if(this.children.size() < 2){
            //Agregamos un hijo con el método add del arrayList.
            this.children.add(child);
        }
    }
    
    //Getters

    public E getResponse() {
        return response;
    }

    public E getQuestion() {
        return question;
    }

    public E getAnimal() {
        return animal;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }
    
    public Node getLeftChild(){
        Node result = null;
        if(this.children.size() > 0){
            result = this.children.get(0);
        }
        return result;
    }
    
    public Node getRightChild(){
        Node result = null;
        if(this.children.size() > 1){
            result = this.children.get(1);
        }
        return result;
    }
    
    //Setters
    public void setResponse(E response) {
        this.response = response;
    }

    public void setQuestion(E question) {
        this.question = question;
    }

    public void setAnimal(E animal) {
        this.animal = animal;
    }
    
    
    // toString

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", response=" + response + ", question="
                + question + ", animal=" + animal + ", children=" + children +
                '}';
    }

    
    
}
