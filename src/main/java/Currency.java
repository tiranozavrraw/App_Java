public enum Currency {
    USD(1),
    GBP(2),
    EUR(3),
    ;

    private final int index;

    Currency(int index) {
        this.index = index;
    }

    public int getIndex (){
        return index;
    }
}
