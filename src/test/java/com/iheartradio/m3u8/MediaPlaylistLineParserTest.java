package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.EncryptionData;
import com.iheartradio.m3u8.data.EncryptionMethod;
import com.iheartradio.m3u8.data.MapInfo;
import org.junit.Test;

import java.util.Arrays;

public class MediaPlaylistLineParserTest extends LineParserStateTestCase {
    @Test
    public void testEXT_X_TARGETDURATION() throws Exception {
        final IExtTagParser handler = MediaPlaylistLineParser.EXT_X_TARGETDURATION;
        final String tag = Constants.EXT_X_TARGETDURATION_TAG;
        final String line = "#" + tag + ":60";

        assertEquals(tag, handler.getTag());

        handler.parse(line, mParseState);
        assertEquals(60, (int) mParseState.getMedia().targetDuration);

        assertParseThrows(handler, line, ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES);
    }

    @Test
    public void testEXTINF() throws Exception {
        final IExtTagParser handler = MediaPlaylistLineParser.EXTINF;
        final String tag = Constants.EXTINF_TAG;
        final String line = "#" + tag + ":-1,TOP 100";

        assertEquals(tag, handler.getTag());

        handler.parse(line, mParseState);
        assertEquals(-1f, mParseState.getMedia().trackInfo.duration);
        assertEquals("TOP 100", mParseState.getMedia().trackInfo.title);
    }

    @Test
    public void testEXT_X_KEY() throws Exception {
        final IExtTagParser handler = MediaPlaylistLineParser.EXT_X_KEY;
        final String tag = Constants.EXT_X_KEY_TAG;
        final String uri = "http://foo.bar.com/";
        final String format = "format";

        final String line = "#" + tag +
                ":METHOD=AES-128" +
                ",URI=\"" + uri + "\"" +
                ",IV=0x1234abcd5678EF90" +
                ",KEYFORMAT=\"" + format + "\"" +
                ",KEYFORMATVERSIONS=\"1/2/3\"";

        assertEquals(tag, handler.getTag());

        handler.parse(line, mParseState);
        EncryptionData encryptionData = mParseState.getMedia().encryptionData;
        assertEquals(EncryptionMethod.AES, encryptionData.getMethod());
        assertEquals(uri, encryptionData.getUri());

        assertEquals(
                Arrays.asList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 10, (byte) 11, (byte) 12, (byte) 13,
                              (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 14, (byte) 15, (byte) 9, (byte) 0),
                encryptionData.getInitializationVector());

        assertEquals(format, encryptionData.getKeyFormat());
        assertEquals(Arrays.asList(1, 2, 3), encryptionData.getKeyFormatVersions());
    }

    @Test
    public void testEXT_X_MAP() throws Exception {
        final IExtTagParser handler = MediaPlaylistLineParser.EXT_X_MAP;
        final String tag = Constants.EXT_X_MAP;
        final String uri = "init.mp4";
        final int subRangeLength = 350;
        final int offset = 76;

        final String line = "#" + tag +
                ":URI=\"" + uri + "\"" +
                ",BYTERANGE=\"" + subRangeLength + "@" + offset + "\"";

        assertEquals(tag, handler.getTag());
        handler.parse(line, mParseState);
        MapInfo mapInfo = mParseState.getMedia().mapInfo;
        assertEquals(uri, mapInfo.getUri());
        assertNotNull(mapInfo.getByteRange());
        assertEquals(subRangeLength, mapInfo.getByteRange().getSubRangeLength());
        assertEquals(offset, mapInfo.getByteRange().getOffset());
    }
}
