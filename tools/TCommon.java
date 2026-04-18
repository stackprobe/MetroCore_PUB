package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class TCommon {
	private TCommon() {
	}

	// バイナリファイル open 読み込み
    public static FileInputStream openBinaryFileForRead(String file) throws IOException {
        return new FileInputStream(file);
    }

	// バイナリファイル open 書き出し(新規 or 上書き)
    public static FileOutputStream openBinaryFileForWrite(String file) throws IOException {
        return new FileOutputStream(file, false); // 上書き
    }

	// バイナリファイル open 書き出し(追記)
    public static FileOutputStream openBinaryFileForAppend(String file) throws IOException {
        return new FileOutputStream(file, true); // 追記
    }

	// テキストファイル open 読み込み
    public static BufferedReader openTextFileForRead(String file, Charset encoding) throws IOException {
        return new BufferedReader(
            new InputStreamReader(
                new FileInputStream(file),
                encoding
            )
        );
    }

	// テキストファイル open 書き出し(新規 or 上書き)
    public static BufferedWriter openTextFileForWrite(String file, Charset encoding) throws IOException {
        return new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(file, false),
                encoding
            )
        );
    }

	// テキストファイル open 書き出し(追記)
    public static BufferedWriter openTextFileForAppend(String file, Charset encoding) throws IOException {
        return new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(file, true),
                encoding
            )
        );
	}

    // バイナリ全部読み込み
    public static byte[] readAllBytes(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    // テキスト全部読み込み（String）
    public static String readAllText(String filePath, Charset encoding) throws IOException {
        return new String(readAllBytes(filePath), encoding);
    }

    // 行単位で読み込み
    public static List<String> readAllLines(String filePath, Charset encoding) throws IOException {
        return Files.readAllLines(Paths.get(filePath), encoding);
    }
}
