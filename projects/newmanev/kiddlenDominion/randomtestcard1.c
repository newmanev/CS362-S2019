#include "dominion.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>
#include <time.h>

void assert_helper (char *test_name, int result, int expect) {
  if(result != expect) {
    printf("Fail - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
  } 
  else {
    printf("Pass - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
  }
}

int main () {
    srand(time(NULL));
    int expected = -5, result = -5;
    int old_hand_count = 0;
    int new_hand_count = 0;
    int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
    struct gameState G, T;
    struct gameState empty = {0};

    printf("********************************************\n");
    printf("\tSmithy Random Test\n");

    for (int j=0; j < 5000; j++) {

        G = empty;
        T = empty;

        int random_seed = rand() % 1000 + 1;
        int num_players = 4;
        int okay = initializeGame(num_players, k, random_seed, &G);

        memcpy(&T, &G, sizeof(G));

        // random variable for what's passed in
        int current_players = rand() % num_players;
        int hand_pos = rand() % 2;

        old_hand_count = T.handCount[current_players];

        int card_played = smithyEffect(current_players, &T, hand_pos, 0);

        new_hand_count = T.handCount[current_players];

        expected = 2;
        result = new_hand_count - old_hand_count;
        assert_helper("Test correct treasure card amount added to hand", result, expected);

    }

}