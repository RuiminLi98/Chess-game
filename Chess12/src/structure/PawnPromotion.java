package structure;

/** 
 * @author Junjie He
 * @author Ruimin Li
 */

/**
 * This is a interface for promotion function
 * four function about promotion are stored in this interface
 */
public interface PawnPromotion {
    public void CheckTransPawnForW(int i, int x, int y);
    public void CheckTransPawnForB(int i, int x, int y);
    public void TransPawnForW(String str,String celln, int x, int y);
    public void TransPawnForB(String str,String celln, int x, int y);
}
