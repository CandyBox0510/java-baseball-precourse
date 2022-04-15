package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        runGame( );
    }

    public static void runGame( ){
        Computer computer = new Computer();
        while(!computer.isEndGame()){
            System.out.printf("숫자를 입력해주세요 : ");
            String userInputNumber = Console.readLine();
            computer.gameStart( userInputNumber );
        }
        afterEndGame();
    }

    public static void afterEndGame(){
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String reGame = Console.readLine();
        if(reGame.equals( "1" )){
            runGame( );
        }
        if(reGame.equals( "2" )){
            System.exit(0);
        }
        System.out.println("1 또는 2 중에 입력하시기 바랍니다.");
        afterEndGame();
    }
}
