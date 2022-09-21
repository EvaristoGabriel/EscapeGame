package brufjfdcc025.escapegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Gabriel Evaristo Carlos
    201964034AB
*/
public class EscapeGame {
    public static void main(String[] args){
        System.out.println("------ Seja Bem-Vindo ao Escape Game -----");
        int continuar = 1;
        while(continuar == 1){
            System.out.println("Quantas bombas você deseja? (Quanto mais bomba mais díficil será o jogo)");
            Scanner teclado = new Scanner(System.in);
            int bombas = teclado.nextInt();
            if(bombas > 30){
                System.out.println("Numero invalido, tente menos que 30 bombas");
                bombas = teclado.nextInt();
            }
            
            System.out.println("Nivel de Dificuldade: ");
            System.out.println("1-Facil (nesta opção as bombas serão mostradas)");
            System.out.println("2-Dificil (nesta opção as bombas não serão mostradas)");
            int opc = teclado.nextInt();
            
            Tabuleiro tabuleiro = new Tabuleiro(bombas,opc);
            tabuleiro.ImprimirTabuleiro();
            String buffer = teclado.nextLine();
            
            boolean saida = false, bomba = false;
            List<String> jogadas = new ArrayList<>();
            while(saida ==  false && bomba == false){
                System.out.println("Digite o sentido e a quantidade de casas que deseja andar: ");
                String jogada = teclado.nextLine();
                tabuleiro.Jogada(jogada);
                
                jogadas.add(jogada);
                
                tabuleiro.ImprimirTabuleiro();
                
                if(tabuleiro.bomb == true){
                    System.out.println("GAME OVER!!!!!!");
                    System.out.println("----- YOU LOSE -----");
                    System.out.println("Suas jogadas: ");
                    System.out.println(jogadas.toString());
                    bomba = true;
                }
                
                if(tabuleiro.saida == true){
                    System.out.println("PARABENS!!!!!!");
                    System.out.println("----- YOU WIN -----");
                    System.out.println("Suas jogadas: ");
                    System.out.println(jogadas.toString());
                    saida = true;
                }
            }
            
            System.out.println("Deseja jogar novamente?");
            System.out.println("1-Sim\n2-Não");
            continuar = teclado.nextInt();
        }
    }
}
