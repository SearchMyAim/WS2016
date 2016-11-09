#ifndef PROGK_UE03_BAAR_SIEVE_H
#define PROGK_UE03_BAAR_SIEVE_H

#include "configuration.h"

#define PRIME_NUMBER            0
#define NO_PRIME                1

/**
 *
 * @param limit value up to primes shall be found.
 * @param status returns following status codes:
 *          <b>SIEVE_OK</b> The function returned normally.
 *          <b>SIEVE_OUT_OF_MEMORY</b> Memory allocation failed.
 * @param primeCount returns the number of primes found in the given range.
 *        If the function fails the passed value is not changed.
 * @return pointer to an uint8_t array holding all found primes. Set bits represnting a prime number
 *         equivalent to their position. ((8 * Index) + SetBit) == Numeric value
 *         [0] = 0x51 (0101 0001) = Bits at position 1, 5 and 7 are set. Representing the numeric values 1, 5 and 7.
 *         [1] = 0x03 (0000 0110) = Bits at position 2 and 3 are set at index => (8*1) + 2 = '10' and (8*1)+3 = '11'
 */
uint8_t *sieve(uint64_t limit, int *status, size_t *primeCount);

#endif //PROGK_UE03_BAAR_SIEVE_H
