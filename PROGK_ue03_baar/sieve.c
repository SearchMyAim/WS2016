#include <stdio.h>
#include <inttypes.h>
#include <stdlib.h>
#include <math.h>
#include "sieve.h"

uint64_t *sieve(uint64_t limit, int *status, size_t *primeCount) {
    const uint8_t sizeUint8 = sizeof(uint8_t);

    /* Allocate the needed memory '0' initialized. */
    uint8_t *pMem = calloc(((limit + 1) / sizeUint8), sizeUint8);
    if(pMem == NULL) {
        /* Memory allocation failed. */
        *status = SIEVE_OUT_OF_MEMORY;
        return NULL;
    }

    /* Mark all none-primes with the Eratosthenes algorithm. */
    uint64_t fact = 8 * sizeUint8;


    pMem[0] = 0x01; //1 doesn't count as prime.
    for(uint64_t i = 2; i <= sqrt(limit); i++) {
        if((pMem[i / fact] & (1 << (i % fact))) == PRIME_NUMBER) {
            for(uint64_t x = i*i; x < limit; x += i) {
                pMem[x / fact] |= (NO_PRIME << (x % fact));
            }
        }
    }

    /* Run through the whole array and count the primes. */
    uint64_t tPrimeCount = 0;
    for(uint64_t i = 2; i < limit; i++) {
        if((pMem[i / fact] & (1 << (i % fact))) == PRIME_NUMBER) {
            tPrimeCount++;
        }
    }

    /* Write status and prime number counter at the passed pointers. */
    *primeCount = tPrimeCount;
    *status = SIEVE_OK;
    return pMem;
}