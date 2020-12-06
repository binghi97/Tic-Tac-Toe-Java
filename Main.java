package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static void print2dArray(char[][] arr){
      System.out.println("---------");
      for (int i=0; i < arr.length; i++) {
        System.out.print("| ");
        for (int j=0; j < arr[i].length; j++){
          System.out.print(arr[i][j] + " ");
        }
        System.out.println("|");
      }
      System.out.println("---------");
    }

    static boolean[] tableCheck(char[][] arr) {
      boolean[] parameters = new boolean[2];
      byte X = 0;
      byte O = 0;
      for (int i = 0; i < arr.length; i++) {
        for ( int j = 0; j < arr[i].length; j++) {
          switch (arr[i][j]){
            case ' ':
              parameters[0]=true;
              break;
            case 'X':
              X++;
              break;
            case 'O':
              O++;
              break;
          }
        }
      }
      if (Math.abs(X-O)>=2)
        parameters[1]=true;
      return parameters;
    }

    static boolean[] getWinner(char[][] arr){
      boolean[] winner = {false, false};
      char[] X = {'X' , 'X' , 'X'};
      char[] O = {'O' , 'O' , 'O'};
      char[] local = new char[3];

      for (int i = 0; i< arr.length; i++){
        local[i] = arr[i][i];
        if (Arrays.equals(arr[i],X))
          winner[0] = true;
        else {
          if (Arrays.equals(arr[i],O))
            winner[1]= true;
        }
      }

      if (Arrays.equals(local,X))
        winner[0] = true;
      else {
        if (Arrays.equals(local,O))
          winner[1]= true;
      }

      for (int i=0; i<3; i++){
        for (int j=0; j<3; j++){
          local[j] = arr[j][i];
        }
        if (Arrays.equals(local,X))
          winner[0] = true;
        else {
          if (Arrays.equals(local,O))
            winner[1]= true;
        }
      }

      for (int i=0; i<3; i++){
        local[i]=arr[i][2-i];
      }
      if (Arrays.equals(local,X))
        winner[0] = true;
      else {
        if (Arrays.equals(local,O))
          winner[1]= true;
      }

      return winner;
    }

    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      char[][] table = new char[3][3];
        for (int i=0; i<3; i++) {
          for (int j = 0; j < 3; j++) {
            table[i][j] = ' ';
          }
        }

      boolean cond = true;
      String str;
      boolean X_turn = true;
      while (cond) {
        print2dArray(table);
        System.out.print("Enter the coordinates:");
        str = input.nextLine();
        if (str.isEmpty())
          str = input.nextLine();
        if (str.length()==3) {
            if( Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(2)) ){
              if (str.charAt(1)==' '){
                int x = Character.getNumericValue(str.charAt(0))-1;
                int y = Character.getNumericValue(str.charAt(2))-1;
                if ( !(x>2 || x<0 || y>2 || y<0) ){
                  int container = x;
                  x = 2 - y;
                  y = container;
                  if ( table[x][y] == ' ' ) {
                    if (X_turn)
                      table[x][y] = 'X';
                    else
                      table[x][y] = 'O';
                    X_turn = !X_turn;
                    if ( getWinner(table)[0] == getWinner(table)[1] ) {
                      if ( !tableCheck(table)[0] ) {
                        print2dArray(table);
                        System.out.println("Draw");
                        cond = false;
                      }
                    } else {
                      print2dArray(table);
                      if (getWinner(table)[0])
                        System.out.println("X wins");
                      else
                        System.out.println("O wins");
                      cond = false;
                    }
                  }
                  else
                    System.out.println("This cell is occupied! Choose another one!");
                } else
                  System.out.println("Coordinates should be from 1 to 3!");
              } else
                System.out.println("Not a valid set of coordinates.");
            } else
              System.out.println("You should enter numbers!");
          } else
            System.out.println("Not a valid set of coordinates.");
      }

      /*
      if ( !tableCheck(table)[1] ) {
        if ( getWinner(table)[0] != getWinner(table)[1] ) {
          if (getWinner(table)[0])
            System.out.println("X wins");
          else
            System.out.println("O wins");
        } else if ( getWinner(table)[0]==false && getWinner(table)[1]==false && tableCheck(table)[0] ) {
          System.out.println("Game not finished");
        } else if ( getWinner(table)[0]==false && getWinner(table)[1]==false && !tableCheck(table)[0] ) {
          System.out.println("Draw");
        } else if ( getWinner(table)[0] && getWinner(table)[1] ) {
          System.out.println("Impossible");
        }
      } else {
        System.out.println("Impossible");
      }
       */
    }
}
