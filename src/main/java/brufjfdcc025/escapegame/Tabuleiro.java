package brufjfdcc025.escapegame;

import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    String[][] tabuleiro = new String[10][10];
    int jogadorlinha, jogadorcoluna;
    int bomba,linha,coluna;
    Random gerador = new Random();
    Scanner teclado = new Scanner(System.in);
    boolean saida, bomb;
    boolean[][] bombed = new boolean[10][10];
    
    
    public Tabuleiro(int bombas, int opc) {
        
        //Iniciando a matriz de bombas
        for(int i=0; i<10; i++)
            for(int j=0;j<10;j++){
                    bombed[i][j] = false;
            }
        //iniciando o tabuleiro com espaço
        for(int i=0; i<10; i++)
            for(int j=0;j<10;j++)
                tabuleiro[i][j] = " ";
        
        //gerando player
        linha = 0;
        coluna = 0;
        tabuleiro[linha][coluna] = "P";
        jogadorlinha = linha;
        jogadorcoluna = coluna;
        
        //alocando n bombas
        int k = 0;
        while(k < bombas){
            linha = gerador.nextInt(9)+1;
            coluna = gerador.nextInt(9)+1;
            if(tabuleiro[linha][coluna] != " " || (linha == 0 && coluna == 1) || (linha == 1 && coluna == 0)|| (linha == 1 && coluna == 1)){
                while(tabuleiro[linha][coluna] != " " || (linha == 0 && coluna == 1) || (linha == 1 && coluna == 0)|| (linha == 1 && coluna == 1)){
                    linha = gerador.nextInt(9)+1;
                    coluna = gerador.nextInt(9)+1;
                }
            }
            for(int j = 0;j<10;j++){
                int cont=0;
                if(tabuleiro[linha][j] == " ")
                    cont++;
                if(cont <= 3){
                    linha = gerador.nextInt(9)+1;
                    if(tabuleiro[linha][coluna] != " " || (linha == 0 && coluna == 1) || (linha == 1 && coluna == 0)|| (linha == 1 && coluna == 1)){
                        while(tabuleiro[linha][coluna] != " " || (linha == 0 && coluna == 1) || (linha == 1 && coluna == 0)|| (linha == 1 && coluna == 1)){
                            linha = gerador.nextInt(9)+1;
                            coluna = gerador.nextInt(9)+1;
                        }
                    }
                }
            }
            for(int j = 0;j<10;j++){
                int cont=0;
                if(tabuleiro[j][coluna] == " ")
                    cont++;
                if(cont <= 3){
                    linha = gerador.nextInt(9)+1;
                    if(tabuleiro[linha][coluna] != " "){
                        while(tabuleiro[linha][coluna] != " "){
                            linha = gerador.nextInt(9)+1;
                            coluna = gerador.nextInt(9)+1;
                        }
                    }
                }
            }
            tabuleiro[linha][coluna] = "B" ;
            k++;
        }


        //gerando saída
        linha = gerador.nextInt(9)+1;
        coluna = gerador.nextInt(9)+1;
        if(tabuleiro[linha][coluna] != " " && tabuleiro[linha+1][coluna] != " " && tabuleiro[linha-1][coluna] != " " &&
                tabuleiro[linha][coluna+1] != " " && tabuleiro[linha][coluna-1] != " "){
            while(tabuleiro[linha][coluna] != " " && tabuleiro[linha+1][coluna] != " " && tabuleiro[linha-1][coluna] != " " &&
                tabuleiro[linha][coluna+1] != " " && tabuleiro[linha][coluna-1] != " "){
                linha = gerador.nextInt(9)+1;
                coluna = gerador.nextInt(9)+1;
            }
        }
        tabuleiro[linha][coluna] = "S";
        
        //alocando as posições com bombas
        for(int i=0; i<10; i++)
            for(int j=0;j<10;j++){
                if(tabuleiro[i][j] == "B"){
                    if(opc == 2)
                        tabuleiro[i][j] = " ";
                    bombed[i][j] = true;
                }
            }
        
        saida = false;
        bomb = false;
        
    }
    
    
    public void ImprimirTabuleiro(){
        for(int i=0; i<10; i++){
            for(int j=0;j<10;j++){
                System.out.print(" | ");
                System.out.print(tabuleiro[i][j]);
            }
                System.out.println("");
                System.out.println("-----------------------------------------");
        }
        System.out.println("");
    }
    
    public void Jogada(String jogada){
        char direcao = ' ';
        int quant = 0;
        for(int i=0; i<jogada.length();i++){
            if(jogada.charAt(i)== 'e' || jogada.charAt(i)== 'd' || jogada.charAt(i)== 'c' || jogada.charAt(i)== 'b'){
                direcao = jogada.charAt(i);
            }
            if(jogada.charAt(i)== '0' || jogada.charAt(i)== '1' || jogada.charAt(i)== '2' || jogada.charAt(i)== '3' || jogada.charAt(i)== '4' ||
                    jogada.charAt(i)== '5' || jogada.charAt(i)== '6' || jogada.charAt(i)== '7' || jogada.charAt(i)== '8' || jogada.charAt(i)== '9'){
                quant = Character.getNumericValue(jogada.charAt(i));
            }
        }
        
        switch(direcao){
            case 'e': 
                if(jogadorcoluna - quant< 0){
                    if(jogadorcoluna == 0){
                        System.out.println("Você está no limite!!!!");
                        System.out.println("Não pode andar para a esquerda!!!!");
                        break;
                    }
                    System.out.println("Valor invalido, o máximo de casa que você pode andar é "+ jogadorcoluna);
                    System.out.println("** O deslocamento já foi escolhido, por favor escolha somente o numero de casas **");
                    System.out.println("Tente novamente: ");
                    quant = teclado.nextInt();
                }
                for(int i=jogadorcoluna-1; i>= jogadorcoluna-quant; i--){
                    if(bombed[jogadorlinha][i] == true){
                        bomb = true;
                        tabuleiro[jogadorlinha][i] = "B";
                    }
                }
                if(bomb == false){
                    if(tabuleiro[jogadorlinha][jogadorcoluna - quant] == "S")
                        saida = true;
                    tabuleiro[jogadorlinha][jogadorcoluna] = " ";
                    jogadorcoluna = jogadorcoluna - quant;
                    tabuleiro[jogadorlinha][jogadorcoluna] = "P"; 
                }
                
                break;
            case 'd':
                if(jogadorcoluna + quant > 9){
                    if(9-jogadorcoluna == 0){
                        System.out.println("Você está no limite!!!!");
                        System.out.println("Não pode andar para a direita!!!!");
                        break;
                    }
                    System.out.println("Valor invalido, o máximo de casa que você pode andar é "+ (9-jogadorcoluna));
                    System.out.println("** O deslocamento já foi escolhido, por favor escolha somente o numero de casas **");
                    System.out.println("Tente novamente: ");
                    quant = teclado.nextInt();
                }
                for(int i=jogadorcoluna+1; i<= jogadorcoluna+quant; i++){
                    if(bombed[jogadorlinha][i] == true){
                        bomb = true;
                        tabuleiro[jogadorlinha][i] = "B";
                    }
                }
                
                if(bomb == false){
                    if(tabuleiro[jogadorlinha][jogadorcoluna + quant] == "S")
                        saida = true;
                    tabuleiro[jogadorlinha][jogadorcoluna] = " ";
                    jogadorcoluna = jogadorcoluna + quant;
                    tabuleiro[jogadorlinha][jogadorcoluna] = "P"; 
                }
                break;
            case 'c':
                if(jogadorlinha - quant< 0){
                    if(jogadorlinha == 0){
                        System.out.println("Você está no limite!!!!");
                        System.out.println("Não pode andar para cima!!!!");
                        break;
                    }
                    System.out.println("Valor invalido, o máximo de casa que você pode andar é "+ jogadorlinha);
                    System.out.println("** O deslocamento já foi escolhido, por favor escolha somente o numero de casas **");
                    System.out.println("Tente novamente: ");
                    quant = teclado.nextInt();
                }
                for(int i=jogadorlinha-1; i>= jogadorlinha-quant; i--){
                    if(bombed[i][jogadorcoluna] == true){
                        tabuleiro[i][jogadorcoluna] = "B";
                        bomb = true;
                    }
                }
                if(bomb == false){
                    if(tabuleiro[jogadorlinha - quant][jogadorcoluna] == "S")
                        saida = true;
                    tabuleiro[jogadorlinha][jogadorcoluna] = " ";
                    jogadorlinha = jogadorlinha - quant;
                    tabuleiro[jogadorlinha][jogadorcoluna] = "P"; 
                }
                break;
            case 'b':
                if(jogadorlinha + quant > 9){
                    if(9-jogadorlinha == 0){
                        System.out.println("Você está no limite!!!!");
                        System.out.println("Não pode andar para baixo!!!!");
                        break;
                    }
                    System.out.println("Valor invalido, o máximo de casa que você pode andar é "+ (9-jogadorlinha));
                    System.out.println("** O deslocamento já foi escolhido, por favor escolha somente o numero de casas **");
                    System.out.println("Tente novamente: ");
                    quant = teclado.nextInt();
                }
                for(int i=jogadorlinha+1; i<= jogadorlinha+quant; i++){
                    if(bombed[i][jogadorcoluna] == true){
                        bomb = true;
                        tabuleiro[i][jogadorcoluna] = "B";
                    }
                }
                if(bomb == false){
                    if(tabuleiro[jogadorlinha+quant][jogadorcoluna] == "S")
                        saida = true;
                    tabuleiro[jogadorlinha][jogadorcoluna] = " ";
                    jogadorlinha = jogadorlinha + quant;
                    tabuleiro[jogadorlinha][jogadorcoluna] = "P"; 
                }
                break;
        }
    }
}
