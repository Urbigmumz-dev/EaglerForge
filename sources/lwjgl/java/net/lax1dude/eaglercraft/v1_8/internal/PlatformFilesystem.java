package net.lax1dude.eaglercraft.v1_8.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.v1_8.internal.lwjgl.LWJGLEntryPoint;
import net.lax1dude.eaglercraft.v1_8.internal.vfs2.EaglerFileSystemException;
import net.lax1dude.eaglercraft.v1_8.internal.vfs2.VFSIterator2.BreakLoop;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.sp.server.internal.lwjgl.DesktopIntegratedServer;

/**
 * Copyright (c) 2022-2024 lax1dude. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
public class PlatformFilesystem {

	public static final Logger logger = LogManager.getLogger("PlatformFilesystem");

	public static final File filesystemRoot = (new File("filesystem/sp")).getAbsoluteFile();

	public static void initialize() {
		assertThread();
		if(!filesystemRoot.isDirectory() && !filesystemRoot.mkdirs()) {
			throw new EaglerFileSystemException("Could not create directory for virtual filesystem: " + filesystemRoot.getAbsolutePath());
		}
	}

	private static void assertThread() {
		if(Thread.currentThread() != DesktopIntegratedServer.serverThread) {
			throw new UnsupportedOperationException("[DEBUG CHECK] VFS2 is currently only initialized for server contexts!");
		}
	}

	public static boolean eaglerDelete(String pathName) {
		assertThread();
		File f = getJREFile(pathName);
		if(!f.exists()) {
			logger.warn("Tried to delete file that doesn't exist: \"{}\"", pathName);
			return false;
		}
		if(f.delete()) {
			deleteParentIfEmpty(f);
			return true;
		}
		return false;
	}

	public static ByteBuffer eaglerRead(String pathName) {
		assertThread();
		File f = getJREFile(pathName);
		if(f.isFile()) {
			long fileSize = f.length();
			if(fileSize > 2147483647L) throw new EaglerFileSystemException("Too large: " + fileSize + " @ " + f.getAbsolutePath());
			ByteBuffer buf = PlatformRuntime.allocateByteBuffer((int)fileSize);
			try(FileInputStream is = new FileInputStream(f)) {
				byte[] copyBuffer = new byte[4096];
				int i;
				while((i = is.read(copyBuffer, 0, copyBuffer.length)) != -1) {
					buf.put(copyBuffer, 0, i);
				}
				if(buf.remaining() > 0) {
					throw new EaglerFileSystemException("ERROR: " + buf.remaining() + " bytes are remaining after reading: " + f.getAbsolutePath());
				}
				buf.flip();
				ByteBuffer tmp = buf;
				buf = null;
				return tmp;
			}catch (IOException e) {
				throw new EaglerFileSystemException("Failed to read: " + f.getAbsolutePath(), e);
			}catch(ArrayIndexOutOfBoundsException ex) {
				throw new EaglerFileSystemException("ERROR: Expected " + fileSize + " bytes, buffer overflow reading: " + f.getAbsolutePath(), ex);
			}finally {
				if(buf != null) {
					PlatformRuntime.freeByteBuffer(buf);
				}
			}
		}else {
			throw new EaglerFileSystemException("Not a file: " + f.getAbsolutePath());
		}
	}

	public static void eaglerWrite(String pathName, ByteBuffer data) {
		assertThread();
		File f = getJREFile(pathName);
		File p = f.getParentFile();
		if(!p.isDirectory()) {
			if(!p.mkdirs()) {
				throw new EaglerFileSystemException("Could not create parent directory: " + p.getAbsolutePath());
			}
		}
		try(FileOutputStream fos = new FileOutputStream(f)) {
			byte[] copyBuffer = new byte[4096];
			int i;
			while((i = data.remaining()) > 0) {
				if(i > copyBuffer.length) {
					i = copyBuffer.length;
				}
				data.get(copyBuffer, 0, i);
				fos.write(copyBuffer, 0, i);
			}
		}catch (IOException e) {
			throw new EaglerFileSystemException("Failed to write: " + f.getAbsolutePath(), e);
		}
	}

	public static boolean eaglerExists(String pathName) {
		assertThread();
		return getJREFile(pathName).isFile();
	}

	public static boolean eaglerMove(String pathNameOld, String pathNameNew) {
		assertThread();
		File f1 = getJREFile(pathNameOld);
		File f2 = getJREFile(pathNameNew);
		if(f2.exists()) {
			logger.warn("Tried to rename file \"{}\" to \"{}\" which already exists! File will be replaced");
			if(!f2.delete()) {
				return false;
			}
		}
		if(f1.renameTo(f2)) {
			deleteParentIfEmpty(f1);
			return true;
		}
		return false;
	}

	public static int eaglerCopy(String pathNameOld, String pathNameNew) {
		assertThread();
		File f1 = getJREFile(pathNameOld);
		File f2 = getJREFile(pathNameNew);
		if(!f1.isFile()) {
			return -1;
		}
		if(f2.isDirectory()) {
			throw new EaglerFileSystemException("Destination file is a directory: " + f2.getAbsolutePath());
		}
		File p = f2.getParentFile();
		if(!p.isDirectory()) {
			if(!p.mkdirs()) {
				throw new EaglerFileSystemException("Could not create parent directory: " + p.getAbsolutePath());
			}
		}
		int sz = 0;
		try(FileInputStream is = new FileInputStream(f1)) {
			try(FileOutputStream os = new FileOutputStream(f2)) {
				byte[] copyBuffer = new byte[4096];
				int i;
				while((i = is.read(copyBuffer, 0, copyBuffer.length)) != -1) {
					os.write(copyBuffer, 0, i);
					sz += i;
				}
			}
		}catch (IOException e) {
			throw new EaglerFileSystemException("Failed to copy \"" + f1.getAbsolutePath() + "\" to file \"" + f2.getAbsolutePath() + "\"", e);
		}
		return sz;
	}

	public static int eaglerSize(String pathName) {
		assertThread();
		File f = getJREFile(pathName);
		if(f.isFile()) {
			long fileSize = f.length();
			if(fileSize > 2147483647L) throw new EaglerFileSystemException("Too large: " + fileSize + " @ " + f.getAbsolutePath());
			return (int)fileSize;
		}else {
			return -1;
		}
	}

	public static void eaglerIterate(String pathName, VFSFilenameIterator itr, boolean recursive) {
		assertThread();
		try {
			iterateFile(pathName, getJREFile(pathName), itr, recursive);
		}catch(BreakLoop ex) {
		}
	}

	private static void iterateFile(String pathName, File f, VFSFilenameIterator itr, boolean recursive) {
		if(!f.exists()) {
			return;
		}
		if(!f.isDirectory()) {
			itr.next(pathName);
			return;
		}
		File[] fa = f.listFiles();
		for(int i = 0; i < fa.length; ++i) {
			File ff = fa[i];
			String fn = pathName + "/" + ff.getName();
			if(ff.isDirectory()) {
				if(recursive) {
					iterateFile(fn, ff, itr, true);
				}
			}else {
				itr.next(fn);
			}
		}
	}

	private static File getJREFile(String path) {
		return new File(filesystemRoot, path);
	}

	private static void deleteParentIfEmpty(File f) {
		String[] s;
		while((f = f.getParentFile()) != null && (s = f.list()) != null && s.length == 0) {
			f.delete();
		}
	}
}
