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
		int bonus = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine, remodel, smithy, village, baron, great_hall};
		struct gameState G;

        printf("********************************************\n");
		printf("\tAdventurer Card Test\n");

		initializeGame(2, k, 1, &G);

		int card_played = cardEffect(gardens, 0, 0, 0, &G, 0, &bonus);

		expected = 1;
		result = card_played;
		assert_helper("Test gardens result is correct", result, expected);

}