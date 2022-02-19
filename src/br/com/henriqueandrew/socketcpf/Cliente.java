//Henrique Andrew da Silva

package br.com.henriqueandrew.socketcpf;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    
   private static Socket socket;
   private static DataInputStream entrada;
   private static DataOutputStream saida;
    
   public static void main (String[] args){
       
       try{
           socket = new Socket("127.0.0.1", 2020);
           
           entrada = new DataInputStream(socket.getInputStream());
           saida = new DataOutputStream(socket.getOutputStream());
           
           //Leitura do CPF do teclado
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           System.out.println("Informe o CPF: ");
           String cpf = br.readLine();
           
           //-Recebendo dado por janela grafica
           //String dadousuario = JOptionPane.showInputDialog(Informe o CPF: ");
           //String cpf = dadoUsuario;
           
           //Envio do CPF ao Servidor para validação
           saida.writeUTF(cpf);
           
           //Recebe o resultado do Servidor
           String resultado = entrada.readUTF();
           
           //Mostra o resultado na tela
           System.out.println(resultado);
           socket.close();
           
       }
       catch(Exception e){
       }
       
   }
   
}
