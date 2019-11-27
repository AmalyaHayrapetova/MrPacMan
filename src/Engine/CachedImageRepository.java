package Engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CachedImageRepository {

    private Map<String, BufferedImage> cache;
    private Game game;

    CachedImageRepository(Game game){
        cache = new HashMap<>();
        this.game = game;
    }

    public BufferedImage Get(String name){
        if(cache.containsKey(name))
            return cache.get(name);
        String path = game.getResourcePath(name);
        BufferedImage image=null;
        try
        {
            image = ImageIO.read(new File(path));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        cache.put(name, image);
        return image;
    }
}
