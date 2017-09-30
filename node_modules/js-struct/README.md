# js-struct

Simplified access to complex byte structures in JavaScript.

js-struct makes it easy to access data in binary buffers. Multi-byte fields,
byte arrays, and C-style structs can all be extracted from typed arrays.

```js
const Inode = Struct([
  Type.uint16('mode'),
  Type.uint16('uid'),
  Type.uint32('size'),
  Type.uint16('gid'),
  Type.uint32('flags'),
  Type.array(Block, NUM_BLOCKS)('blocks'),
]);
```

## Installing

`npm install js-struct`

## Getting started

js-struct provides tools to pull usable JS primitives, arrays, and objects out of raw bytes. All data is designed to be fetched from `Uint8Arrays`, which can wrap ECMAScript `ArrayBuffers` or Node.js `Buffers`.

The library contains three ways to structure the data: pre-defined Types, Arrays, and custom Structs. These can be composed in various ways to describe the layout of byte data.

```js
// Read the unsigned 16-bit number at index 10 of `arr`
let num = Type.uint16.read(arr, 10);

// Fetch an array of 4 bytes, starting at index 0
let bytes = Type.array(Type.byte, 4).read(arr, 0);

// Retrieve an array of 10 Points
const Point = Struct([
  Type.int32('x'),
  Type.int32('y'),
]);
let points = Type.array(Point, 10).read(arr, 0);
```

## Supported types

The following types are available as properties on `require('js-struct').Types`

 - `uint8`, unsigned 8-bit integer
 - `byte`, alias for `uint8`
 - `int8`, signed 8-bit integer
 - `uint16`, unsigned 16-bit integer
 - `ushort`, alias for `uint16`
 - `int16`, signed 16-bit integer
 - `short`, alias for `uint16`
 - `uint32`, unsigned 32-bit integer
 - `ulong`, alias for `uint32`
 - `int32`, signed 32-bit integer
 - `long`, alias for `int32`
 - `char`, similar to `byte`, but renders as a String

Any of these types can be read directly from an `Uint8Array`, with the `.read(source, index)` method. As parameters, it takes the typed array, and a byte-aligned index to begin reading from.

An array of any type can be constructed as well, with `Type.array()`. It is a method that takes a type, and a numeric size. Arrays share the same `.read()` method as the standard types.

Finally, it is possible to construct more complex objects with `require('js-struct').Struct`. `Struct` is a method that takes an array of fields.

To allow naming of fields on a Struct, all Types, Arrays, and Structs themselves are also functions that take a field name.

```js
let s = Struct([
  Type.uint8('fieldA'),
  Type.uint8('fieldB'),
]);

// Calling s.read() will return an object with two fields

console.log(s.read(arr, 0));
// {
//   fieldA: ...,
//   fieldB: ...,
// }
```
