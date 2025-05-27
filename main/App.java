package main;
import java.util.ArrayList;
import java.util.List;

public class App{
    public static int count = 0;
    public static void main(String[] args) {

        int [][] sets= new int[][]{{-1, 1, -2, 2}, {-1, 1, -2, 2, -3,3},{-1, 1, -2, 2, -3,3,-4,4}, {-1, 1, -2, 2, -3,3,-4,4, -5,5}};
        
        int [] ns = {3,4,5,6,7,8,9,10};
        var resp = nRainhas(3);
        System.out.println("SOMA SUBCONJUNTOS");
        System.out.printf("%-5s| %-10s| %-10s%n", "size", "iteracoes", "tempo(s)");
        System.out.println("-------------------------------");
        for (int i = 0; i < sets.length; i++) {
            count = 0;
            long s = System.nanoTime();
            somaSubconjuntosAux(sets[i], count, new ArrayList<>(), 0, 0);
            long e = System.nanoTime();
        
            System.out.printf("%-5d| %-10d| %-10.10f%n", sets[i].length, count, (e-s)*1e-9);
        
        }
        System.out.println(resp);
        System.out.println(count);

    }

    public static boolean isSafe(int linha, int coluna, int[][] tabuleiro){
        count++;
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
        count++;

        if(linha == tabuleiro.length){
            return true;
        }

       
        for(int coluna = 0; coluna < tabuleiro.length; coluna++){
            count++;

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
        count++;

        if (n < 2){
            throw new IllegalArgumentException("n < 2");
        }
        int tabuleiro [][] = new int[n][n];
        ArrayList<Integer> resp = new ArrayList<>();
        colocarRainha(0, tabuleiro, resp);
        return resp;

        
    }

    public static List<Integer> somaSubconjuntosAux(int [] set, int target, ArrayList<Integer> backtr, int i,int j){
        count++;
        if(backtr.isEmpty()){
            backtr.add(set[i]);
        }
        int sum = backtr.stream().reduce(0,Integer::sum);

        if( sum == target){
            return backtr;
        }
        if(i < set.length && j == set.length){
            return somaSubconjuntosAux(set, target, new ArrayList<>(), i+1, i+2);
        }
        
        if(i == set.length && j == set.length){
            return null;
        }

        if(backtr.stream().reduce(0,Integer::sum) + set[j] == target){
                backtr.add(set[j]);
                return backtr;
        }else if(backtr.stream().reduce(0,Integer::sum) + set[j] > target){
                backtr.removeLast();
                return somaSubconjuntosAux(set, target, backtr, i,j+1);
        }
        backtr.add(set[j]);
        j++;
        
        return somaSubconjuntosAux(set, target, backtr, i,j);
    }

    
    public static ArrayList<ArrayList<Integer>> somaSubconjuntosTodos(int [] set, int target, ArrayList<Integer> backtr, int i,int j, ArrayList<ArrayList<Integer>> save){
        if (backtr.isEmpty() && i < set.length) {
        backtr.add(set[i]);
    }

    int sum = backtr.stream().mapToInt(Integer::intValue).sum();

    if (sum == target) {
        save.add(new ArrayList<>(backtr));  // Clonar para evitar modificação posterior
        if (i + 1 < set.length) {
            return somaSubconjuntosTodos(set, target, new ArrayList<>(), i + 1, i + 2, save);
        } else {
            return save;
        }
    }

    if (i >= set.length || j >= set.length) {
        if (i + 1 < set.length) {
            return somaSubconjuntosTodos(set, target, new ArrayList<>(), i + 1, i + 2, save);
        } else {
            return save;
        }
    }

    int sumWithJ = sum + set[j];

    if (sumWithJ == target) {
        backtr.add(set[j]);
        save.add(new ArrayList<>(backtr));
        // Tentar próximo subconjunto a partir de i+1
        return somaSubconjuntosTodos(set, target, new ArrayList<>(), i + 1, i + 2, save);
    } else if (sumWithJ > target) {
        // Não adicionar, tentar outro caminho
        if (!backtr.isEmpty()) {
            backtr.remove(backtr.size() - 1);
        }
        return somaSubconjuntosTodos(set, target, backtr, i, j + 1, save);
    } else {
        backtr.add(set[j]);
        return somaSubconjuntosTodos(set, target, backtr, i, j + 1, save);
    }
}

    

}