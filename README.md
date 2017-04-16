# study-on-hashing
A small study project on hash-algorithms

## Introduction

## What is a hash function?
A [hash function](https://en.wikipedia.org/wiki/Hash_function) is any function that can be used to map data of arbitrary size to data of fixed size.
The values returned by a hash function are called 'hash values', 'hash codes', 'digests', or simply 'hashes'.

Say for example that [SHA-1](https://en.wikipedia.org/wiki/SHA-1) is a hash function and 'test' is a text that we want to hash then
'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3' would be the reduced-fix-length-string called the 'hash' that in almost
all cases will be the hex-string representation.

```
'test' => [SHA-1] => 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3'
```

For example in Scala:

```scala
import com.roundeights.hasher.Hasher

val TextToHash: String = "test"
val hash: String = Hasher(TextToHash).sha1.hex
assert(hash == "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3")
assert(hash.length == 40)
```

## What are hash functions used for?
Hashes are used to ensure data and message integrity, password validity, and are the basis of many other cryptographic systems.
Hashes are also used in a data structure called a hash table, widely used in computer software for rapid data lookup. Hash functions
also accelerate table or database lookup by detecting duplicated records in a large file. Hashes are also useful in cryptography.
A [cryptographic hash function](https://en.wikipedia.org/wiki/Cryptographic_hash_function) allows one to easily verify that some
input data maps to a given hash value, but if the input data is unknown, it is deliberately difficult to reconstruct it
(or equivalent alternatives) by knowing the stored hash value. This is used for assuring integrity of transmitted data,
and is the building block for [Hash-based Message Authentication Codes (HMAC)](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code),
which provide message authentication.

Hash functions are related to (and often confused with) checksums, check digits, fingerprints, lossy compression, randomization functions,
error-correcting codes, and ciphers. Although these concepts overlap to some extent, each has its own uses and requirements and is designed
and optimized differently.

## Non-cryptographic hash functions
Non cryptographic hash functions try to avoid collisions for input. Some aim to detect accidental changes in data(CRCs),
others try to put objects into different buckets in a hash table with as few collisions as possible. In exchange for weaker guarantees
they are typically (much) faster.

Some non-cryptographic hash functions are:

- MurmurHash: a non-cryptographic hash function suitable for general hash-based lookup:
- SpookyHash:
- JenkinsHash:
- CityHash:
- Java HashCode:

```scala
```

## Java HashCode
A hash function is an algorithm that maps data of variable length to data of fixed length. The values returned by hash functions are called
hash values, hash codes, hash sums, check sums or simply hashes. The Java hashcode() method does the same, so it is a hash function but
there are some other points to be considered while studying hashcodes:

- Hashcodes are typically used to enhance the performance of large collections of data.
- Collections such as HashSet and HashMap use the hashcode of an object to determine how exactly an object should be stored in the collection.
- When an object is to be searched from collections that use hashcodes, object’s hashcode is calculated first and with the help of it, object is retrieved.

Let us first understand what exactly are hashcodes. Consider the scenario in which we want to store multiple strings in a collection,
say we are storing it in HashSet.

We will number all the letters from 97 to 122 so 'a' = 97, b = 98, c = 99 and so on till z = 122, which is the same as the [ASCII table](http://www.asciitable.com/)
decimal value of a character. We will define our hash function such that it returns the sum of the value of all the characters in the string:

```scala
scala> 'a': Int
res0: Int = 97

scala> 'z': Int
res1: Int = 122

scala> def hasher(str: String): Int = str.sum
hasher: (str: String)Int

scala> hasher("a")
res2: Int = 97

scala> hasher("z")
res3: Int = 122

scala> hasher("zz")
res4: Int = 244

scala> hasher("darth bane")
res5: Int = 969

scala> hasher("darth zannah")
res6: Int = 1203

scala> hasher("darth tenebrous")
res7: Int = 1546

scala> hasher("darth plagueis")
res8: Int = 1421

scala> hasher("darth sidious")
res9: Int = 1331

scala> hasher("darth maul")
res10: Int = 994

scala> hasher("darth vader")
res11: Int = 1093

scala> hasher("unlimited power")
res12: Int = 1560

scala> hasher("USAMERICA")
res13: Int = 666

// lets stop geeking out :)
```


In the Java programming language, every class implicitly or explicitly provides a hashCode() method, which digests the data stored in an instance
of the class into a single hash value (a 32-bit signed integer).

This hash is used by other code when storing or manipulating the instance. The hash values are intended to be evenly distributed for varied inputs
for use in clustering. This property is important to the performance of hash tables and other data structures that store objects in groups ("buckets")
based on their computed hash values. Technically, in Java, hashCode() by default is a native method, meaning, it has the modifier 'native', as
it is implemented directly in the native code in the JVM.

All the classes inherit a basic hash scheme from the fundamental base class java.lang.Object, but instead many override this to provide a hash function
that better handles their specific data. Classes which provide their own implementation must override the object method public int hashCode().

The general contract for overridden implementations of this method is that they behave in a way consistent with the same object's equals() method:
that a given object must consistently report the same hash value (unless it is changed so that the new version is no longer considered "equal" to the old),
and that two objects which equals() says are equal must report the same hash value. There's no requirement that hash values be consistent between
different Java implementations, or even between different execution runs of the same program, and while two unequal objects having different hashes
is very desirable, this is not mandatory (that is, the hash function implemented doesn't need to be a perfect hash).

Scala generates the following hashcode for a case class:

```bash
$ scala -Xprint:typer
```

```scala
case class Person(name: String, age: Int)

override def hashCode(): Int = {
 var acc: Int = -889275714;
acc = scala.runtime.Statics.mix(acc, scala.runtime.Statics.anyHash(name));
acc = scala.runtime.Statics.mix(acc, age);
scala.runtime.Statics.finalizeHash(acc, 2)
};
```

## Java HashMap
The [Java HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) is a hash table based implementation of the Map interface.
This implementation provides all of the optional map operations, and permits null values and the null key. The HashMap class is roughly equivalent to
[Hashtable](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html), except that it is unsynchronized and permits nulls.
This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time.

The HashMap implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the
elements properly among the buckets. Iteration over collection views requires time proportional to the "capacity" of the HashMap instance
(the number of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high
(or the load factor too low) if iteration performance is important.

An instance of HashMap has two parameters that affect its performance: initial capacity and load factor. The capacity is the number of buckets
in the hash table, and the initial capacity is simply the capacity at the time the hash table is created. The load factor is a measure of how
full the hash table is allowed to get before its capacity is automatically increased. When the number of entries in the hash table exceeds
the product of the load factor and the current capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that
the hash table has approximately twice the number of buckets.

As a general rule, the default load factor (.75) offers a good tradeoff between time and space costs. Higher values decrease the space overhead
but increase the lookup cost (reflected in most of the operations of the HashMap class, including get and put). The expected number of entries
in the map and its load factor should be taken into account when setting its initial capacity, so as to minimize the number of rehash operations.
If the initial capacity is greater than the maximum number of entries divided by the load factor, no rehash operations will ever occur.

So a HashMap is basically build up of multiple lists (buckets) that entry objects. Each Entry object represents a key-value pair. The field next refers
to another Entry object if a bucket has more than one Entry. Sometimes it might happen that hash codes for 2 different objects are the same. In this case,
two objects will be saved in one bucket and will be presented as a linked list. The entry point is the more recently added object. This object refers to
another object with the next field and so on. The last entry refers to null.

[img]

When you add a value to the hashmap you use the key. The key is very important as it plays two roles:

1. The key is used to generate the hash number, a 32-bit integer in Java, and denotes the bucket where the group of objects will be stored,
2. The key is also used to check for equality, for example, if the bucket contains multiple objects from which the key resolves to the same hashcode,
   the key will be used to check which entry (each entry contains the key), contains a key that is equal to the key we are looking for.

So when we store a key/value pair, the key is used to calculate the hashcode and will resolve to a bucket number by means of hashcode % n where n is the
number of buckets. Next a check is done whether an entry already exists with the same key, if so the value will be overwritten, if not, a new
entry will be created.

To get an entry in the hashmap, you'll use the key. Again, the hashcode is calculated and the bucket is being resolved (hashcode % n ), and then
an equals check on the key will be done to resolve which entry in the bucket matches the key.

It is very important to create a good hashcode and equals method for you Java objects so be sure to read 'Effective Java by Joshua Bloch' Chapter 3,
item 8 and 9 that explains equals and hashcode in depth.

When you are using Scala, the 'case class' got you covered as it creates the necessary code for you. This is also true when you use Kotlin
as it uses the 'data class' structure.

## Trie
A [trie](https://en.wikipedia.org/wiki/Trie), also called digital tree and sometimes radix tree or prefix tree (as they can be searched by prefixes),
is a kind of search tree—an ordered tree data structure that is used to store a dynamic set or associative array where the keys are usually strings.
Unlike a binary search tree, no node in the tree stores the key associated with that node; instead, its position in the tree defines the key with
which it is associated. All the descendants of a node have a common prefix of the string associated with that node, and the root is associated with
the empty string. Values are not necessarily associated with every node. Rather, values tend only to be associated with leaves, and with some inner

A trie has a number of advantages over binary search trees. A trie can also be used to replace a hash table, over which it has the following advantages:

- Looking up data in a trie is faster in the worst case, O(m) time (where m is the length of a search string), compared to an imperfect hash table.
An imperfect hash table can have key collisions. A key collision is the hash function mapping of different keys to the same position in a hash table.
The worst-case lookup speed in an imperfect hash table is O(N) time, but far more typically is O(1), with O(m) time spent evaluating the hash.
- There are no collisions of different keys in a trie.
- Buckets in a trie, which are analogous to hash table buckets that store key collisions, are necessary only if a single key is associated with more than one value.
- There is no need to provide a hash function or to change hash functions as more keys are added to a trie.
- A trie can provide an alphabetical ordering of the entries by key.

Tries do have some drawbacks as well:

- Tries can be slower in some cases than hash tables for looking up data, especially if the data is directly accessed on a hard disk drive or some other
secondary storage device where the random-access time is high compared to main memory.
- Some keys, such as floating point numbers, can lead to long chains and prefixes that are not particularly meaningful. Nevertheless, a bitwise trie
can handle standard IEEE single and double format floating point numbers.
- Some tries can require more space than a hash table, as memory may be allocated for each character in the search string, rather than a single chunk of
memory for the whole entry, as in most hash tables.

## Hash Trie
[Hash tries](http://docs.scala-lang.org/overviews/collections/concrete-immutable-collection-classes.html#hash_tries) are a standard way to implement
immutable sets and maps efficiently. They are supported by class `scala.collection.immutable.HashMap`. Their representation is similar to vectors
in that they are also trees where every node has 32 elements or 32 subtrees. But the selection of these keys is now done based on hash code.
For instance, to find a given key in a map, one first takes the hash code of the key. Then, the lowest 5 bits of the hash code are used to select
the first subtree, followed by the next 5 bits and so on. The selection stops once all elements stored in a node have hash codes that differ from each
other in the bits that are selected up to this level.

Hash tries strike a nice balance between reasonably fast lookups and reasonably efficient functional insertions and deletions.
That’s why they underly Scala’s default implementations of immutable maps and sets. In fact, Scala has a further optimization for immutable
sets and maps that contain less than five elements. Sets and maps with one to four elements are stored as single objects that just contain the
elements (or key/value pairs in the case of a map) as fields. The empty immutable set and the empty immutable map is in each case a single object
- there’s no need to duplicate storage for those because an empty immutable set or map will always stay empty.

## Cryptographic Hash Function
A [cryptographic hash function](https://en.wikipedia.org/wiki/Cryptographic_hash_function) is a special class of hash function
that has certain properties which make it suitable for use in cryptography. It is a mathematical algorithm that maps data of
arbitrary size to a bit string of a fixed size (a hash function) which is designed to also be a one-way function, that is,
a function which is infeasible to invert.

The only way to recreate the input data from an ideal cryptographic hash function's output is to attempt a brute-force search
of possible inputs to see if they produce a match, or use a rainbow table of matched hashes.

Examples of cryptographic Hash functions are:

| Hash Function | Description |
| ------------- | ----------- |
| MD5 | The MD5 algorithm is a widely used hash function producing a 128-bit hash value. Although MD5 was initially designed to be used as a cryptographic hash function, it has been found to suffer from extensive vulnerabilities. It can still be used as a checksum to verify data integrity, but only against unintentional corruption. |
| SHA1 | SHA-1 (Secure Hash Algorithm 1) is a cryptographic hash function designed by the United States National Security Agency and is a U.S. Federal Information Processing Standard published by the United States NIST. SHA-1 produces a 160-bit (20-byte) hash value known as a message digest. A SHA-1 hash value is typically rendered as a hexadecimal number, 40 digits long. |
| SHA256 | SHA-2 includes significant changes from its predecessor, SHA-1. The SHA-2 family consists of six hash functions with digests (hash values) that are 224, 256, 384 or 512 bits: SHA-224, SHA-256, SHA-384, SHA-512, SHA-512/224, SHA-512/256. |
| SHA384 | A truncated version of SHA-512 |
| SHA512 | A SHA-2 hash functions computed with 64-bit words |
| HMAC-MD5 | |
| HMAC-SHA1 | |
| HMAC-SHA256 | |
| HMAC-SHA512 | |
| BCrypt | |
| CRC32 | CRC generates a value with a fixed and because of that it is occasionally used as a hash function |
| PBKDF2 | |

So every cryptographic hash function is a hash function, but not every hash function is a cryptographic hash.

A cryptographic hash function aims to guarantee a number of security properties. Most importantly that it's hard
to find collisions or pre-images and that the output appears random.

## Rainbow tables
A rainbow table is a precomputed table for reversing cryptographic hash functions, usually for cracking password hashes.
Tables are usually used in recovering a plaintext password up to a certain length consisting of a limited set of characters.
It is a practical example of a space/time trade-off, using less computer processing time and more storage than a brute-force
attack which calculates a hash on every attempt, but more processing time and less storage than a simple lookup table with
one entry per hash. Use of a key derivation function that employs a salt makes this attack infeasible.

Lets create a simple rainbow table:

```scala
scala> import com.roundeights.hasher.Hasher
import com.roundeights.hasher.Hasher

val rainbow: Map[String, String] = (for {
  t <- 'r' to 'v'
} yield s"tes$t").map(text => Hasher(text).sha1.hex -> text).toMap

rainbow: Map[String,String] = Map(
 6e79f4ca26f7ed989cc814f9dd8490fecbadd84f -> tesr,
 820541632c0db277202411e3ae43ccf73b15d6f8 -> tess,
 a94a8fe5ccb19ba61c4c0873d391e987982fbbd3 -> test,
 476b762a0615cccaf18485e66a64423656d92938 -> tesu,
 9ab18a95599d1ee071bc70f5b89eb3e8dee58ffc -> tesv
)

rainbow.get("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3")
res2: Option[String] = Some(test)
```

The lesson here is, always 'salt' your plain text passwords, ie. an algorithm that is unknown to the hacker, unknown to
the supplier of the password (ie the user) but known to your system (ie. implementation) which is the 'salt'.

## Each hash is unique but always repeatable
The word 'test' will hash to something that no other word hashes to, but it will always hash to the same thing.

## The function is 'one way'
If you are given the value of what 'test' hashes too but you didn't know what made it, you would never be able to find out
that 'test' was the original word, well, apart from looking the hash up in something called a [rainbow-table](https://en.wikipedia.org/wiki/Rainbow_table).

## MD5
The [MD5 algorithm](https://en.wikipedia.org/wiki/MD5) is a widely used hash function producing a 128-bit hash value.
Although MD5 was initially designed to be used as a cryptographic hash function, it has been found to suffer from extensive
vulnerabilities. It can still be used as a checksum to verify data integrity, but only against unintentional corruption.

```scala
import com.roundeights.hasher.Hasher

val TextToHash: String = "test"
val hash: String = Hasher(TextToHash).md5.hex
assert(hash == "098f6bcd4621d373cade4e832627b4f6")
assert(hash.length == 32)
```

## SHA-1
In cryptography, [SHA-1 (Secure Hash Algorithm 1)]((https://en.wikipedia.org/wiki/SHA-1)) is a cryptographic hash function
designed by the United States National Security Agency and is a U.S. Federal Information Processing Standard published by
the United States NIST.[3] SHA-1 produces a 160-bit (20-byte) hash value known as a message digest.
A SHA-1 hash value is typically rendered as a hexadecimal number, 40 digits long.

SHA-1 is no longer considered secure against well-funded opponents. In 2005, cryptanalysts found attacks on SHA-1 suggesting that
the algorithm might not be secure enough for ongoing use, and since 2010 many organizations have recommended its replacement
by SHA-2 or SHA-3. Microsoft, Google, Apple and Mozilla have all announced that their respective browsers will stop accepting
SHA-1 SSL certificates by 2017.

On February 23, 2017 CWI Amsterdam and Google announced they had performed a collision attack against SHA-1, publishing
two dissimilar PDF files which produce the same SHA-1 hash as proof of concept.

```scala
import com.roundeights.hasher.Hasher

val TextToHash: String = "test"
val hash: String = Hasher(TextToHash).sha1.hex
assert(hash == "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3")
assert(hash.length == 40)
```

## SHA-2
SHA-2 includes significant changes from its predecessor, SHA-1. The SHA-2 family consists of six hash functions with digests (hash values) that are 224, 256, 384 or 512 bits: SHA-224, SHA-256, SHA-384, SHA-512, SHA-512/224, SHA-512/256.

## Hash and Buckets
A bucket is a fast-access location (like an array index) that is the the result of the hash function. In context of a hash table or hash map
the key should be unique and maps to an associated value like so ("Foo" -> 42). The hash function is used to transform the key into the index (the hash)
of an array element (the slot or bucket) where the corresponding value is to be stored. It would not be good if the hash function is weak which means that
the resulting hash of a key would point to the same location; in that case you would find the incorrect value belonging to the key.

```bash
[key] -> [hash function] -> [hash value][buckets]
[   ] -> [hash function] -> [00        ][       ]
[foo] -> [hash function] -> [01        ][42     ]
[bar] -> [hash function] -> [02        ][72     ]
[pie] -> [hash function] -> [03        ][31415  ]
[   ] -> [hash function] -> [04        ][       ]
[   ] -> [hash function] -> [05        ][       ]
[   ] -> [hash function] -> [06        ][       ]
```

The idea with hashing is to turn a complex input value into a different value which can be used to rapidly extract or store data.

## Resources
- [How hashing works](http://www.metamorphosite.com/one-way-hash-encryption-sha1-data-software)
- [Consistent Hashing](http://blog.plasmaconduit.com/consistent-hashing/)
- [Choosing a good hash function 1/3](https://research.neustar.biz/2012/02/02/choosing-a-good-hash-function-part-1/)
- [Choosing a good hash function 2/3](https://research.neustar.biz/2012/02/02/choosing-a-good-hash-function-part-2/)
- [Choosing a good hash function 3/3](https://research.neustar.biz/2012/02/02/choosing-a-good-hash-function-part-3/)
- [An Illustrated Guide to Cryptographic Hashes - Steve Friedl](http://www.unixwiz.net/techtips/iguide-crypto-hashes.html)
- [MD5 Collision Demo](http://www.mscs.dal.ca/~selinger/md5collision/)
- [What are Hashcodes?](http://www.thejavageek.com/2013/06/27/what-are-hashcodes/)
- [How does a Java HashMap handle different objects with the same hash code?](http://stackoverflow.com/questions/6493605/how-does-a-java-hashmap-handle-different-objects-with-the-same-hash-code)