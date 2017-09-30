'use strict';

/**
 * Convert a two's-complement negative to its JS numeric value
 */
function sign(value, pow) {
  const msb = 1 << pow;
  if ((value & msb) === 0) {
    return value;
  }
  let neg = -1 * (value & msb);
  neg += (value & (msb - 1));
  return neg;
}

module.exports = sign;