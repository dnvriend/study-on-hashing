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

class CRC32Test extends TestSpec {
  final val TextToHash: String = "test"

  it should "hash using CRC32" in {
    val hash: String = Hasher(TextToHash).crc32.hex
    hash shouldBe "d87f7e0c"
    hash.length shouldBe 8

    val result: Boolean = Hasher(TextToHash).crc32.hash = hash
    result shouldBe true
  }
}
