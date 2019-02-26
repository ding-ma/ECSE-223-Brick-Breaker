package ca.mcgill.ecse223.block.persistence;
import ca.mcgill.ecse223.block.model.Block223;

public class Block223Persistence {

    private static String filename = "data.block223";

    public static void save(Block223 block) {
        PersistenceObjectStream.serialize(block);
    }

    public static Block223 load() {
        PersistenceObjectStream.setFilename(filename);
        Block223 block = (Block223) PersistenceObjectStream.deserialize();

        if (block == null) {
            block = new Block223();
        }
//        else {
//            block.reinitialize();
//            //find reinitialize in block 223 class, compared to btms. this method doesnt exist in block
//        }
        return block;
    }

    public static void setFilename(String newFilename) {
        filename = newFilename;
    }

}
