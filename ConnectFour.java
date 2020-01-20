/*
Rebecca Hochman-Fisher
Connect Four
Plays a game of Connect Four, where two people compete until they've connected
four spaces.


*/
import java.util.*;

class ConnectFour{
  public int[][] board= new int[7][7];

  public static void main(String []args){
    ConnectFour thisGame= new ConnectFour();
    Player p1= new Player("Player 1",1);
    Player p2= new Player("Player 2",2);
    Scanner reader= new Scanner(System.in);
    boolean keepPlaying = true; // while this is true the game continues
    System.out.println("\nWelcome to Connect Four!");
    thisGame.printBoard();
    System.out.println("\nPlayer 1 goes first. Enter a column:\n");
    while(keepPlaying==true){
      thisGame.placePiece(p1);
      thisGame.printBoard();
      if(thisGame.isWinner(p1)==true){
        break;
      }
      thisGame.placePiece(p2);
      thisGame.printBoard();
      if(thisGame.isWinner(p2)==true){
        break;
      }
    }

    System.out.println("Thanks for playing!");


  }

// creates a board for the ConnectFour class
  public ConnectFour(){
    for(int[]row: board){
      for(int place:row){
        place=0;
      }
    }
  }

  //prints out the current status of the board
  public void printBoard(){
    for(int[] row: board){
      for(int place:row){
        System.out.print("  "+place+"  ");
      }
      System.out.print("\n\n");
    }
    for(int i=0; i<7; i++){
      System.out.print("-----");
    }
    System.out.print("\n");
    for(int i=0; i<7; i++){
      System.out.print("  "+i+"  ");
    }
    System.out.println("\n");
  }

/* takes which player is placing the piece
 then asks player where they want piece to be placed */
  public void placePiece(Player who){
    Scanner reader= new Scanner(System.in);
    int col=0;
    System.out.println(who.getName()+" enter the column you want to place a piece in: ");
    col=reader.nextInt();
    for(int i=6; i>=0; i--){
      if(board[i][col]==0){
        board[i][col]=who.getNum();
        who.addMove(new Coordinate(i,col));
        System.out.println("The coord is row "+i+" and column "+col);
        break;
      }
    }
  }

// checks to see if a player has won. input: player output: whether player wins
  public boolean isWinner(Player whoIsBeingChecked){
    boolean h,v,du,dd;
    h= whoIsBeingChecked.checkHorizontal(board);
    v=whoIsBeingChecked.checkVertical(board);
    du=whoIsBeingChecked.checkDiagonallyUp(board);
    dd=whoIsBeingChecked.checkDiagonallyDown(board);
    if(h||v||du||dd){
      System.out.println("\n"+whoIsBeingChecked.getName()+" YOU WON");
      return true;
    }
    else{
      return false;
    }
  }
 }


class Player{
  private String name;
  private int num;
  private Vector<Coordinate> theMoves;


  public Player(String name, int num){
    theMoves= new Vector<Coordinate>();
    this.name=name;
    this.num= num;

  }

  public String getName(){
    return name;
  }

  public int getNum(){
    return num;
  }

  public Coordinate getLast(){
    return theMoves.get(theMoves.size()-1);
  }

  public void addMove(Coordinate addThis){
    theMoves.add(addThis);
  }


  /*checks all the points in the vector to see if a horizontal win was achieved
  returns true if win is achieved, false if not*/
  public boolean checkHorizontal(int[][] aBoard){
      int col, row;
    for(Coordinate coords: theMoves){
      col=coords.getCol();
      row= coords.getRow();
      if(7-col>3){
        if(aBoard[row][col+1]==num){
            if(aBoard[row][col+2]==num){
              if(aBoard[row][col+3]==num) {
                return true;
              }
            }
          }
        }
      if(col>=3){
        if(aBoard[row][col-1]==num){
            if(aBoard[row][col-2]==num){
              if(aBoard[row][col-3]==num){
                return true;
              }
            }
          }
        }
    }
      return false;
  }

  /*checks vector too see if VERTICAL win was achieved
  returns true if it wins, false if it doesn't*/
  public boolean checkVertical(int[][] aBoard){
    int col, row;
    for(Coordinate coords: theMoves){
      col=coords.getCol();
      row= coords.getRow();
    if(7-row>3){
      if(aBoard[row+1][col]==num){
        if(aBoard[row+2][col]==num){
          if(aBoard[row+3][col]==num) {
              return true;
            }
          }
        }
      }
      if(row>=3){
        if(aBoard[row-1][col]==num){
            if(aBoard[row-2][col]==num){
              if(aBoard[row-3][col]==num){
                return true;
            }
          }
        }
      }
  }
    return false;
  }

  /*checks vector too see if DIAGONAL DOWN win was achieved
  returns true if it wins, false if it doesn't*/
  public boolean checkDiagonallyDown(int[][] aBoard){
      int col, row;
      for(Coordinate coords: theMoves){
       col=coords.getCol();
       row= coords.getRow();
       if(row>=3){
          col=coords.getCol();
          row= coords.getRow();
          if(7-col>3){
            //down and right
            if(aBoard[row-1][col+1]==num){
                if(aBoard[row-2][col+2]==num){
                  if(aBoard[row-3][col+3]==num) {
                    return true;
                  }
                }
              }
            }
           if(col>=3){
             //down and left
            if(aBoard[row-1][col-1]==num){
                if(aBoard[row-2][col-2]==num){
                  if(aBoard[row-3][col-3]==num){
                    return true;
                  }
                }
              }
            }
        }
    }
    return false;
  }



  /*checks vector too see if DIAGONAL UP win was achieved
  returns true if it wins, false if it doesn't*/
  public boolean checkDiagonallyUp(int[][] aBoard){
    int col, row;
    for(Coordinate coords: theMoves){
      col=coords.getCol();
      row= coords.getRow();
      if(7-row>3){
        col=coords.getCol();
        row= coords.getRow();
        if(7-col>3){
          //up and right
          if(aBoard[row+1][col+1]==num){
              if(aBoard[row+2][col+2]==num){
                if(aBoard[row+3][col+3]==num) {
                  return true;
                }
              }
            }
          }
         if(col>=3){
           //up and left
          if(aBoard[row+1][col-1]==num){
              if(aBoard[row+2][col-2]==num){
                if(aBoard[row+3][col-3]==num){
                  return true;
                }
              }
            }
          }
      }
  }
  return false;
  }



}

class Coordinate{
  private int row;
  private int col;

// Constructor assigns the two int values passed to it to row and col
  public Coordinate(int r, int c){
    row=r;
    col=c;
  }
  public int getRow(){
    return row;
  }
  public int getCol(){
    return col;
  }

}
