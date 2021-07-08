public class TestData {
    private String game;
    private String n;


    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
    @Override
    public String toString(){
        return "{"+
                "game="+game +'\n'+
                "n="+n+'\n'+
                '}';
    }
}
