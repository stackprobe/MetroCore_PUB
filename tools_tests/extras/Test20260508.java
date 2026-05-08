package tools_tests.extras;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tools.CsvFileReader;
import tools.CsvFileWriter;

public class Test20260508 {
    public static void main(String[] args) {
        try {
            run();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static class ConvInfo_t {
        public List<String> srcTokens = new ArrayList<String>();
        public String destCell1;
        public String destCell2;
    }

    private static List<ConvInfo_t> _convInfos = new ArrayList<ConvInfo_t>();

    private static void addConvInfo(String destCell1, String destCell2, String... srcTokens) {
        ConvInfo_t ci = new ConvInfo_t();

        ci.srcTokens = Arrays.asList(srcTokens);
        ci.destCell1 = destCell1;
        ci.destCell2 = destCell2;

        _convInfos.add(ci);
    }

    private static void run() throws IOException {

        // --

        // addConvInfo ( dest1, dest2, srcTokens... );

        addConvInfo("あああ", "いいい", "AAA", "BBB");
        addConvInfo("ううう", "えええ", "CCC", "DDD");
        addConvInfo("おおお", "かかか", "EEE", "FFF");

        // --

        List<String[]> rows = CsvFileReader.readToEnd(
                "C:\\temp\\input.csv"
                );

        List<String[]> dest = new ArrayList<String[]>();

        for (int rowidx = 0; rowidx < rows.size(); rowidx++) {
            String cell = rows.get(rowidx)[0].trim();
            String dest1;
            String dest2;

            if ("".equals(cell) || "-".equals(cell)) {
                dest1 = "-";
                dest2 = "-";
            }
            else {
                int i;
                for (i = 0; i < _convInfos.size(); i++) {
                    int ti;
                    for (ti = 0; ti < _convInfos.get(i).srcTokens.size(); ti++) {
                        if (!cell.contains(_convInfos.get(i).srcTokens.get(ti))) {
                            break;
                        }
                    }
                    if (ti == _convInfos.get(i).srcTokens.size()) {
                        break;
                    }
                }

                if (i < _convInfos.size()) {
                    dest1 = _convInfos.get(i).destCell1;
                    dest2 = _convInfos.get(i).destCell2;
                }
                else {
                    dest1 = "★";
                    dest2 = "★";
                }
            }

            dest.add(new String[] {
                    dest1,
                    dest2,
                    });
        }

        CsvFileWriter.writeRows("C:\\temp\\output.csv", dest);
    }
}
