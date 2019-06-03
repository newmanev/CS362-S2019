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
    int old_treasure_count = 0;
    int new_treasure_count = 0;
    int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
    struct gameState G, T;
    struct gameState empty = {0};

    printf("********************************************\n");
    printf("\tAdventurer Unit Test\n");

    for (int j=0; j < 5000; j++) {

        G = empty;
        T = empty;

        int random_seed = rand() % 1000 + 1;
        int num_players = rand() % (MAX_PLAYERS-1) + 2;
        int okay = initializeGame(num_players, k, random_seed, &G);

        memcpy(&T, &G, sizeof(G));

        for (int i = 0; i < T.handCount[0]; i++) {
            if (T.hand[0][i] == (copper || silver || gold))
                old_treasure_count++;
        }

        // random variable for what's passed in
        int current_players = rand() % num_players;
        int drawn_treasure = rand() % 2;

        int card_played = adventurerCardEffect(&T, drawn_treasure, current_players);

        for (int i = 0; i < T.handCount[0]; i++) {
            if (T.hand[0][i] == (copper || silver || gold))
                    new_treasure_count++;
        }

        expected = 2;
        result = new_treasure_count - old_treasure_count;
        assert_helper("Test correct treasure card amount added to hand", result, expected);

    }

}