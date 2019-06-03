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
			int old_treasure_count = 0;
			int new_treasure_count = 0;
			int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
		struct gameState G;

        printf("********************************************\n");
		printf("\tAdventurer Unit Test\n");

		initializeGame(2, k, 1, &G);

		for (int i = 0; i < G.handCount[0]; i++) {
			if (G.hand[0][i] == (copper || silver || gold))
				old_treasure_count++;
		}
		int temphand[MAX_HAND];
		int card_played = adventurerEffect(0, &G, 0, 0, temphand, 0);

    for (int i = 0; i < G.handCount[0]; i++) {
	  	if (G.hand[0][i] == (copper || silver || gold))
				new_treasure_count++;
		}

		expected = 2;
		result = new_treasure_count - old_treasure_count;
		assert_helper("Test correct treasure card amount added to hand", result, expected);

}