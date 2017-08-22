package com.iheartradio.m3u8.data;

import java.util.Objects;

public class ByteRange {
    private final int subRangeLength;
    private final int offset;

    public ByteRange(int subRangeLength, int offset) {
        this.subRangeLength = subRangeLength;
        this.offset = offset;
    }

    public int getSubRangeLength() {
        return subRangeLength;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "ByteRange{" +
                "subRangeLength=" + subRangeLength +
                ", offset=" + offset +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteRange byteRange = (ByteRange) o;
        return subRangeLength == byteRange.subRangeLength &&
                offset == byteRange.offset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subRangeLength, offset);
    }
}
