package com.iheartradio.m3u8.data;

import java.util.Objects;

public class TrackData {
    private final String mUri;
    private final TrackInfo mTrackInfo;
    private final EncryptionData mEncryptionData;
    private final String mProgramDateTime;
    private final boolean mHasDiscontinuity;
    private final MapInfo mMapInfo;

    private TrackData(String uri, TrackInfo trackInfo, EncryptionData encryptionData, String programDateTime, boolean hasDiscontinuity, MapInfo mapInfo) {
        mUri = uri;
        mTrackInfo = trackInfo;
        mEncryptionData = encryptionData;
        mProgramDateTime = programDateTime;
        mHasDiscontinuity = hasDiscontinuity;
        mMapInfo = mapInfo;
    }

    public String getUri() {
        return mUri;
    }

    public boolean hasTrackInfo() {
        return mTrackInfo != null;
    }

    public TrackInfo getTrackInfo() {
        return mTrackInfo;
    }

    public boolean hasEncryptionData() {
        return mEncryptionData != null;
    }

    public boolean isEncrypted() {
        return hasEncryptionData() &&
               mEncryptionData.getMethod() != null &&
               mEncryptionData.getMethod() != EncryptionMethod.NONE;
    }

    public boolean hasProgramDateTime() {
        return mProgramDateTime != null && mProgramDateTime.length() > 0;
    }

    public String getProgramDateTime() {
        return mProgramDateTime;
    }

    public boolean hasDiscontinuity() {
        return mHasDiscontinuity;
    }

    public EncryptionData getEncryptionData() {
        return mEncryptionData;
    }


    public boolean hasMapInfo() {
        return mMapInfo != null;
    }

    public Builder buildUpon() {
        return new Builder(getUri(), mTrackInfo, mEncryptionData, mHasDiscontinuity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUri, mEncryptionData, mTrackInfo, mHasDiscontinuity);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TrackData)) {
            return false;
        }

        TrackData other = (TrackData) o;

        return Objects.equals(mUri, other.mUri) &&
               Objects.equals(mTrackInfo, other.mTrackInfo) &&
               Objects.equals(mEncryptionData, other.mEncryptionData) &&
               Objects.equals(mProgramDateTime, other.mProgramDateTime) &&
               Objects.equals(mHasDiscontinuity, other.mHasDiscontinuity);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("(TrackData")
                .append(" mUri=").append(mUri)
                .append(" mTrackInfo=").append(mTrackInfo)
                .append(" mEncryptionData=").append(mEncryptionData)
                .append(" mProgramDateTime=").append(mProgramDateTime)
                .append(" mHasDiscontinuity=").append(mHasDiscontinuity)
                .append(")")
                .toString();
    }

    public MapInfo getMapInfo() {
        return mMapInfo;
    }

    public static class Builder {
        private String mUri;
        private TrackInfo mTrackInfo;
        private EncryptionData mEncryptionData;
        private String mProgramDateTime;
        private boolean mHasDiscontinuity;
        private MapInfo mMapInfo;

        public Builder() {
        }

        private Builder(String uri, TrackInfo trackInfo, EncryptionData encryptionData, boolean hasDiscontinuity) {
            mUri = uri;
            mTrackInfo = trackInfo;
            mEncryptionData = encryptionData;
            mHasDiscontinuity = hasDiscontinuity;
        }

        public Builder withUri(String url) {
            mUri = url;
            return this;
        }

        public Builder withTrackInfo(TrackInfo trackInfo) {
            mTrackInfo = trackInfo;
            return this;
        }

        public Builder withEncryptionData(EncryptionData encryptionData) {
            mEncryptionData = encryptionData;
            return this;
        }

        public Builder withProgramDateTime(String programDateTime) {
            mProgramDateTime = programDateTime;
            return this;
        }

        public Builder withDiscontinuity(boolean hasDiscontinuity) {
            mHasDiscontinuity = hasDiscontinuity;
            return this;
        }

        public Builder withMapInfo(MapInfo mapInfo) {
            mMapInfo = mapInfo;
            return this;
        }

        public TrackData build() {
            return new TrackData(mUri, mTrackInfo, mEncryptionData, mProgramDateTime, mHasDiscontinuity, mMapInfo);
        }
    }
}
