# number-theory

Number-theoretic functions in Clojure

Borrows some ideas from
http://planet.racket-lang.org/package-source/soegaard/math.plt/1/5/number-theory.ss.

## Usage

**divides? [a b]**

True if a divides b, in the sense of a mod b == 0.

**integer-power [a b]**

Raises a to the power of b, where b >= 0. a need not be positive.

**gcd [a b & more]**

Computes the greatest common divisor (gcd) of a and b and possibly
more integers. None of them may be zero.

## License

Copyright © 2014-2016 Brian Beckman

Distributed under the Apache 2.0 Public License.
http://www.apache.org/licenses/
