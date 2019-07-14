package de.mklinger.micro.streamcopy;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

/**
 * @author Marc Klinger - mklinger[at]mklinger[dot]de
 */
public class StreamCopyTest {
	@Test
	public void testCopy() throws IOException {
		final byte[] bytes = randomBytes();

		long copied;

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
				copied = StreamCopy.copy(in, out);
			}

			assertThat(out.toByteArray(), equalTo(bytes));
		}

		assertThat(copied, equalTo((long)bytes.length));
	}

	@Test
	public void testCopyBufSize() throws IOException {
		final byte[] bytes = randomBytes();

		long copied;

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
				copied = StreamCopy.copy(in, out, 1);
			}

			assertThat(out.toByteArray(), equalTo(bytes));
		}

		assertThat(copied, equalTo((long)bytes.length));
	}

	@Test
	public void testCopyByteArray() throws IOException {
		final byte[] bytes = randomBytes();

		byte[] out;
		try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
			out = StreamCopy.toByteArray(in);
		}

		assertThat(out, equalTo(bytes));
	}

	@Test
	public void testCopyString() throws IOException {
		final String string = "Ihr naht euch wieder, schwankende Gestalten!\n" +
				"Die früh sich einst dem trüben Blick gezeigt.";

		String out;
		try (ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8))) {
			out = StreamCopy.toString(in, StandardCharsets.UTF_8);
		}

		assertThat(out, equalTo(string));
	}

	private byte[] randomBytes() {
		final byte[] bytes = new byte[2345];
		ThreadLocalRandom.current().nextBytes(bytes);
		return bytes;
	}
}
