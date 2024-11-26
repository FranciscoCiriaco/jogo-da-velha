package org.example;


import java.util.Scanner;

public class JogoDaVelha {

    public static void exibirTabuleiro(String[][] tabuleiro) {
        System.out.println("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
        System.out.println("\n");
    }

    public static boolean verificarVitoria(String[][] tabuleiro, String jogador) {

        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0].equals(jogador) && tabuleiro[i][1].equals(jogador)
                    && tabuleiro[i][2].equals(jogador)) ||
                    (tabuleiro[0][i].equals(jogador) && tabuleiro[1][i].equals(jogador)
                            && tabuleiro[2][i].equals(jogador))) {
                return true;
            }
        }


        if ((tabuleiro[0][0].equals(jogador) && tabuleiro[1][1].equals(jogador)
                && tabuleiro[2][2].equals(jogador)) ||
                (tabuleiro[0][2].equals(jogador) && tabuleiro[1][1].equals(jogador)
                        && tabuleiro[2][0].equals(jogador))) {
            return true;
        }

        return false;
    }

    public static boolean verificarEmpate(String[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[][] tabuleiro = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };


        System.out.print("Nome do jogador 1 (X): ");
        String p1 = scanner.nextLine();
        System.out.print("Nome do jogador 2 (O): ");
        String p2 = scanner.nextLine();


        String jogadorAtual = p1;
        String simboloAtual = "X";


        for (int rodada = 0; rodada < 9; rodada++) {
            exibirTabuleiro(tabuleiro);


            int pos = 0;
            boolean posicaoValida = false;
            while (!posicaoValida) {
                System.out.print(jogadorAtual + ", escolha uma posição (1-9): ");
                pos = scanner.nextInt();
                if (pos < 1 || pos > 9) {
                    System.out.println("Posição inválida! Escolha um número entre 1 e 9.");
                    continue;
                }
                int linha = (pos - 1) / 3;
                int coluna = (pos - 1) % 3;
                if (tabuleiro[linha][coluna].equals(" ")) {
                    tabuleiro[linha][coluna] = simboloAtual;
                    posicaoValida = true;
                } else {
                    System.out.println("Posição já ocupada! Escolha outra.");
                }
            }


            if (verificarVitoria(tabuleiro, simboloAtual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println(jogadorAtual + " venceu!");
                return;
            }


            if (verificarEmpate(tabuleiro)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Empate!");
                return;
            }


            if (jogadorAtual.equals(p1)) {
                jogadorAtual = p2;
                simboloAtual = "O";
            } else {
                jogadorAtual = p1;
                simboloAtual = "X";
            }
        }
    }
}
