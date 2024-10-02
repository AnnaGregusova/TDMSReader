package cz.cuni.mff.TDMSReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class DataReader {
    RandomAccessFile file;
    FileChannel channel;

    public DataReader(RandomAccessFile file) {
        this.file = file;
    }

    public int readInt32(int offset) throws IOException, InvalidOffsetException {
        checkOffset(offset);
        file.seek(offset);
        byte[] bytes = new byte[Integer.BYTES];
        file.read(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }

    public long readInt64(int offset) throws IOException, InvalidOffsetException {
        checkOffset(offset);
        file.seek(offset);
        byte[] bytes = new byte[Long.BYTES];
        file.read(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getLong();
    }

    public char readChar(int offset) throws IOException, InvalidOffsetException {
        checkOffset(offset);
        file.seek(offset);
        return file.readChar();
    }

    public String readString(long offset, int length) throws IOException {
        checkOffset(offset);
        channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();
        return new String(buffer.array());
    }

    public byte[] readBytes(int offset, int length) throws IOException {
        checkOffset(offset);
        channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);

        ArrayList<Byte> byteArrayList = new ArrayList<>();
        while (buffer.hasRemaining()) {
            byteArrayList.add(buffer.get());
            byte b = buffer.get();
            String hex = String.format("%02X", b); // Hexadecimal
            int decimal = b & 0xFF; // Decimal
        }
        return bytes;
    }

    public void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            String hex = String.format("%02X", b);
            int decimal = b & 0xFF;
            System.out.print(hex);
        }
        System.out.println();
    }

    class DataTypeReader {
        Object readTimeStamp(int currentOffset) throws IOException {
            byte[] bytes = readBytes(currentOffset, 16);
            ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

            // Extract fraction part
            long fraction = buffer.getLong();
            double result = 0D;
            for (int j = 0; j < Long.BYTES * 8; j++) {
                result += (fraction & 0x01);
                result /= 2;
                fraction >>>= 1;
            }

            // Extract seconds part
            long seconds = buffer.getLong();
            Instant niEpoch = OffsetDateTime.of(1904, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC).toInstant();
            Instant timestamp = niEpoch.plusSeconds(seconds).plusNanos(Double.valueOf(result * 1E9).longValue());

            // Return the timestamp as a string
            return timestamp;
        }

        Object readDoubleFloat(int currentOffset) throws IOException {
            byte[] bytes = readBytes(currentOffset, 8);
            ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
            return buffer.getDouble();
        }
    }

    private class TDMSSegmentMask {
        private int mask;
        private final int metadataMask = 0b0000001;
        private final int rawdataMask = 0b0000100;
        private final int DAQmxMask = 0b1000000;
        private final int interleavedMask = 0b0010000;
        private final int bigEndianMask = 0b0100000;
        private final int newObjectMask = 0b00000010;

        public TDMSSegmentMask(int mask, boolean hasMetaData, boolean hasRawData, boolean hasDAQmxMask,
                               boolean isInterleaved, boolean isBigEndian, boolean hasNewObjectList) {
            this.mask = mask;
        }

        public boolean hasMetadata() {
            return (mask & metadataMask) > 0;
        }

        public boolean hasRawData() {
            return (mask & rawdataMask) > 0;
        }

        public boolean hasDAQmxMask() {
            return (mask & DAQmxMask) > 0;
        }

        public boolean isBigEndian() {
            return (mask & bigEndianMask) > 0;
        }

        public boolean isInterLeaved() {
            return (mask & interleavedMask) > 0;
        }

        public boolean hasNewObjectList() {
            return (mask & newObjectMask) > 0;
        }
    }
    private void checkOffset(long offset){
        if (offset < 0){
            throw  new InvalidOffsetException("Offset can not be a negative number." + offset);
        }
    }
    private class InvalidOffsetException extends IllegalArgumentException {
        public InvalidOffsetException(String message) {
            super(message);
        }
    }
}
