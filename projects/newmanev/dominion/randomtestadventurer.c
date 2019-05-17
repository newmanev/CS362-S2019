#include "dominion.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>
#include <time.h>

void assert_helper (char *test_name, int result, int expect, int* state) {
  if(result != expect && *state == 0) {
    printf("Fail - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
    *state += 1;
  } if (result == expect && *state == 1) {
    printf("Pass - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
  }
}

int inputNum() {

    int r = rand() % 5;

    return r;

}

int main () {
    srand(time(NULL));
    int expected = -5, result = -5;
    int old_treasure_count = 0;
    int new_treasure_count = 0;
    int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
    struct gameState G, T;

    printf("********************************************\n");
    printf("\tAdventurer Unit Test\n");
    SelectStream(2);
    PutSeed(3);

    int okay = initializeGame(3, k, 3, &G);
    printf("%d", okay);
    if (okay == -1) {
        printf("Fail");
        return 1;
    }

    int state=0;

    for (int j=0; j < 5000; j++) {


        memcpy(&T, &G, sizeof(G));

        for (int i = 0; i < T.handCount[0]; i++) {
            if (T.hand[0][i] == (copper || silver || gold))
                old_treasure_count++;
        }

        // random variable for what's passed in
        // int current_players = rand() % 2;

        int card_played = adventurerCardEffect(&T, 0, 0);

        for (int i = 0; i < T.handCount[0]; i++) {
            if (T.hand[0][i] == (copper || silver || gold))
                    new_treasure_count++;
        }

        expected = 2;
        result = new_treasure_count - old_treasure_count;
        assert_helper("Test correct treasure card amount added to hand", result, expected, &state);
    }

}