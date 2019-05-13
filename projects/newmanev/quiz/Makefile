CFLAGS = -Wall -fpic -coverage -lm -std=c99

default: testme

testme: testme.c
	echo "Random testing ouput: " >> testme.out
	gcc -o quiz testme.c $(CFLAGS)
	./quiz >> testme.out

clean:
	rm testme.out quiz *.gcov *.gcno *gcda