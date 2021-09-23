import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

public class UuidTest
{

    @Test
    public void testUuid() throws Exception
    {


        final String string = "CA6BB671-30B5-46C1-8E89E12B39366374";

        byte[] bytes = concatenate(toByteArray(new UUID(0,0)), string.getBytes(StandardCharsets.UTF_8));
        final String s = UUID.nameUUIDFromBytes(bytes).toString();

        System.out.println(s);

    }


    @Test
    public void testBnMuuid()
    {

        final String idStr = "CA6BB671-30B5-46C1-8E89E12B39366374";

        System.out.println(UUID.fromString(idStr.toLowerCase()));

    }



    public byte[] toByteArray(final UUID uuid)
    {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }


    public byte[] concatenate(final byte[] namespaceUuidBytes, final byte[] nameBytes)
    {
        final byte[] combinedBytes = new byte[namespaceUuidBytes.length + nameBytes.length];
        System.arraycopy(namespaceUuidBytes, 0, combinedBytes, 0, namespaceUuidBytes.length);
        System.arraycopy(nameBytes, 0, combinedBytes, namespaceUuidBytes.length, nameBytes.length);
        return combinedBytes;
    }

}
