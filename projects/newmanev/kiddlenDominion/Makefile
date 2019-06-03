CFLAGS = -Wall -fpic -coverage -lm -std=c99

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/

####################################################################

randomtestresults: randomtestadventurer.c randomtestcard1.c #randomtestcard2.c
	echo "randomtestadventurer.c:" >> randomtestresults.out
	gcc -o randomtestadventurer dominion.c rngs.c randomtestadventurer.c $(CFLAGS)
	./randomtestadventurer >> randomtestresults.out
	gcov randomtestadventurer.c -b >> randomtestresults.out

	echo "randomtestcard1.c:" >> randomtestresults.out
	gcc -o randomtestcard1 dominion.c rngs.c randomtestcard1.c $(CFLAGS)
	./randomtestcard1 >> randomtestresults.out
	gcov randomtestcard1.c -b >> randomtestresults.out

randomtestadventurer: randomtestadventurer.c dominion.o
	gcc -o randomtestadventurer dominion.c rngs.c randomtestadventurer.c $(CFLAGS)

randomtestcard1: randomtestcard1.c dominion.o
	gcc -o randomtestcard1 dominion.c rngs.c randomtestcard1.c $(CFLAGS)

####################################################################

testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

# unittestresults: unittestresults.out

unittestresults: unittest1.c unittest2.c unittest3.c dominion.o
	echo "unittest1.c:" >> unittestresults.out
	gcc -o unittest1 dominion.c rngs.c unittest1.c $(CFLAGS)
	./unittest1 >> unittestresults.out
	gcov dominion.c -b >> unittestresults.out 
	
	echo "unittest2.c:" >> unittestresults.out
	gcc -o unittest2 dominion.c rngs.c unittest2.c $(CFLAGS)
	./unittest2 >> unittestresults.out
	gcov dominion.c -b >> unittestresults.out 
	
	echo "unittest3.c:" >> unittestresults.out
	gcc -o unittest3 dominion.c rngs.c unittest3.c $(CFLAGS)
	./unittest3 >> unittestresults.out
	gcov dominion.c -b >> unittestresults.out 
	
unittest1: unittest1.c dominion.o
	gcc -o unittest1 dominion.c rngs.c unittest1.c $(CFLAGS)

unittest2: unittest2.c dominion.o
	gcc -o unittest2 dominion.c rngs.c unittest2.c $(CFLAGS)

unittest3: unittest3.c dominion.o
	gcc -o unittest3 dominion.c rngs.c unittest3.c $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

runtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player 

clean:
	rm -f *.o playdom.exe playdom player player.exe *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4 randomtestadventurer randomtestcard1 randomtestcard2 randomtestresults
