package ics321.assignment1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Path;

/**
 * @author Alice Chen Provides methods for loading and searching through a data
 *         file.
 */
public class Assignment1 {

	private FileReader file;
	private File binFile;

	/**
	 * If binaryLoadedFileName is not null, means binaryLoad was called and that
	 * data.bin can be read from (contains content)
	 */
	private String binaryLoadedFileName;

	/**
	 * The file name used by commands other than load. It is overwritten when load
	 * is called.
	 */
	private String loadedFileName;

	/**
	 * The character that is assumed to separate values in lines of files read by
	 * this class.
	 */
	private final String SEPARATOR = "\\|";

	/**
	 * load takes a fileName as an argument and reads in the CSV file at the given
	 * path. The data may not all fit in memory.
	 *
	 * @param fileName
	 *            The name of the file to be loaded.
	 */
	public void naiveLoad(String fileName) {

		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		this.loadedFileName = fileName;
		try {
			file = new FileReader(fileName);
			System.out.println("naiveLoad " + fileName);

		} catch (FileNotFoundException e) {
			System.out.println("cannot load " + fileName);
			e.printStackTrace();
		}
	}

	/**
	 * searchEq takes a columnNumber and a value and prints tuples that match the
	 * given value on the given column. More points will be given for faster return
	 * of this method.
	 *
	 * i.e., query with 3 F, what is printed out is, on the third column (pipes
	 * being the separator) look for the value F, and print out that line A.C.
	 *
	 * @param columnNumber
	 *            The column of a row to be checked.
	 * @param value
	 *            The value which column values are checked against.
	 */
	public void naiveSearchEq(int columnNumber, String value) {

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("naiveSearchEq col # " + columnNumber + " = " + value);
		FileReader fr = null;
		try {
			fr = new FileReader(loadedFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = "";
		boolean cont = true;

		if (fr != null) {

			try {
				int st = 0;

				while (cont) {

					if (fr.ready()) {
						st = fr.read();
					}

					char c = (char) st;

					if (!fr.ready()) { // read last line
						String alter = s;
						String[] arr = alter.split(SEPARATOR);

						if (arr[(columnNumber - 1)].equals(value)) {
							if(s.charAt(s.length()-1) == ('|')) {
								System.out.println(s);
							}else {
								System.out.println(s+"|");
							}
						}
						s = "";

						cont = false;
					} else if (!(c == '\n')) {
						s += c;
					} else {
						String alter = s;
						String[] arr = alter.split(SEPARATOR);
						if (arr[(columnNumber - 1)].equals(value)) {
							System.out.println(s);
							s = "";
						} else {
							s = "";
						}
					}

				}

			} catch (IOException sm) {

			}
		}
	}

	/**
	 * prints the rows of the table where the value in column number columnNum is
	 * equal to the given value. Column numbers start from one, while String.split
	 * splits the lines into a 0-based index.
	 * 
	 * Reads an entire line using bufferedReader to split into respective arrays
	 * then compares the values, and if the same, print the line
	 * 
	 * A.C.
	 * 
	 * @param columnNumber
	 *            the columnNumber to compare to the line (compare to value with
	 *            arr[columnNumber-1])
	 * 
	 * @param value
	 *            the value to find, if found, print the line
	 */

	public void naiveBufSearchEq(int columnNumber, String value) {

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("naiveBufSearchEq col # " + columnNumber + " = " + value);

		String s = "";
		FileReader fr = null;
		try {
			fr = new FileReader(loadedFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			while (br.ready()) {
				s = br.readLine();
				String store = s;
				String[] arr = store.split(SEPARATOR);
				if (arr[columnNumber - 1].equals(value)) {
					System.out.println(s);
				}

			}
		} catch (IOException e) {

		}
	}

	/**
	 * searchGtr takes a columnNumber and a value and prints tuples where the given
	 * column is greater than the given value. More points will be given for faster
	 * return of this method. A.C.
	 *
	 * @param columnNumber
	 *            The column of a row to be checked.
	 * @param value
	 *            The value which column values are checked against.
	 */
	public void naiveSearchGtr(int columnNumber, float value) {

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("naiveSearchGtr col #" + columnNumber + ">" + value);

		FileReader fr = null;
		try {
			fr = new FileReader(loadedFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = "";
		boolean cont = true;

		if (fr != null) {
			try {
				int st = 0;

				while (cont) {

					if (fr.ready()) {
						st = fr.read();
					}

					char c = (char) st;

					if (!fr.ready()) {
						String alter = s;
						String[] arr = alter.split(SEPARATOR);
						float num = Float.parseFloat(arr[(columnNumber) - 1]);
						if (num > value) {
							if(s.charAt(s.length()-1) == ('|')) {
								System.out.println(s);
							}else {
								System.out.println(s+"|");
							}			}
						s = "";
						cont = false;
					} else if (!(c == '\n')) {
						s += c;
					} else {
						String alter = s;
						String[] arr = alter.split(SEPARATOR);
						float num = Float.parseFloat(arr[(columnNumber) - 1]);

						if (num > value) {// if col > value, do
							System.out.println(s);
						}
						s = "";
					}
				}

			} catch (IOException sm) {

			}
		}
	}

	/**
	 * prints the rows of the table where the value in column number columnNum is
	 * greater than the given value. A.C
	 * 
	 * @param columnNumber
	 * @param value
	 */

	public void naiveBufSearchGtr(int columnNumber, float value) {

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("naiveBufSearchGtr col # " + columnNumber + " = " + value);

		String s = "";
		FileReader fr = null;
		try {
			fr = new FileReader(loadedFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		if (fr != null) {
			try {
				while (br.ready()) {
					s = br.readLine();
					String store = s;
					String[] arr = store.split(SEPARATOR);
					float val = Float.parseFloat(arr[columnNumber - 1]);
					if (val > value) {
						System.out.println(s);
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * binaryLoad transforms CSV to binary file. The filename of the binary file
	 * should be stored in your program.
	 * 
	 * @param fileName
	 *            the String to be loaded and converted to a binary file and read
	 */
	public void binaryLoad(String fileName) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("binaryLoad " + fileName);
		this.binaryLoadedFileName = "data.bin";
		binFile = new File(this.binaryLoadedFileName);
		BufferedReader in = null;
		DataOutputStream out = null;

		try {
			in = new BufferedReader(new FileReader(fileName));
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(binFile)));
			String s = "";
			do {
				if (in.ready()) {
					s = in.readLine(); // readLine from CSV
					out.writeUTF(s); // write line to bin
				} else {
					s = "";
				}
			} while (!(s.isEmpty()));
			out.close();

		} catch (IOException e) {
		}

	}

	/**
	 * prints the rows of the table where the value in column number columnNum is
	 * equal to the given value. Column numbers start from one.
	 * 
	 */
	public void binarySearchEq(int colN, String val) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("binarySearchEq col # " + colN + " = " + val);
		DataInputStream input = null;
		try {
			input = new DataInputStream(new FileInputStream(binFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (NullPointerException npe) {

		}
		String s = "";
		String toAlter = "";
		if (input != null) {
			try {
				while (input.available() > -1) {
					s = input.readUTF(); // reads an entire line, and is stored to array
					toAlter = s;
					String[] arr = toAlter.split(SEPARATOR);
					if (arr[colN - 1].equals(val)) {
						System.out.println(s);
					}
				}
			} catch (EOFException eof) {
			} catch (IOException ioe) {
			}
			try {
				input.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * prints the rows of the table where the value in column number columnNum is
	 * greater than the given value.
	 * 
	 */
	public void binarySearchGtr(int colN, float val) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		System.out.println("binarySearchGtr col # " + colN + " = " + val);
		DataInputStream input = null;
		try {
			input = new DataInputStream(new FileInputStream(binFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (NullPointerException npe) {
		}
		String s = "";
		String toAlter = "";

		try {
			while (input.available() >= -1) {
				s = input.readUTF(); // reads an entire line, and is stored to array
				toAlter = s;
				String[] arr = toAlter.split(SEPARATOR);
				float comp = Float.parseFloat(arr[colN - 1]);
				if (comp >= val) {
					System.out.println(s);
				}
			}
		} catch (EOFException eof) {// absolutely necessary
		} catch (IOException ioe) {
		}
		try {
			input.close();
		} catch (IOException e) {
		}
	}

}
