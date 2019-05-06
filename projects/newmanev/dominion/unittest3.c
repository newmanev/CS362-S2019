#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include "rngs.h"
#include "assert_helper.c"

int main () {
		int expected = -5, result = -5;
        int old_players_hand = 0;
        int new_players_hand = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
		struct gameState G;

        printf("********************************************\n");
		printf("\tSmithy Card Test\n");

		initializeGame(2, k, 1, &G);

		old_players_hand = G.handCount[0];
		int card_played = smithyCardEffect(&G, 0, 0);
		new_players_hand = G.handCount[0];

		expected = 2;
		result = new_players_hand - old_players_hand;
		assert_helper("Test correct amount of drawn cards", result, expected);

}