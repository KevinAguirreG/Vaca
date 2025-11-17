/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vaca;

import java.io.Serializable;

/**
 *
 * @author KevinAguirre
 */
public class Tree<E> implements Serializable{
    //Variables
    private int id;
    private static int num=1;
    //La raíz de nuestro árbol
    private Node root;
    
    //Constructores
    //Vacío
    public Tree() {
        this.id = num;
        num++;
    }
    
    //Lleno
    public Tree(Node root) {
        this();
        this.root = root;
    }
    
    /**
     * Agregamos un nuevo hijo a la root.
     * @param newNode 
     */
    public void addChild(Node newNode){
        this.root.addChild(newNode);
    }

    public Node getRoot() {
        return root;
    }
    
    
    
    /**
     * Obtenemos al hijo por medio de la posición dada.
     * @param position
     * @return 
     */
    public Node getChildByPosition(int position){
        Node resultNode = null; 
        /* Si la root no es igual a null y la posición es menor al número de 
        hijos y mayor o igual a 0.
        */
        if(root != null && root.getChildren().size() > position 
                && position >= 0){
            //Asignamos el hijo de la posición como variable a regresar.
            resultNode = (Node) root.getChildren().get(position);
        }
        //Regresamos el resultado.
        return resultNode;
    }
    
    
    /**
     * Obtenemos el hijo que sea igual al nodo dado.
     * @param node
     * @return 
     */
    public Node getChildByNode(Node node){
        Node resultNode = null;
        //Recorremos los hijos.
        for(int i = 0; i < root.getChildren().size(); i++){
            //Si el hijo es igual al nodo que estamos buscando.
            if(root.getChildren().get(i) == node)
                //Asignamos el nodo como dato a regresar.
                resultNode = (Node) root.getChildren().get(i);
        }
        //Regresamos el resultado.
        return resultNode;
    }
    
    
}
