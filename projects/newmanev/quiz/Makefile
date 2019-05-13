CFLAGS = -Wall -fpic -coverage -lm -std=c99

default: testme

testme: testme.c
	echo "Random testing ouput: " >> testme.out
	gcc -o testme testme.c $(CFLAGS)
	./testme >> testme.out

clean:
	rm testme.out testme *.gcov *.gcno *gcda