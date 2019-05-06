#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"
#include <stdlib.h>

int assert_helper (char *test_name, int result, int expect) {
  if(result != expect) {
    printf("Fail - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
    return 1;
  } else {
    printf("Pass - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
    return 0;
  }
}

int main () {
		int expected = -5, result = -5;
        int old_players_hand = 0;
        int new_players_hand = 0;
		int bonus = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
		struct gameState G;

        printf("********************************************\n");
		printf("\tGardens Card Test\n");

		initializeGame(2, k, 1, &G);

		int card_played = cardEffect(gardens, 0, 0, 0, &G, 0, &bonus);

		expected = -1;
		result = card_played;
		assert_helper("Test gardens result is correct", result, expected);

}