#include <stdio.h>
#include <inttypes.h>
#include <stdlib.h>
#include <math.h>
#include "sieve.h"

const uint8_t PRIME_NUMBER  = 0x00;
const uint8_t NO_PRIME      = 0x01;

uint64_t *sieve(uint64_t limit, int *status, size_t *primeCount) {
    /* Allocate the needed memory '0' initialized. */
    uint8_t *pMem = calloc((limit + 1), sizeof(uint8_t));
    if(pMem == NULL) {
        /* Memory allocation failed. */
        *status = SIEVE_OUT_OF_MEMORY;
        return NULL;
    }

    /* Mark all none-primes with the Eratosthenes algorithm. */
    for(uint64_t i = 2; i <= sqrt(limit); i++) {
        if(pMem[i] == PRIME_NUMBER) {
            pMem[i] = PRIME_NUMBER;
            for(uint64_t x = i*i; x < limit; x += i) {
                pMem[x] = NO_PRIME;
            }
        }
    }

    /* Run through the whole array and count the primes. */
    uint64_t tPrimeCount = 0;
    for(uint64_t i = 2; i < limit; i++) {
        if(pMem[i] == PRIME_NUMBER) {
            tPrimeCount++;
        }
    }

    /* If the print option is turned on allocate memory to hold the prime numbers up to the limit. */
    uint64_t *primeArray = NULL;
    if(*status ==  PRIME_PRINT_ARRAY) {
        primeArray = malloc(sizeof(uint64_t) * tPrimeCount);
        uint64_t idx = 0;
        for (uint64_t i = 2; i < limit; i++) {
            if (pMem[i] == PRIME_NUMBER) {
                primeArray[idx++] = i;
            }
        }
    }

    /* Write status and prime number counter at the passed pointers. */
    *primeCount = tPrimeCount;
    *status = SIEVE_OK;

    free(pMem);
    return primeArray;
}