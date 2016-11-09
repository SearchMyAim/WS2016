#include <inttypes.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "configuration.h"
#include "sieve.h"

const char *PRINT_OPTION = "-p";

int main(int argc, char *argv[]) {
    int print = NO_PRINT_ARRAY;
    uint64_t limit = 0;

    switch(argc) {
        case 2:
            /* Only the limit of primes passed. */
            limit = strtoull(argv[1], NULL, 10);
            break;

        case 3:
            /* First argument MUST be the print option. */
            limit = strtoull(argv[2], NULL, 10);
            if(strcmp(argv[1], PRINT_OPTION)) {
                return SIEVE_INVALID_ARGUMENT;
            }
            print = PRIME_PRINT_ARRAY;
            break;

        default:
            return SIEVE_INVALID_ARGUMENT;
    }

    if(limit == 0) {
        return SIEVE_INVALID_ARGUMENT;
    }

    int status = SIEVE_OK;
    size_t primeCount = 0;
    uint8_t *primeArray = sieve(limit, &status, &primeCount);

    if(status == SIEVE_OK) {
        printf("\nPrime count: %zu \n", primeCount);

        if((primeArray != NULL) && (print == PRIME_PRINT_ARRAY)) {
            uint64_t fact = 8 * sizeof(uint8_t);
            for (uint64_t i = 2; i < limit; i++) {
                if((primeArray[i / fact] & (1 << (i % fact))) == PRIME_NUMBER) {
                    printf("%" PRIu64 " - ", i);
                }
            }
        }
    }

    if(primeArray != NULL) {
        /* If memory has been allocated free it at this point. */
        free(primeArray);
    }
    return status;
}
