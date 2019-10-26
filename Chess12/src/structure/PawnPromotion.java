package structure;

public interface PawnPromotion {
    public void CheckTransPawnForW(int i, int x, int y);
    public void CheckTransPawnForB(int i, int x, int y);
    public void TransPawnForW(String str,String celln, int x, int y);
    public void TransPawnForB(String str,String celln, int x, int y);
}
