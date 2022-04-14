package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private List<Integer> question;
    private int winCount;

    Computer(){
        this.question = new ArrayList<>();
        this.winCount = 0;
        this.createQuestion();
    }

    public void createQuestion(){
        while(this.question.size() < 3){
            this.addNumber( Randoms.pickNumberInRange(1, 9) );
        }
    }

    private void addNumber(int randomNumber){
        boolean isExist = this.checkIsExist( randomNumber );
        if(!isExist){
            this.question.add( randomNumber );
        }
    }

    private boolean checkIsExist(int randomNumber){
        return this.question.contains( randomNumber );
    }

    public List< Integer > getQuestion() {
        return question;
    }

    public int getWinCount() {
        return winCount;
    }
}
