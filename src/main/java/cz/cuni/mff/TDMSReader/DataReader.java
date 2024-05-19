package cz.cuni.mff.TDMSReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * Abstract class for reading data from a file.
 */
public class DataReader {
    RandomAccessFile file;
    FileChannel channel;

    /**
     * Reads a 32-bit integer from the specified offset in the file.
     *
     * @param offset The offset in the file from which to read the integer.
     * @return The integer read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int readInt32(int offset) throws IOException {
        file.seek(offset);

        byte[] bytes = new byte[Integer.BYTES];
        file.read(bytes);

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        return buffer.getInt();
    }

    /**
     * Reads a 64-bit integer from the specified offset in the file.
     *
     * @param offset The offset in the file from which to read the integer.
     * @return The long integer read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public long readInt64(int offset) throws IOException {
        file.seek(offset);

        byte[] bytes = new byte[Long.BYTES];
        file.read(bytes);

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        return buffer.getLong();
    }

    /**
     * Reads a character from the specified offset in the file.
     *
     * @param offset The offset in the file from which to read the character.
     * @return The character read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public char readChar(int offset) throws IOException {
        file.seek(offset);
        return file.readChar();
    }

    /**
     * Reads a string from the specified offset in the file.
     *
     * @param offset The offset in the file from which to read the string.
     * @param length The length of the string to read.
     * @return The string read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public String readString(long offset, int length) throws IOException {
        channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();
        return new String(buffer.array());
    }

    /**
     * Reads bytes from the specified offset in the file.
     *
     * @param offset The offset in the file from which to read the bytes.
     * @param length The number of bytes to read.
     * @return An array of bytes read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public byte[] readBytes(int offset, int length) throws IOException {
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
            System.out.println("Hex: " + hex + ", Decimal: " + decimal);
        }
        return bytes;
    }

    /**
     * Prints the hexadecimal representation of bytes.
     *
     * @param bytes The array of bytes to print.
     */
    public void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            String hex = String.format("%02X", b);
            int decimal = b & 0xFF;
            System.out.print(hex);
        }
    }

    /**
     * Constructs a DataReader with the given RandomAccessFile.
     *
     * @param file The RandomAccessFile to read from.
     */
    public DataReader(RandomAccessFile file) {
        this.file = file;
    }

    /**
     * Inner class representing information about a TDMSSegment.
     */
    private class TDMSSegmentMask {
        private int mask;

        private final int metadataMask = 0b0000001;
        private final int rawdataMask = 0b0000100;
        private final int DAQmxMask = 0b1000000;
        private final int interleavedMask = 0b0010000;
        private final int bigEndianMask = 0b0100000;
        private final int newObjectMask = 0b00000010;

        /**
         * Constructs a TDMSSegmentInfo with the given mask and boolean values.
         *
         * @param mask              The mask representing the segment info.
         * @param hasMetaData       Indicates if metadata is present.
         * @param hasRawData        Indicates if raw data is present.
         * @param hasDAQmxMask      Indicates if DAQmx mask is present.
         * @param isInterleaved     Indicates if data is interleaved.
         * @param isBigEndian       Indicates if data is in big endian format.
         * @param hasNewObjectList Indicates if a new object list is present.
         */
        public TDMSSegmentMask(int mask, boolean hasMetaData, boolean hasRawData, boolean hasDAQmxMask,
                               boolean isInterleaved, boolean isBigEndian, boolean hasNewObjectList) {
            this.mask = mask;
        }

        /**
         * Checks if metadata is present.
         *
         * @return true if metadata is present, otherwise false.
         */
        public boolean hasMetadata() {
            return (mask & metadataMask) > 0;
        }

        /**
         * Checks if raw data is present.
         *
         * @return true if raw data is present, otherwise false.
         */
        public boolean hasRawData() {
            return (mask & rawdataMask) > 0;
        }

        /**
         * Checks if DAQmx mask is present.
         *
         * @return true if DAQmx mask is present, otherwise false.
         */
        public boolean hasDAQmxMask() {
            return (mask & DAQmxMask) > 0;
        }

        /**
         * Checks if data is in big endian format.
         *
         * @return true if data is in big endian format, otherwise false.
         */
        public boolean isBigEndian() {
            return (mask & bigEndianMask) > 0;
        }

        /**
         * Checks if data is interleaved.
         *
         * @return true if data is interleaved, otherwise false.
         */
        public boolean isInterLeaved() {
            return (mask & interleavedMask) > 0;
        }

        /**
         * Checks if a new object list is present.
         *
         * @return true if a new object list is present, otherwise false.
         */
        public boolean hasNewObjectList() {
            return (mask & newObjectMask) > 0;
        }
    }
}
