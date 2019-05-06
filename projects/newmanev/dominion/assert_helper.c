#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>

int assert_helper (char *test_name, int result, int expect) {
  if(result != expect) {
    printf("Fail - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
    return 1;
  } else {
    printf("Pass - %s\n\tResult: %d\n\tExpected: %d\n", test_name, result, expect);
    return 0;
  }
}

