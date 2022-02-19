//Henrique Andrew da Silva

package br.com.henriqueandrew.socketcpf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    private static Socket socket;
    private static ServerSocket server;
    
    private static DataInputStream entrada;
    private static DataOutputStream saida;
    
    private static String cpf;
    
    public static void main(String[] args){
        
        try{            
            //Porta de recepcao
            server = new ServerSocket(2020);
            socket = server.accept(); 
                    
            //Fluxo de entrada de saida
            entrada = new DataInputStream(socket.getInputStream());
            saida =  new DataOutputStream(socket.getOutputStream());
    
    
            //Recebimento do CPF
            cpf = entrada.readUTF();
            System.out.println("CPF informado pelo usuario: " + cpf);    
    
            //Validar CPF
            if (cpf == null){
                System.out.println("CPF não informado!");
            }
            else{
                String cpfGerado = "";
                removerCaracteres();
                if(verificarTamanho(cpf)){
                    System.out.println("Tamanho CPF invalido!");
                }
                if(verificarDigitosIguais(cpf)){
                    System.out.println("CPF invalido!");
                }
                else{
                    cpfGerado = cpf.substring(0, 9);
                    cpfGerado = cpfGerado.concat(calculoCPF(cpfGerado));
                    cpfGerado = cpfGerado.concat(calculoCPF(cpfGerado));
                    
                    String resultado = "";
                    if(!cpfGerado.equals(cpf)){
                        resultado = "CPF invalido!!!";
                    }
                    else{
                        resultado = "CPF é Válido!!!";
                    }
                    //Envio do Resultado
                    saida.writeUTF(resultado);
                    socket.close();
                }
                
            } 
            
        }
        catch(Exception e){
        
        }
   
    }
    
    private static void removerCaracteres(){
        cpf = cpf.replace("-", "");
        cpf = cpf.replace(".", "");
    }
    
    private static boolean verificarTamanho(String cpf){
        if (cpf.length() != 11)
            return true;
                return false;
    }
    
    private static boolean verificarDigitosIguais(String cpf){
        char dig = '0';
        char [] charCPF = cpf.toCharArray();
        for(char c: charCPF)
            if (c != dig)
                return false;
                return true;
    }
    
    private static String calculoCPF(String cpf){
        int digGerado = 0;
        int mult = cpf.length()+1;
        char[] charCPF = cpf.toCharArray();
        for (int i = 0; i < cpf.length(); i++)
            digGerado += (charCPF[i]-48)* mult--;
        if(digGerado % 11 < 2)
            digGerado = 0;
        else
            digGerado = 11 - digGerado % 11;
        return String.valueOf(digGerado);
        
    }
}
