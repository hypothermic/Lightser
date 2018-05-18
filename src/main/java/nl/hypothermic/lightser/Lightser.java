package nl.hypothermic.lightser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/** 
 * <b>- Lightser -</b><br><i>(lightweight - serialization)</i><br><br>
 * Easy to use and lightweight serialization engine.
 * Fully asynchronous, featuring easy-to-use callbacks.
 * @author hypothermic
 * @version 1.0.0
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

public class Lightser {
	
	/**
	 * Callback interface for serialization
	 */
	public interface SerializeCallback {
		void onSerializeSuccessful();
		void onSerializeFailed(Exception x);
	}

	/**
	 * Serialize an object to a path <br>
	 * @param obj Object to serialize
	 * @param path Where to store the serialized file
	 * @param ret Return callback with results
	 */
	public static void serialize(Serializable obj, File path, SerializeCallback ret) {
		new SerializeThread(obj, path, ret).run();
	}
	
	/**
	 * Callback interface for deserialization
	 */
	public interface DeserializeCallback {
		void onDeserializeSuccessful(Serializable ser);
		void onDeserializeFailed(Exception x);
	}
	
	/**
	 * Deserialize an object from a path <br>
	 * @param path Path to serialized file
	 * @param ret Return callback with results
	 */
	public static void deserialize(File path, DeserializeCallback ret) {
		new DeserializeThread(path, ret).run();
	}
	
	private static class SerializeThread implements Runnable {
		
		private Serializable obj;
		private File path;
		private SerializeCallback ret;
		
		public SerializeThread(Serializable obj, File path, SerializeCallback ret) {
			this.obj = obj;
			this.path = path;
			this.ret = ret;
		}
		
		public void run() {
			try {
				FileOutputStream fos = new FileOutputStream(path);
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(obj);
				out.close();
				fos.close();
			} catch (Exception x) {
				ret.onSerializeFailed(x);
				return;
			}
			ret.onSerializeSuccessful();
		}
	}
	
	private static class DeserializeThread implements Runnable {
		
		private File path;
		private DeserializeCallback ret;
		
		public DeserializeThread(File path, DeserializeCallback ret) {
			this.path = path;
			this.ret = ret;
		}
		
		public void run() {
			try {
				FileInputStream fis = new FileInputStream(path);
				ObjectInputStream in = new ObjectInputStream(fis);
				Serializable ser = (Serializable) in.readObject();
				in.close();
				fis.close();
				ret.onDeserializeSuccessful(ser);
			} catch (Exception x) {
				ret.onDeserializeFailed(x);
			}
		}
	}
}
