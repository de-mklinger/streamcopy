/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mklinger.micro.streamcopy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Stream copy utility.
 * <p>
 * The code was taken from commons-io:commons-io:2.6 IOUtils and stripped down
 * to the minimum.
 * </p>
 */
public class StreamCopy {
	/**
	 * Represents the end-of-file (or stream).
	 * @since 2.5 (made public)
	 */
	public static final int EOF = -1;

	/**
	 * The default buffer size ({@value}) to use for
	 * {@link #copyLarge(InputStream, OutputStream)}
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	/**
	 * Instances should NOT be constructed in standard programming.
	 */
	private StreamCopy() {
	}

	/**
	 * Copies bytes from a <code>InputStream</code> to an
	 * <code>OutputStream</code>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * <p>
	 * The buffer size is given by {@link #DEFAULT_BUFFER_SIZE}.
	 *
	 * @param input the <code>InputStream</code> to read from
	 * @param output the <code>OutputStream</code> to write to
	 * @return the number of bytes copied
	 * @throws NullPointerException if the input or output is null
	 * @throws IOException          if an I/O error occurs
	 */
	public static long copy(final InputStream input, final OutputStream output)
			throws IOException {
		return copy(input, output, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * Copies bytes from an <code>InputStream</code> to an <code>OutputStream</code> using an internal buffer of the
	 * given size.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a <code>BufferedInputStream</code>.
	 * <p>
	 *
	 * @param input the <code>InputStream</code> to read from
	 * @param output the <code>OutputStream</code> to write to
	 * @param bufferSize the bufferSize used to copy from the input to the output
	 * @return the number of bytes copied
	 * @throws NullPointerException if the input or output is null
	 * @throws IOException          if an I/O error occurs
	 */
	public static long copy(final InputStream input, final OutputStream output, final int bufferSize)
			throws IOException {
		return copy(input, output, new byte[bufferSize]);
	}

	/**
	 * Copies bytes from a <code>InputStream</code> to an
	 * <code>OutputStream</code>.
	 * <p>
	 * This method uses the provided buffer, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * <p>
	 *
	 * @param input the <code>InputStream</code> to read from
	 * @param output the <code>OutputStream</code> to write to
	 * @param buffer the buffer to use for the copy
	 * @return the number of bytes copied
	 * @throws NullPointerException if the input or output is null
	 * @throws IOException          if an I/O error occurs
	 */
	private static long copy(final InputStream input, final OutputStream output, final byte[] buffer)
			throws IOException {
		long count = 0;
		int n;
		while (EOF != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
}
