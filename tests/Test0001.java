package tests;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.stream.XMLStreamException;

import tools.XMLTools;

public class Test0001 {
	public static void main(String[] args) {
		try {
			// -- choose one --

			test01();

			// --
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void test01() throws IOException, XMLStreamException, URISyntaxException {
		String testDataFilePath = new File(Test0001.class.getResource("res/TestData0001.xml").toURI()).getCanonicalPath();
		System.out.println("< " + testDataFilePath);

		XMLTools.Node root = XMLTools.loadFromFile(testDataFilePath);

		XMLTools.writeToFile(root, "C:\\temp\\output.xml");
	}
}
