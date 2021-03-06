/**
 * Copyright 2014 National University of Ireland, Galway.
 *
 * This file is part of the SIREn project. Project and contact information:
 *
 *  https://github.com/rdelbru/SIREn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sindice.siren.index.codecs.block;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRef;

/**
 * Abstraction over the block decompression algorithm.
 */
public abstract class BlockDecompressor {

  /**
   * Return the window size over the {@link IntsRef} output buffer used during
   * decompression.
   * <p>
   * This is to avoid {@link ArrayIndexOutOfBoundsException} if the decompressor
   * is working on a buffer which size does not match a multiple of the window
   * size.
   * <p>
   * For example, {@link AForBlockDecompressor} is performing decompression using
   * a window of 32 integers. In the case of short postings list with
   * 5 integers, the instantiated output buffer must be a multiple of window
   * size, i.e., 32 in this case. If the postings list would contain 33 integers
   * instead, then the instantiated output buffer should be 64.
   */
  public abstract int getWindowSize();

  /**
   * Decompress the specified byte array into a list of integers.
   */
  public abstract void decompress(BytesRef input, IntsRef output);

}
