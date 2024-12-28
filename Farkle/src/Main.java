import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/*
*** The Game of Farkle

A single 2 is worth 10 points.
A single 4 is worth 5 points.
A straight (1-2-3-4-5-6) is worth 150 points.
Three of a kind is worth 10 times the number rolled (eg three 4s = 40 points).
Four of a kind is worth 20 times the number rolled   (eg four 4’s = 80 points).
Three pairs are worth 150 points

 */
public class Main {
    static ArrayList<Player> player_list = new ArrayList<>();
    static int point_of_singleTwo = 10;
    static int point_of_singleFour = 5;
    static int point_of_straight = 150;

    //do this later...

    public static void main(String[] args) {

        // Create a player object for each player and add it to the ArrayList.
        int numOfPlayers = count_players();
        for (int i = 0; i < numOfPlayers; i++) {
            player_list.add(new Player());
        }


        while (!check_game_over()){
            for (Player player: player_list) {
                turn(player);
                                }
            }
        }


    public static void turn (Player player) {

        String response = getPlayerResponse("Type anything to roll (type 'end' to end your turn) ");

        while (!response.equals("end")) {

            System.out.println("You rolled " + player.rollDice());

            while (player.number_of_specific_die(2) > 0) {

                do {
                    do {
                        response = getPlayerResponse("Do you want to bank a 2 (y/n)");
                    } while (!response.equals("y") && !response.equals("n"));

                    if (response.equals("y")) {
                        player.remove_specific_die(2, 1);
                        player.setScore(player.getScore() + point_of_singleTwo);
                        System.out.println(player.getScore());
                    } else {
                        break;
                    }

                } while (player.number_of_specific_die(2) > 0);

            }
            if (player.number_of_specific_die(4) > 0) {
                do {
                    do {
                        response = getPlayerResponse("Do you want to bank a 4 (y/n)");
                    } while (!response.equals("y") && !response.equals("n"));

                    if (response.equals("y")) {
                        player.remove_specific_die(4, 1);
                        player.setScore(player.getScore() + point_of_singleFour);
                        System.out.println(player.getScore());
                    } else {
                        break;
                    }

                } while (player.number_of_specific_die(4) > 0);
            }

            int three_of_a_kind_value = player.check_for_three_of_a_kind();

            if (three_of_a_kind_value != 0) {
                do {
                    response = getPlayerResponse("You have 3 of " + three_of_a_kind_value + ". Would you like to bank it for " + three_of_a_kind_value * 10 + "? (y/n)");
                } while (!response.equals("y") && !response.equals("n"));

                if (response.equals("y")) {
                    player.remove_specific_die(three_of_a_kind_value, 3);
                    player.setScore(player.getScore() + three_of_a_kind_value * 10);
                    System.out.println(player.getScore());
                } else {
                    break;
                }

            }

            int four_of_a_kind_value = player.check_for_four_of_a_kind();

            if (four_of_a_kind_value != 0) {
                do {
                    response = getPlayerResponse("You have 4 of " + four_of_a_kind_value + ". Would you like to bank it for " + four_of_a_kind_value * 20 + "? (y/n)");
                } while (!response.equals("y") && !response.equals("n"));

                if (response.equals("y")) {
                    player.remove_specific_die(four_of_a_kind_value, 4);
                    player.setScore(player.getScore() + four_of_a_kind_value * 20);
                    System.out.println(player.getScore());
                } else {
                    break;
                }

            }

            if (player.check_for_striaght()) {
                System.out.println("Congrats! You got a straight! Here's 150 points!");
                player.setScore(player.getScore() + 150);
                System.out.println(player.getScore());
            }

            if (player.check_for_farkle()) {
                System.out.println("You Farkled. You lose all your points.");
                player.setScore(0);
            }


        }



    }


    public static String getPlayerResponse(String printStatement){
        Scanner response_object = new Scanner(System.in);

        System.out.println(printStatement);
        return response_object.nextLine();
    }

    public static int count_players () {
        Scanner scanner_object_for_count_players = new Scanner(System.in);
        System.out.println("Number of Players: ");

        String number_of_players = scanner_object_for_count_players.nextLine();

        //Parse to Integer and return
        return Integer.parseInt(number_of_players);

    }

    public static boolean check_game_over () {
        for (Player player : player_list) {
            if (player.getScore() == 300) {
                return true;
            }
        }
        return false;
    }


}
