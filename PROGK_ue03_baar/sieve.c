#include <stdio.h>
#include <inttypes.h>
#include <stdlib.h>
#include <math.h>
#include "sieve.h"

const uint64_t PRIME_NUMBER = 1;
const uint64_t NO_PRIME     = 0;
const uint64_t NOT_CHECKED  = 255;

uint64_t *sieve(uint64_t limit, int *status, size_t *primeCount) {
    /* Allocate the needed memory. */
    uint64_t *pMem = malloc(sizeof(uint64_t) * (limit + 1));
    if(pMem == NULL) {
        /* Out of memory. */
        *status = SIEVE_OUT_OF_MEMORY;
        return NULL;
    }

    /* Initialize the allocated memory to a defined value. */
    for(uint64_t i = 0; i < limit + 1; i++) {
        pMem[i] = NOT_CHECKED;
    }



    pMem[0] = NO_PRIME;
    pMem[1] = NO_PRIME;
    for(uint64_t i = 2; i < sqrt(limit); i++) {
        if(pMem[i] == NOT_CHECKED) {
            pMem[i] = PRIME_NUMBER;
            for(uint64_t x = i*i; x < limit; x += i) {
                pMem[x] = NO_PRIME;
            }
        }
    }

    uint64_t tPrimeCount = 0;
    for(uint64_t i = 2; i < limit; i++) {
        if(pMem[i] != NO_PRIME) {
            tPrimeCount++;
            pMem[i] = PRIME_NUMBER;
        }
    }


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

    *primeCount = tPrimeCount;
    *status = SIEVE_OK;

    free(pMem);
    return primeArray;
}