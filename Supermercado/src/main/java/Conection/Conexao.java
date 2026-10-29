/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection conectar() 
            throws SQLException {
        
        String servidor = "localhost"; // padrão
        String porta = "3306"; //padrão
        String banco = "supermercado";
        String usuario = "root";//padrão
        String senha ="S3n4c@2026"; //padrão
        String url ="jdbc:mysql://"+
                servidor + ":"+ porta + "/" + banco; //padrão
        
        return DriverManager.getConnection(url, usuario, senha);
    
    }// fim do conectar()
    
    public static void main(String[] args) {
          // Abre a conexão e fecha automaticamente ao terminar.
        try (Connection conexao = conectar()) {

            if (conexao.isValid(5)) {
                System.out.println("Conectado ao MySQL com sucesso!");
            } else {
                System.out.println("A conexão não respondeu ao teste.");
            }

        } catch (SQLException erro) {

            System.out.println("Erro ao conectar ao banco:");
            System.out.println(erro.getMessage());

        }
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}// fim da classe
