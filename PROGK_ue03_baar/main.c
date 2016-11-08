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

    if(limit >= UINT32_MAX) {
        uint64_t max = (limit * ((limit == UINT32_MAX) ? 1 : 2)) / 1000000;
        printf(        "\n!!------------ Exceeding 4GB Memory ------------!!"
                       "\n!! If you go on more then 4GB RAM are necessary !!"
                       "\n!! Memory usage: %" PRIu64 " Megabyte"
                       "\n!! Do you want to go on? [Y/N]:", max);
        char go = 'n';
        scanf("%c", &go);
        if((go != 'Y') && (go != 'y')) {
            return SIEVE_OUT_OF_MEMORY;
        }
    }

    int status = print;
    size_t primeCount = 0;
    size_t *primeArray = sieve(limit, &status, &primeCount);

    if(status == SIEVE_OK) {
        printf("\nPrime count: %" PRIu64 "\n", primeCount);

        if((primeArray != NULL) && (print == PRIME_PRINT_ARRAY)) {
            for(uint64_t i = 0; i < primeCount; i++) {
                printf("%" PRIu64 " - ", primeArray[i]);
            }
        }
    }

    if(primeArray != NULL) {
        free(primeArray);
    }

    return status;
}
