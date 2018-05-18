package nl.hypothermic.lightser.test;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import nl.hypothermic.lightser.Lightser;
import nl.hypothermic.lightser.Lightser.DeserializeCallback;
import nl.hypothermic.lightser.Lightser.SerializeCallback;

/** 
 * <b>- Lightser Test Suite -</b><br><br>
 * JUnit test suite for Lightser v1.0.0
 * @author hypothermic
 */
/* 
 * MIT License
 * 
 * Copyright (c) 2018 hypothermic <admin@hypothermic.nl> (www.github.com/hypothermic)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@FixMethodOrder(MethodSorters.JVM)
public class TestSuite {
	
	private static final File sample = new File("src/test/resources/sample.ser");
	
	@Test public void Serialization() {
		Lightser.serialize(new int[] {0, 2, 4, 8}, sample, new SerializeCallback() {
			public void onSerializeSuccessful() {
				;
			}
			public void onSerializeFailed(Exception x) {
				x.printStackTrace();
				Assert.fail();
			}
		});
	}
	
	@Test public void Deserialization() {
		Lightser.deserialize(sample, new DeserializeCallback() {
			public void onDeserializeSuccessful(Serializable ser) {
				if (Arrays.toString((int[]) ser).equals("[0, 2, 4, 8]")) {
					;
				} else {
					Assert.fail("Content of serialization file did not match expected content");
				}
			}
			public void onDeserializeFailed(Exception x) {
				x.printStackTrace();
				Assert.fail();
			}
		});
	}
}
