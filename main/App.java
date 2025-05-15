package main;
import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        var resp = (nRainhas(4));
    }

    public static boolean isSafe(int linha, int coluna, int[][] tabuleiro){
        for(int  i = 0; i < linha; i++){
            if(tabuleiro[i][coluna] == 1){
                return false;
            }
        }

        for(int i = linha - 1, j = coluna - 1; i >= 0 && j >= 0; i--, j--){
            if(tabuleiro[i][j] == 1){
                return false;
            }
        }

        for(int i = linha - 1, j = coluna + 1; j < tabuleiro.length && i >= 0; i--, j++){
            if(tabuleiro[i][j] == 1){
                return false;
            }
        }
        return true;
    }

    public static boolean colocarRainha(int linha, int [][] tabuleiro, ArrayList<Integer> res){
        if(linha == tabuleiro.length){
            return true;
        }

       
        for(int coluna = 0; coluna < tabuleiro.length; coluna++){
            if(tabuleiro[linha][coluna] == 1){
                return false;
            }
            
            
            if(isSafe(linha, coluna, tabuleiro)){
                
                tabuleiro[linha][coluna] = 1;
                res.add(coluna+1);
                if(colocarRainha(linha+1, tabuleiro,res)){
                    return true;
                }

                tabuleiro[linha][coluna] = 0;
            }
        }
        return false;
    }

    public static List<Integer> nRainhas(int n)throws IllegalArgumentException{
        if (n < 2){
            throw new IllegalArgumentException("n < 2");
        }
        int tabuleiro [][] = new int[n][n];
        ArrayList<Integer> resp = new ArrayList<>();
        colocarRainha(0, tabuleiro, resp);
        
        return resp;

        
    }


}