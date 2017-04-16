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

class MD5Test extends TestSpec {
  final val TextToHash: String = "test"

  it should "hash using MD5" in {
    val hash: String = Hasher(TextToHash).md5.hex
    hash shouldBe "098f6bcd4621d373cade4e832627b4f6"
    hash.length shouldBe 32

    val result: Boolean = Hasher(TextToHash).md5.hash = hash
    result shouldBe true
  }
}
