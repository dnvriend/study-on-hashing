/*
 * Copyright 2017 Dennis Vriend
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dnvriend

import com.roundeights.hasher.Hasher

class ShaHashTest extends TestSpec {
  final val TextToHash: String = "test"

  it should "hash a text to a hex string using SHA-1" in {
    val hash: String = Hasher(TextToHash).sha1.hex
    hash shouldBe "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"
    hash.length shouldBe 40
  }

  it should "hash a text using HMAC-MD5" in {
    val hash: String = Hasher(TextToHash).hmac(key = "abc").md5.hex
    hash shouldBe "a943114ac3511f4f5e6c4704b91de15d"
    hash.length shouldBe 32
  }

  it should "hash a text using HMAC-SHA-1" in {
    val hash: String = Hasher(TextToHash).hmac(key = "abc").sha1.hex
    hash shouldBe "3c38c442c961e29ca778a4e5927c596b750d3e67"
    hash.length shouldBe 40
  }

  it should "hash a text using HMAC-SHA-256" in {
    val hash: String = Hasher(TextToHash).hmac(key = "abc").sha256.hex
    hash shouldBe "d64ccf0d4b1449153d78215f9ff9b90ec3730de1fd2b357e153026c9a3fada96"
    hash.length shouldBe 64
  }

  it should "hash a text using HMAC-SHA-512" in {
    val hash: String = Hasher(TextToHash).hmac(key = "abc").sha512.hex
    hash shouldBe "c55194bd4959a626fb2f2f18b6e84a870b3e19f479b57d8837761cc90819592e70d2cb5031f91108f14e8afb01eb42fae785f248c47fab9302c1244fba57971d"
    hash.length shouldBe 128
  }

  it should "hash a text using SHA-2 SHA-256" in {
    val hash: String = Hasher(TextToHash).sha256.hex
    hash shouldBe "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"
    hash.length shouldBe 64
  }

  it should "hash a text using SHA-2 SHA-384" in {
    val hash: String = Hasher(TextToHash).sha384.hex
    hash shouldBe "768412320f7b0aa5812fce428dc4706b3cae50e02a64caa16a782249bfe8efc4b7ef1ccb126255d196047dfedf17a0a9"
    hash.length shouldBe 96
  }

  it should "hash a text using SHA-2 SHA-512" in {
    val hash: String = Hasher(TextToHash).sha512.hex
    hash shouldBe "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff"
    hash.length shouldBe 128

    val result = Hasher(TextToHash).sha512.hash = hash
    result shouldBe true
  }
}
