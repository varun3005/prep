package org.vp.prep.misc;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Tail {
	
	        public static void main(String[] args) throws Exception {
	                Tail tail = new Tail();
	                tail.readLastNLines("D:/dict.txt",3);
	        }

			private void readLastNLines(String fileName, int lines) throws IOException {
				RandomAccessFile raf = null;
				try{
	                File file = new File(fileName);
	                raf = new RandomAccessFile(file, "rw");
	                long eof = raf.length();
					StringBuffer buf = new StringBuffer();
					raf.seek(eof);
					while(lines>0){
						eof = eof-1;
						if(eof<0){
							break;
						}
						raf.seek(eof);
						char ch = (char)raf.readByte();
						buf.append(ch);
						if(ch=='\n'){
							lines--;
						}
					}
					System.out.println(buf.reverse());
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					raf.close();
				}
			}
}


