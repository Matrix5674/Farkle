import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Player {
    private ArrayList<Integer> dice = new ArrayList<>();
    private int score;

    public Player () {
        for (int i = 0; i < 6; i++) {
            this.dice.add(0);
        }

        Collections.sort(this.dice);
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public ArrayList<Integer> getDice() {
        return dice;
    }

    public ArrayList<Integer> rollDice() {
        for (int i = 0; i < 6; i++) {
            dice.add((int)(Math.random()*6 + 1));
        }
        return dice;
    }

    public int number_of_specific_die(int checkedNumber) {
        int count = 0;
        for (int die: dice) {
            if (die == checkedNumber) {
                count += 1;
            }
        }
        return count;
    }

    public void remove_specific_die(int remove_die, int number_of_dice){
        int counter = number_of_dice;
        for (int i = 0; i < dice.size(); i++) {
            if (dice.get(i) == remove_die){
                counter--;
                dice.remove(i);
                if (counter == 0) break;

            }
        }
    }

    public int check_for_three_of_a_kind(){
        int dice_with_three_of_a_kind = 0;
        for (int i = 1; i <= 6; i++) {
            if (number_of_specific_die(i) >= 3) {
                dice_with_three_of_a_kind = i;
            }
        }
        return dice_with_three_of_a_kind;
    }

    public int check_for_four_of_a_kind(){
        int dice_with_four_of_a_kind = 0;
        for (int i = 1; i <= 6; i++) {
            if (number_of_specific_die(i) >= 4) {
                dice_with_four_of_a_kind = i;
            }
        }
        return dice_with_four_of_a_kind;
    }

    public boolean check_for_striaght () {
        Collections.sort(dice);

        for (int i = 1; i <= 6; i++) {
            if (number_of_specific_die((i-1)) != i){
                return false;
            }
        }
        return true;
    }

    public boolean check_for_three_pairs (){

        ArrayList<Integer> temp_dice = dice;
        if (temp_dice.size() >= 6){
            return false;
        }

        Collections.sort(temp_dice);

        for (int i = 0; i < temp_dice.size(); i += 2) {
            if (!Objects.equals(temp_dice.get(i), temp_dice.get((i + 1)))){
                return false;
            }
        }
        return true;
    }
    public boolean check_for_farkle() {
        return (number_of_specific_die(2) == 0 && number_of_specific_die(4) == 0 && check_for_three_of_a_kind() == 0 && check_for_four_of_a_kind() == 0 && !check_for_striaght() && !check_for_three_pairs());

    }





}
