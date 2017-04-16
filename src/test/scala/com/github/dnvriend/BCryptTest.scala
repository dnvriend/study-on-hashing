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

class BCryptTest extends TestSpec {
  final val TextToHash: String = "test"

  it should "encrypt/validate bcrypt" in {
    val hash: String = Hasher(TextToHash).bcrypt(rounds = 10).hex
    val result: Boolean = Hasher(TextToHash).bcrypt(rounds = 10).hash = hash
    hash.length shouldBe 120
    result shouldBe true
  }
}
