import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

// abstract class
public class DataReader {
    private RandomAccessFile file;
    FileChannel channel;
    public int readInt32(int offset) throws IOException {
        file.seek(offset);

        byte[] bytes = new byte[Integer.BYTES];
        file.read(bytes);

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        return buffer.getInt();

    }

    public long readInt64(int offset) throws IOException {

        /*byte[] bytes = new byte[Long.BYTES];

        channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();
        buffer.order(java.nio.ByteOrder.LITTLE_ENDIAN); // Set byte order to little-endian
        buffer.get(bytes);
        ByteBuffer wrapped = ByteBuffer.wrap(bytes);
        wrapped.order(java.nio.ByteOrder.LITTLE_ENDIAN);
        return wrapped.getLong(); // Read long from the wrapped buffer*/

        file.seek(offset);

        byte[] bytes = new byte[Long.BYTES];
        file.read(bytes);

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        return buffer.getLong();
    }

    public char readChar(int offset) throws IOException {
        /*channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(Character.BYTES);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();

        return buffer.getChar();*/
        file.seek(offset);
        return file.readChar();
    }

    public String readString(long offset, int length) throws IOException {

        channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();
        return new String(buffer.array());

    }

    public byte[] readBytes(int offset, int length) throws IOException {
        channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.position(offset);
        channel.read(buffer);
        buffer.flip();

        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);


        for (byte b : bytes) {
            String hex = String.format("%02X", b);
            int decimal = b & 0xFF;
            System.out.println("Hex: " + hex + ", Decimal: " + decimal);
        }

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

    public DataReader(RandomAccessFile file){
        this.file = file;
    }

    // TODO
    private class TDMSSegmentInfo {
        private int mask;

        private final int metadataMask = 0b0000001;
        private final int rawdataMask = 0b0000100;
        private final int DAQmxMask = 0b1000000;
        private final int interleavedMask = 0b0010000;
        private final int bigEndianMask = 0b0100000;
        private final int newObjectMask = 0b00000010;

        public TDMSSegmentInfo(int mask, boolean hasMetaData, boolean hasRawData, boolean hasDAQmxMask, boolean isInterleaved, boolean isBigEndian, boolean hasNewObjectList){
            this.mask = mask;
        }

        public boolean hasMetadata(){

            return (mask & metadataMask) > 0;
        }

        public boolean hasRawData(){

            return (mask & rawdataMask) > 0;
        }
        public boolean hasDAQmxMask() {
            return (mask & DAQmxMask) > 0;
        }
        public boolean isBigEndian(){
            return (mask & bigEndianMask) >0;

        }
        public boolean isInterLeaved(){
            return (mask & interleavedMask) > 0;
        }

        public boolean hasNewObjectList(){
            return (mask & newObjectMask) > 0;

        }
    }

}





