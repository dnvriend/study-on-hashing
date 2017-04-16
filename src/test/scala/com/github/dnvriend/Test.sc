import java.nio.ByteBuffer

import com.roundeights.hasher.Hasher

val TextToHash: String = "test"
val hash: String = Hasher(TextToHash).md5.hex
assert(hash == "098f6bcd4621d373cade4e832627b4f6")
assert(hash.length == 32)

import scala.util.hashing.MurmurHash3

val seed = MurmurHash3.stringHash("abcdefg")
seed.toByte

val b = Array(seed.toByte).map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}


ByteBuffer.allocate(200).putInt(20000000000).array()