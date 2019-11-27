package Engine;

public enum Maze {
    A {
        public String getImage(){ return "maze-a-scaled.png"; };
        public String getLoaderFile(){ return "a.txt"; };
    },
    B {
        public String getImage(){ return "maze-b.png"; };
        public String getLoaderFile(){ return "b.txt"; };
    },
    C {
        public String getImage(){ return "maze-c.png"; };
        public String getLoaderFile(){ return "c.txt"; };
    },
    D {
        public String getImage(){ return "maze-d.png"; };
        public String getLoaderFile(){ return "d.txt"; };
    };

    public abstract String getImage();
    public abstract String getLoaderFile();
}
