/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vaca;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author KevinAguirre
 */
public class Vaca {
    //Iniciamos variables en caso de no tener un árbol anterior.
    public static Node initialNode = new Node(null, 
            "Tiene cuernos?", null);
    public static Node muuu = new Node("si", null, "vaca");
    public static Node miau = new Node("no", null, "gato");
    //Scanner para que el usuario pueda ingresar texto.
    public static Scanner input = new Scanner(System.in);
    
    /**
     * Método para agregar hijos al root.
     */
    public static void startTree(){
        initialNode.addChild(muuu);
        initialNode.addChild(miau);
    }
    
    /**
     * Método que recorre el árbol y le hace preguntas al usuario.
     * @param root
     * @return 
     */
    public static Node ask(Node root){
        //Inicializamos la variable a regresar.
        Node result = null;
        //Si nuestro nodo tiene una pregunta.
        if(root.getQuestion() != null){
            //Le hacemos la pregunta al usuario.
            String response = JOptionPane.showInputDialog
                (root.getQuestion() + " si/no");
            //Si responde que sí.
            if(response.contentEquals("si")){ //Degradación.
                //Si no tenemos hijos, somos una hoja.
                if(root.getLeftChild().getChildren().isEmpty()){
                    //Por lo tanto somos la respuesta.
                    result = root.getLeftChild();//Caso de salida.
                }else{
                    //De lo contrario seguimos recorriendo el árbol.
                    result = ask(root.getLeftChild());//Caso recursivo.
                }
            }else{
                //Si responde que no.
                //Si no tenemos hijos, somos una hoja.
                if(root.getRightChild().getChildren().isEmpty()){
                    //Por lo tanto somos la respuesta.
                    result = root.getRightChild(); //Caso de salida.
                }else{
                    //De lo contrario seguimos recorriendo el árbol.
                    result = ask(root.getRightChild());//Caso recursivo.
                }
            }
        }
        //Regresamos nuestro resultado.
        return result;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inicio
        // Ponemos un delimitador al Scanner para leer espacios en blanco.
        input.useDelimiter("\n");
        //Iniciamos la variable para saber si el juego ha acabado.
        boolean endGame = false;
        //Inicializamos la variable de nuestro árbol.
        Tree myTree = null;
        //Le preguntamos al usuario si quiere continuar con su partida anterior.
        String responseLoadGame = JOptionPane.showInputDialog
        ("¿Quieres cargar la partida pasada? si/no");
        //Comprobamos su respuesta.
        if(responseLoadGame.contentEquals("si")){
            //En caso de ser sí, cargamos el documento de su árbol.
            try {
                //Buscamos en nuestros archivos el documento.
                FileInputStream fileIn = new FileInputStream(
                        "C:\\Users\\kevin\\OneDrive\\Documentos"
                                + "\\NetBeansProjects\\Vaca\\TreeVaca.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                //Asignamos el árbol anterior a nuestro árbol local. 
                myTree = (Tree) in.readObject();
                //Cerramos las variables para cargar archivos serializados.
                in.close();
                fileIn.close();
            } catch (FileNotFoundException e) {
                //Atrapamos los errores que peudan ocurrir.
                // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
            }
       }else{
            //En caso de que no quiera cargar su partida anterior creamos un 
            // nuevo árbol.
            myTree = new Tree(initialNode);
            startTree();
        }
       
        //Repetimos el código hasta que el jugador ya no quiera continuar.
        do{ 
            // Preguntar si la vaca acertó.
            //Invocamos al método recursivo.
            Node cowAns = ask(myTree.getRoot());
            //Le preguntamos la usuario si acertamos.
            String response = JOptionPane.showInputDialog
        ("¿Tu animal era " + cowAns.getAnimal() + "? si/no");
            //Comprobamos la respuesta
            if(response.contentEquals("si")){
                //Si el usuario dice que si adivinamos, hemos ganado.
                JOptionPane.showMessageDialog
                    (null, "MUUU GANE MUUUUU");
            }else{
                //De lo contrario, aprendemos de nuestros errores.
                //Le pedimos al usuario su animal.
                String userAnimal = JOptionPane.showInputDialog
                ("¿Cuál era tu animal?");
                //Le pedimos una pregunta para identificar al animal.
                String userQuestion = JOptionPane.showInputDialog
                    ("Escribe en formato de pregunta como se "
                    + "diferencía " + userAnimal + " de " + cowAns.getAnimal() 
                    + ": ");
                
                
                //Creamos nodos de los animales.
                Node oldAnimal = new Node
                ("no", null, cowAns.getAnimal());
                Node newAnimal = new Node
                ("si", null, userAnimal);

                //Modificamos el nodo que le dimos al usuario con la pregunta.
                cowAns.setAnimal(null);
                cowAns.setQuestion(userQuestion);

                //Agregamos los animales al nodo de pregunta.
                cowAns.addChild(newAnimal);
                cowAns.addChild(oldAnimal);

            }
            //Le preguntamos al usuario si quiere seguir jugando.
            String responseEndGame = JOptionPane.showInputDialog
                ("¿Quieres volver a jugar?");
            
            //Si el jugador ya no quiere continuar mandamos true.
             if(responseEndGame.contentEquals("no")){
               endGame = true;
            }
               
        }while(!endGame); // Terminamos el juego.
        //Guardar el árbol
        try{
            //Guardamos el tree en donde le indiquemos.
            FileOutputStream fileOut = new FileOutputStream("TreeVaca.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            //Le indicamos el objeto a guardar.
            out.writeObject(myTree);
            //Cerramos las variables de serialización.
            out.close();
            fileOut.close();
            //Mandamos mensaje de confirmación
            JOptionPane.showMessageDialog
                    (null, "Vaca guardada correctamente");
        }catch(IOException e){
            //Atrapamos los errores.
            e.printStackTrace();
        }

        // Fin
    }   
    
    
}
    

