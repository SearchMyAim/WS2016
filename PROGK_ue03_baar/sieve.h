#ifndef PROGK_UE03_BAAR_SIEVE_H
#define PROGK_UE03_BAAR_SIEVE_H

#include "configuration.h"

/**
 *
 * @param limit value up to primes shall be found.
 * @param status returns following status codes:
 *          <b>SIEVE_OK</b> The function returned normally.
 *          <b>SIEVE_OUT_OF_MEMORY</b> Memory allocation failed.
 * @param primeCount returns the number of primes found in the given range.
 *        If the function fails the passed value is not changed.
 * @return pointer to an array holding all found primes.
 */
uint64_t *sieve(uint64_t limit, int *status, size_t *primeCount);

#endif //PROGK_UE03_BAAR_SIEVE_H